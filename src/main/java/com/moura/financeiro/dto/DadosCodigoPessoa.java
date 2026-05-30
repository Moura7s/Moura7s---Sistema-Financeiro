package com.moura.financeiro.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCodigoPessoa(
        @NotNull
        Long codigo
) {
}
