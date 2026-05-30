package com.moura.financeiro.dto;

import com.moura.financeiro.domain.TipoLancamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoLancamento(
        @NotNull
        Long codigo,

        String descricao,

        LocalDate dataVencimento,

        LocalDate dataPagamento,

        @DecimalMin(value = "0.01")
        BigDecimal valor,

        String observacao,

        TipoLancamento tipo,

        @Valid
        DadosCodigoCategoria categoria,

        @Valid
        DadosCodigoPessoa pessoa
) {
}
