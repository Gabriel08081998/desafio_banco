package com.desafio.view;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ContaDTO {

    @NotNull
    private String cpf;
    @NotNull
    private int agencia;
    @NotNull
    private int numero;
    @NotNull
    private String tipoConta;
    @NotNull
    protected double saldo;

}
