package com.desafio.repository;

import com.desafio.model.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
    Optional<Cliente> findByCpf(String cpf);
}
