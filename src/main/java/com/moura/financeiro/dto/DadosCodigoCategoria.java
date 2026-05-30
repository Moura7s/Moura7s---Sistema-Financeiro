package com.moura.financeiro.dto;

import jakarta.validation.constraints.NotNull;

public record DadosCodigoCategoria(
        @NotNull
        Long codigo
) {
}
