package com.desafio.controller;

import com.desafio.model.Cliente;
import com.desafio.model.Conta;
import com.desafio.service.ContaService;
import com.desafio.view.ClienteDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContaControllerTest {

    public static final long ID = 1L;
    public static final String CPF = "13245532";
    public static final int AGENCIA = 12;
    public static final int NUMERO = 1234;
    public static final String TIPO_CONTA = "corrente";
    public static final double SALDO = 123.0;
    public static final String CPF1 = "12367566";
    public static final int AGENCIA1 = 12;
    public static final int NUMERO1 = 12346;
    public static final String TIPO_CONTA1 = "corrente";
    public static final double SALDO1 = 600.0;
    public static final long ID1 = 2L;
    public static final String NOME = "Bruno";
    public static final String CPF2 = "12345678910";
    public static final String SENHA = "123";
    public static final String CPF3 = "1234545";
    public static final int AGENCIA2 = 12;
    public static final int NUMERO2 = 12345;
    public static final String TIPO_CONTA2 = "corrente";
    public static final double SALDO2 = 6000.0;
    public static final long ID2 = 1L;
    public static final String NOME1 = "Bruno";
    public static final String CPF4 = "12345678910";
    public static final String SENHA1 = "123";
    @InjectMocks
    private ContaController contaController;

    @Mock
    private ContaService contaService;

    private Conta conta;

    private ContaDTO contaDTO;

    private Cliente cliente;

    private ClienteDTO clienteDTO;

    private Optional<Conta> optionalConta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startConta();
    }

    @Test
    void criarContaBadRequest() {
        when(contaService.criarConta(cliente.getId(),contaDTO)).thenReturn(Optional.empty());
        ResponseEntity<?> responseEntity = contaController.criarConta(cliente.getId(), contaDTO);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
    @Test
    void criarContaCreated() {
        when(contaService.criarConta(cliente.getId(),contaDTO)).thenReturn(Optional.of(conta));
        ResponseEntity<?> responseEntity = contaController.criarConta(cliente.getId(), contaDTO);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void buscarContasCreated() {
        when(contaService.buscarConta(cliente.getId())).thenReturn(Optional.of(conta));
        ResponseEntity<?> responseEntity = contaController.buscarConta(cliente.getId());
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void buscarContasBadRequest()  throws Exception {
        when(contaService.buscarConta(cliente.getId())).thenReturn(Optional.empty());
        ResponseEntity<?> responseEntity = contaController.buscarConta(cliente.getId());
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    private void startConta() {
        conta = new Conta(ID, cliente, CPF, AGENCIA, NUMERO, TIPO_CONTA, SALDO);
        contaDTO = new ContaDTO(CPF1, AGENCIA1, NUMERO1, TIPO_CONTA1, SALDO1);
        cliente = new Cliente(ID1, NOME, CPF2, SENHA);
        clienteDTO = new ClienteDTO(ID2, NOME1, CPF4, SENHA1);


    }

}