package com.desafio.controller;

import com.desafio.model.Cliente;
import com.desafio.model.Conta;
import com.desafio.model.Transacoes;
import com.desafio.service.TransacoesService;
import com.desafio.view.TransacoesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TransacoesControllerTest {
    public static final long ID_PAGADOR = 1L;
    public static final LocalDateTime DATA_TRANSACAO = LocalDateTime.now();
    public static final String NOME_DESTINATARIO = "Bruno";
    public static final int AGENCIA = 12;
    public static final String CPF = "12345678910";
    public static final int NUMERO = 12345;
    public static final double VALOR = 6000.0;
    @InjectMocks
    private TransacoesController transacoesController;

    @Mock
    private TransacoesService transacoesService;

    private Transacoes transacoes;

    private TransacoesDTO transacoesDTO;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startTransacoes();
    }

    @Test
    void trasferir() {
        when(transacoesService.trasferir(ID_PAGADOR,transacoesDTO)).thenReturn(Optional.of(transacoes));
        ResponseEntity<?> transacao1 = transacoesController.trasferir(ID_PAGADOR,transacoesDTO);
        assertNotNull(transacao1);
        assertEquals(HttpStatus.CREATED, transacao1.getStatusCode());

    }
    @Test
    void trasferirBadRequest() {
        when(transacoesService.trasferir(ID_PAGADOR,transacoesDTO)).thenReturn(Optional.empty());
        ResponseEntity<?> transacao1 = transacoesController.trasferir(ID_PAGADOR,transacoesDTO);
        assertNotNull(transacao1);
        assertEquals(HttpStatus.BAD_REQUEST, transacao1.getStatusCode());
    }

    @Test
    void buscarTransacoesPorCliente() {
        when(transacoesService.buscarTransacoesPorCliente(CPF)).thenReturn(List.of(transacoes));
        ResponseEntity<?> transacao1 = transacoesController.buscarTransacoesPorCliente(CPF);
        assertNotNull(transacao1);
        assertEquals(HttpStatus.OK, transacao1.getStatusCode());
    }

    private  void startTransacoes(){
        transacoes = new Transacoes(ID_PAGADOR,new Conta(), DATA_TRANSACAO, NOME_DESTINATARIO, AGENCIA, CPF, NUMERO, VALOR);
        transacoesDTO = new TransacoesDTO(AGENCIA,CPF,NUMERO,VALOR,DATA_TRANSACAO);

    }
}