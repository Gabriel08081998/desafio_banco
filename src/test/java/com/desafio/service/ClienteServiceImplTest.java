package com.desafio.service;

import com.desafio.model.Cliente;
import com.desafio.repository.ClienteRepository;
import com.desafio.view.ClienteException;
import com.desafio.view.ClienteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {
    public static final long ID = 1L;
    public static final String NOME = "Bruno";
    public static final String CPF = "145678910";
    public static final String SENHA = "123";

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;
    private ClienteDTO clienteDTO;
    private Optional<Cliente> optionalCliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

//    @Test
//    void criarCliente() {
//
//        when(clienteRepository.findByCpf(any())).thenReturn(Optional.empty());
//        when(clienteRepository.save(any())).thenReturn(cliente);
//
//        Optional<Cliente> optional = clienteService.criarCliente(clienteDTO);
//        assertTrue(optional.isPresent());
//        assertEquals(ID, optional.get().getId());
//        assertEquals(NOME, optional.get().getNome());
//        assertEquals(CPF, optional.get().getCpf());
//        assertEquals(SENHA, optional.get().getSenha());
//
//    }

    @Test
    void criarClienteException() {
        when(clienteRepository.findByCpf(any())).thenReturn(optionalCliente);
        ClienteException exception = assertThrows(ClienteException.class, () -> clienteService.criarCliente(clienteDTO));
        assertEquals("Cliente ja existente", exception.getMessage());
    }
    @Test
    void findByCpf() {
        when(clienteRepository.existsByCpf(any())).thenReturn(true);
        boolean result = clienteService.existsByCpf(CPF);
        assertTrue(result);
    }
    private void startCliente(){
        cliente = new Cliente(ID, NOME, CPF, SENHA);
        clienteDTO = new ClienteDTO(ID, NOME, CPF, SENHA);
        optionalCliente = Optional.of(new Cliente(ID, NOME, CPF, SENHA));

    }
}