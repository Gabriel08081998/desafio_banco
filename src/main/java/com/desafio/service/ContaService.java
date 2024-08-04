package com.desafio.service;

import com.desafio.view.ContaDTO;
import com.desafio.model.Conta;

import java.util.Optional;

public interface ContaService {
    Optional<Conta> criarConta (long idCliente, ContaDTO contaDTO);

    Optional<Conta> buscarConta (long idConta);
}
