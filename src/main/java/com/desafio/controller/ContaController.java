package com.desafio.controller;

import com.desafio.service.ContaService;
import com.desafio.view.ClienteException;
import com.desafio.view.ContaDTO;
import com.desafio.model.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/{idCliente}")
    public ResponseEntity<?> criarConta (@PathVariable("idCliente") Long idCliente, @RequestBody @Valid ContaDTO contaDTO) {
        try {
            Optional<Conta> optionalConta = contaService.criarConta(idCliente, contaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalConta.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{idConta}")
    public ResponseEntity<?> buscarConta(@PathVariable("idConta") long idConta) {
        try {
            Optional<Conta> optionalConta = contaService.buscarConta(idConta);
            return ResponseEntity.status(HttpStatus.OK).body(optionalConta.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}