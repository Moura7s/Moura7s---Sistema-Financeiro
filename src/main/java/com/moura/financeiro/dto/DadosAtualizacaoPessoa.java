package com.moura.financeiro.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPessoa(
        @NotNull
        Long codigo,

        String nome,

        @Valid
        DadosEndereco endereco,

        Boolean ativo
) {
}
