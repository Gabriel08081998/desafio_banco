package com.desafio.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacoesDTO {
    @NotNull
    private int agencia;
    @NotNull
    private String cpf;
    @NotNull
    private int numero;
    @NotNull
    private double valor;
    private LocalDateTime dataTransacao;
}
