package com.moura.financeiro.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPessoa(
        @NotBlank
        String nome,

        @NotNull
        @Valid
        DadosEndereco endereco,

        Boolean ativo
) {
}
