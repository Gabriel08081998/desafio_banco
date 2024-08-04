package com.desafio.service;

import com.desafio.model.Cliente;
import com.desafio.repository.ClienteRepository;
import com.desafio.view.ClienteDTO;
import com.desafio.view.ClienteException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Override
    public Optional<Cliente> criarCliente(ClienteDTO clienteDTO) {
        Optional<Cliente> optionalcpf = clienteRepository.findByCpf(clienteDTO.getCpf());
        if (!optionalcpf.isEmpty()) {
            throw new ClienteException("Cliente ja existente");
        }
        Cliente cliente = Cliente.builder().build();
        BeanUtils.copyProperties(clienteDTO, cliente);
        Cliente save = clienteRepository.save(cliente);
        save.setSenha("*****");
        return Optional.of(save);

    }

    public boolean existsByCpf(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }
}
