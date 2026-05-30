package com.moura.financeiro.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosEndereco(
        @NotBlank
        String logradouro,

        @NotBlank
        String numero,

        String complemento,

        @NotBlank
        String bairro,

        @NotBlank
        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        String estado
) {
}
