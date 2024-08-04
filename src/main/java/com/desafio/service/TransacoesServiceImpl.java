package com.desafio.service;

import com.desafio.model.Cliente;
import com.desafio.repository.ClienteRepository;
import com.desafio.repository.TransacoesRepository;
import com.desafio.view.ClienteDTO;
import com.desafio.view.ClienteException;
import com.desafio.view.ContaDTO;
import com.desafio.view.TransacoesDTO;
import jakarta.transaction.Transactional;
import com.desafio.model.Conta;
import com.desafio.model.Transacoes;
import com.desafio.repository.ContaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TransacoesServiceImpl implements TransacoesService {

    @Autowired
    private TransacoesRepository transacoesRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional
    public Optional<Transacoes> trasferir(long idConta, TransacoesDTO transacoesDTO) {
        Optional<Conta> optionalConta = contaRepository.findById(idConta);
        if (transacoesDTO.getNumero() == 0){
            throw new ClienteException("Informar numero da conta de destino");

        }

        if (!optionalConta.isPresent()){
            throw new ClienteException("Conta inexistente");
        }
        Conta conta = optionalConta.get();
        if (conta.getSaldo() < transacoesDTO.getValor()){
            throw new ClienteException("Saldo insuficiente");
        }
        Optional<Conta> destinoContaOptional = contaRepository.findByCpf(transacoesDTO.getCpf());
        if (!destinoContaOptional.isPresent()){
            throw new ClienteException("Conta de destino inexistente");
        }
        if (conta.getNumero() == transacoesDTO.getNumero()){
            throw new ClienteException("Não pode transferir para a sua mesma conta");
        }
        if (conta.getAgencia() != transacoesDTO.getAgencia()){
            throw new ClienteException("Agencia de destino inexistente");
        }

        Conta contaDestino = destinoContaOptional.get();



            conta.setSaldo(conta.getSaldo() - transacoesDTO.getValor());
            contaRepository.save(conta);

            contaDestino.setSaldo(contaDestino.getSaldo() + transacoesDTO.getValor());
            contaRepository.save(contaDestino);

            Transacoes transacao = new Transacoes();
            BeanUtils.copyProperties(transacoesDTO, transacao);

            transacao.setContaRemetente(conta);
            transacao.setCpf(conta.getIdCliente().getCpf());
            transacao.setNomeDestinatario(contaDestino.getIdCliente().getNome());
            transacao.setAgencia(contaDestino.getAgencia());
            transacao.setDataTransacao(LocalDateTime.now());

            Transacoes salvarTransacao = transacoesRepository.save(transacao);
            return Optional.of(salvarTransacao);


    }

    @Override
    public List<Transacoes> buscarTransacoesPorCliente(String cpf) {
        List<Transacoes> transacoes = transacoesRepository.findByCpf(cpf);

        return transacoesRepository.findByCpf(cpf);
    }

}
