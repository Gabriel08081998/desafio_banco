package com.desafio.service;

import com.desafio.model.Transacoes;
import com.desafio.view.TransacoesDTO;

import java.util.List;
import java.util.Optional;

public interface TransacoesService {
    Optional<Transacoes> trasferir(long idConta, TransacoesDTO transacoesDTO);

    List<Transacoes> buscarTransacoesPorCliente(String cpf);
}
