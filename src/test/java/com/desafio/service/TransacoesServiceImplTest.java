package com.desafio.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.desafio.model.Cliente;
import com.desafio.model.Conta;
import com.desafio.model.Transacoes;
import com.desafio.repository.ClienteRepository;
import com.desafio.repository.ContaRepository;
import com.desafio.repository.TransacoesRepository;
import com.desafio.view.ClienteException;
import com.desafio.view.TransacoesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TransacoesServiceImplTest {

    public static final long ID_PAGADOR = 1L;
    public static final LocalDateTime DATA_TRANSACAO = LocalDateTime.now();
    public static final Conta CONTA_REMETENTE = new Conta();
    public static final String NOME_DESTINATARIO = "Bruno";
    public static final int AGENCIA = 21;
    public static final String CPF = "123435465423";
    public static final int NUMERO = 12345;
    public static final double VALOR = 123.99;
    public static final long ID = 1L;
    public static final Cliente ID_CLIENTE = new Cliente();
    public static final String CPF1 = "12345678910";
    public static final int AGENCIA1 = 12;
    public static final int NUMERO1 = 1234215;
    public static final String TIPO_CONTA = "corrente";
    public static final double SALDO = 6000.0;
    @InjectMocks
    private TransacoesServiceImpl transacoesServiceImpl;

    @Mock
    private TransacoesRepository transacoesRepository;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ClienteRepository clienteRepository;

    private Transacoes transacoes;

    private TransacoesDTO transacoesDTO;

    private Conta conta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startTransferencias();
    }

    @Test
    void trasferirContaInexistente() {

        when(contaRepository.findById(anyLong())).thenReturn(Optional.empty());
        ClienteException exception = assertThrows(ClienteException.class, () -> transacoesServiceImpl.trasferir(ID_PAGADOR, transacoesDTO));
        assertEquals("Conta inexistente", exception.getMessage());
    }
    @Test
    void trasferirSaldoInsuficiente() {

        conta.setSaldo(00.0);
        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));
        ClienteException exception = assertThrows(ClienteException.class, () -> transacoesServiceImpl.trasferir(ID, transacoesDTO));
        assertEquals("Saldo insuficiente", exception.getMessage());
    }
    @Test
    void trasferirContaDestinoInexistente() {
        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));
        ClienteException exception = assertThrows(ClienteException.class, () -> transacoesServiceImpl.trasferir(ID, transacoesDTO));
        assertEquals("Conta de destino inexistente", exception.getMessage());
    }

    @Test
    void trasferirEntreMesmasContas() {

        Conta conta1 = new Conta();
        BeanUtils.copyProperties(conta, conta1);
        conta1.setNumero(NUMERO);

        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta1));
        when(contaRepository.findByCpf(any())).thenReturn(Optional.of(conta));
        ClienteException exception = assertThrows(ClienteException.class, () -> transacoesServiceImpl.trasferir(ID, transacoesDTO));

        assertEquals("Não pode transferir para a sua mesma conta", exception.getMessage());
    }

    @Test
    void trasferirAgenciaDestinoInexistente() {

        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));
        when(contaRepository.findByCpf(any())).thenReturn(Optional.of(conta));
        ClienteException exception = assertThrows(ClienteException.class, () -> transacoesServiceImpl.trasferir(ID, transacoesDTO));
        assertEquals("Agencia de destino inexistente", exception.getMessage());
    }

    @Test
    void trasferirInformarNumeroContaDestino() {
        transacoesDTO.setNumero(0);

        ClienteException exception = assertThrows(ClienteException.class, () -> transacoesServiceImpl.trasferir(ID, transacoesDTO));
        assertEquals("Informar numero da conta de destino", exception.getMessage());
    }

    @Test
    void trasferirSucesso() {
        // Configurando o saldo da conta remetente para um valor que permite a transferência
        conta.setSaldo(VALOR + 100); // Saldo suficiente para a transferência

        // Simulando a conta de destino com saldo inicial
        Conta contaDestino = new Conta(ID + 1, ID_CLIENTE, "12345678911", 12, 1234444, TIPO_CONTA, 500.0);

        // Configurando o DTO da transação com dados válidos
        transacoesDTO.setNumero(12345); // Número da conta de destino
        transacoesDTO.setAgencia(12);   // Agência da conta de destino
        transacoesDTO.setCpf("12345678911"); // CPF da conta de destino

        // Simulando o comportamento dos repositórios
        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));
        when(contaRepository.findByCpf(anyString())).thenReturn(Optional.of(contaDestino));
        when(transacoesRepository.save(any(Transacoes.class))).thenAnswer(invocation -> {
            Transacoes transacao = invocation.getArgument(0);
            transacao.setIdPagador(1L); // Definindo um ID fictício para a transação salva
            return transacao;
        });

        // Executando a transferência
        Optional<Transacoes> transacao = transacoesServiceImpl.trasferir(ID, transacoesDTO);

        // Verificando se a transação foi realizada
        assertTrue(transacao.isPresent());

        // Verificando se o saldo foi atualizado corretamente
        assertEquals(VALOR + 100 - VALOR, conta.getSaldo(), 0.001);
        assertEquals(500.0 + VALOR, contaDestino.getSaldo(), 0.001);

        // Verificando se a transação foi salva corretamente
        Transacoes transacaoSalva = transacao.get();
        assertEquals(1L, transacaoSalva.getIdPagador()); // Verificando se o ID da transação foi definido corretamente
        assertEquals(ID_PAGADOR, transacaoSalva.getIdPagador());
        assertEquals(NUMERO, transacaoSalva.getNumero());
        assertEquals(VALOR, transacaoSalva.getValor(), 0.001);

    }

    @Test
    void buscarTransacoesPorCliente() {
        when(transacoesRepository.findByCpf(any())).thenReturn(List.of(transacoes));
        List<Transacoes> transacoes = transacoesServiceImpl.buscarTransacoesPorCliente(CPF);
        assertNotNull(transacoes);
        assertEquals(1, transacoes.size());
        assertEquals(ID_PAGADOR, transacoes.get(0).getIdPagador());
        assertEquals(NUMERO, transacoes.get(0).getNumero());
        assertEquals(VALOR, transacoes.get(0).getValor(), 0.001);
    }

    private void startTransferencias() {
        transacoes = new Transacoes(ID_PAGADOR, CONTA_REMETENTE, DATA_TRANSACAO, NOME_DESTINATARIO, AGENCIA, CPF, NUMERO, VALOR);
        transacoesDTO = new TransacoesDTO(AGENCIA,CPF,NUMERO,VALOR,DATA_TRANSACAO);
        conta = new Conta(ID, ID_CLIENTE, CPF1, AGENCIA1, NUMERO1, TIPO_CONTA, SALDO);
    }
}