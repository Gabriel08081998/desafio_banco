package com.desafio.controller;

import com.desafio.model.Cliente;
import com.desafio.service.ClienteService;
import com.desafio.view.ClienteDTO;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    public static final long ID = 1L;
    public static final String NOME = "Bruno";
    public static final String CPF = "12345678910";
    public static final String SENHA = "123";
    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private Optional<Cliente> optionalCliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void criarCliente() {
        when(clienteService.criarCliente(any())).thenReturn(optionalCliente);
        ResponseEntity<?> response = clienteController.criarCliente(clienteDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    void crearClienteException() {
        when(clienteService.criarCliente(any())).thenReturn(Optional.empty());
        ResponseEntity<?> response = clienteController.criarCliente(clienteDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    private void startCliente(){
        cliente = new Cliente(ID, NOME, CPF, SENHA);
        clienteDTO = new ClienteDTO(ID, NOME, CPF, SENHA);
        optionalCliente = Optional.of(new Cliente(ID, NOME, CPF, SENHA));

    }
}