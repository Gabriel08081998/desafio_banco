package com.desafio.controller;

import com.desafio.model.Transacoes;
import com.desafio.service.TransacoesService;
import com.desafio.view.TransacoesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transacoa")
public class TransacoesController {
    @Autowired
    private TransacoesService transacoesServise;

    @PostMapping("/{idConta}")
    public ResponseEntity<?> trasferir(@PathVariable("idConta") long idConta, @RequestBody @Valid TransacoesDTO transacoesDTO) {
        try {
            Optional<Transacoes> optionalTransacao = transacoesServise.trasferir(idConta,transacoesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalTransacao.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<Transacoes>> buscarTransacoesPorCliente(@PathVariable("cpf") String cpf) {
        List<Transacoes> transacoes = transacoesServise.buscarTransacoesPorCliente(cpf);
        return ResponseEntity.ok(transacoes);
    }

}
