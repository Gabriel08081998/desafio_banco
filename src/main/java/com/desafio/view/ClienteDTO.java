package com.desafio.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private long id;
    @NotNull
    private String nome;
    @NotNull
    private String cpf;
    @NotNull
    private String senha;
}
