package com.desafio.repository;

import com.desafio.model.Transacoes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransacoesRepository extends CrudRepository<Transacoes, Long> {

    List<Transacoes> findByCpf(String cpf);
}
