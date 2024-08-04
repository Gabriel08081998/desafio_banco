package com.desafio.repository;

import com.desafio.model.Conta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, Long> {
    Optional<Conta> findByCpf(String cpf);
}
