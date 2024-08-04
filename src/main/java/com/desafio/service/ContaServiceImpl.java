package com.desafio.service;

import com.desafio.model.Cliente;
import com.desafio.repository.ClienteRepository;
import com.desafio.view.ClienteException;
import com.desafio.model.Conta;
import com.desafio.repository.ContaRepository;
import com.desafio.view.ClienteDTO;
import com.desafio.view.ContaDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Optional<Conta> criarConta(long idCliente, ContaDTO contaDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(idCliente);
        if (!optionalCliente.isPresent()) {
            throw  new ClienteException("Cliente não encontrado");
        }
        if (!contaDTO.getCpf().equals(optionalCliente.get().getCpf())) {
            throw new ClienteException("CPF inválido");
        }
        Optional<Conta> optionalConta = contaRepository.findByCpf(contaDTO.getCpf());
        if (optionalConta.isPresent()){
            throw new ClienteException("Conta ja existente");
        }
        Cliente cliente =optionalCliente.get();

        Conta conta = Conta.builder()
                .idCliente(cliente)
                .cpf(cliente.getCpf())
                .agencia(contaDTO.getAgencia())
                .numero(contaDTO.getNumero())
                .tipoConta(contaDTO.getTipoConta())
                .saldo(contaDTO.getSaldo())
                .build();

        BeanUtils.copyProperties(contaDTO, conta);
        Conta save = contaRepository.save(conta);
        conta.setIdCliente(cliente);
//        cliente.setSenha("*****");
        return Optional.of(save);
        }

    @Override
    public Optional<Conta> buscarConta(long idConta) {
        Optional<Conta> optionalConta = contaRepository.findById(idConta);
        if (!optionalConta.isPresent()) {
            throw new ClienteException("Conta não encontrada");
        }

        return Optional.of(optionalConta.get());
    }


}
