package com.desafio.controller;

import com.desafio.model.Cliente;
import com.desafio.repository.ClienteRepository;
import com.desafio.util.UtilCriptografia;
import com.desafio.view.ClienteDTO;
import com.desafio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<?> criarCliente (@RequestBody @Valid ClienteDTO clienteDTO){
        try{
            // Chama o método hashPassword da classe User para criptografar a senha
            String encryptedPassword = UtilCriptografia.hashPassword(clienteDTO.getSenha());
            clienteDTO.setSenha(encryptedPassword);

            Optional<Cliente> optionalCliente = clienteService.criarCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalCliente.get());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}


