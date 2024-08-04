package com.desafio.service;

import com.desafio.model.Cliente;
import com.desafio.view.ClienteDTO;

import java.util.Optional;

public interface ClienteService {
    Optional<Cliente> criarCliente(ClienteDTO clienteDTO);


}
