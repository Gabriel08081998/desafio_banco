package com.desafio.service;

import com.desafio.model.Cliente;
import com.desafio.repository.ClienteRepository;
import com.desafio.repository.ContaRepository;
import com.desafio.view.ClienteException;
import com.desafio.model.Conta;
import com.desafio.view.ContaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContaServiceImplTest {

    public static final long ID = 1L;
    public static final String CPF = "12345678910";
    public static final int AGENCIA = 12;
    public static final int NUMERO = 1234;
    public static final String TIPO_CONTA = "corrente";
    public static final double SALDO = 123.0;
    public static final String NOME = "Bruno";
    public static final String SENHA = "123";
    public static final String CPF1 = "12345678910";
    public static final int AGENCIA1 = 12;
    public static final int NUMERO1 = 1234;
    public static final String TIPO_CONTA1 = "corrente";
    public static final double SALDO1 = 123.0;
    @InjectMocks
    private ContaServiceImpl contaServiceImpl;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ClienteRepository clienteRepository;



    private Conta conta;
    private ContaDTO contaDTO;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startConta();
    }

    @Test
    void criarConta() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(contaRepository.findByCpf(any())).thenReturn(Optional.empty());
        when(contaRepository.save(any())).thenReturn(conta);
        contaServiceImpl.criarConta(ID, contaDTO);
    }

    @Test
    void criarContaClienteNaoEncontrado() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        ClienteException exception = assertThrows(ClienteException.class,() -> contaServiceImpl.criarConta(ID, contaDTO));
        assertEquals("Cliente não encontrado", exception.getMessage());

    }

    @Test
    void criarContaCpfInvalido() {
        Cliente  cliente1 = new Cliente();
        BeanUtils.copyProperties(cliente, cliente1);
        cliente1.setCpf("12345678911");
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente1));
        ClienteException exception = assertThrows(ClienteException.class, () -> contaServiceImpl.criarConta(ID, contaDTO));

        assertEquals("CPF inválido", exception.getMessage());
    }

    @Test
    void criarContaJaExistente() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(contaRepository.findByCpf(any())).thenReturn(Optional.of(conta));
        ClienteException exception = assertThrows(ClienteException.class, () -> contaServiceImpl.criarConta(ID, contaDTO));
        assertEquals("Conta ja existente", exception.getMessage());
    }

    @Test
    void buscarConta() {

        when(contaRepository.findById(anyLong())).thenReturn(Optional.of(conta));
        contaServiceImpl.buscarConta(ID);

    }
    @Test
    void buscarContaNaoEncontrada() {
        when(contaRepository.findById(anyLong())).thenReturn(Optional.empty());
        ClienteException exception = assertThrows(ClienteException.class, () -> contaServiceImpl.buscarConta(ID));
        assertEquals("Conta não encontrada", exception.getMessage());
    }

    private void startConta() {
        conta = new Conta(ID,cliente, CPF, AGENCIA, NUMERO, TIPO_CONTA, SALDO);
        cliente = new Cliente(ID, NOME, CPF, SENHA);
        contaDTO = new ContaDTO(CPF1, AGENCIA1, NUMERO1, TIPO_CONTA1, SALDO1);
    }
}