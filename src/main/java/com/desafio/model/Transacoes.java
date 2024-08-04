package com.desafio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagador;

    @ManyToOne
    @JoinColumn(name = "id_Conta_remetente")
    private Conta contaRemetente;



    private LocalDateTime dataTransacao;

    private String nomeDestinatario;
    private int agencia;
    private String cpf;
    private int numero;
    private double valor;


}
