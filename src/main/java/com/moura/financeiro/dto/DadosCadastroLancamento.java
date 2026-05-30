package com.moura.financeiro.dto;

import com.moura.financeiro.domain.TipoLancamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosCadastroLancamento(
        @NotBlank
        String descricao,

        @NotNull
        LocalDate dataVencimento,

        LocalDate dataPagamento,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal valor,

        String observacao,

        @NotNull
        TipoLancamento tipo,

        @NotNull
        @Valid
        DadosCodigoCategoria categoria,

        @NotNull
        @Valid
        DadosCodigoPessoa pessoa
) {
}
