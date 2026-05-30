package com.moura.financeiro.dto;

import com.moura.financeiro.domain.Lancamento;
import com.moura.financeiro.domain.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosListagemLancamento(
        Long codigo,
        String descricao,
        LocalDate dataVencimento,
        BigDecimal valor,
        TipoLancamento tipo,
        String categoria,
        String pessoa
) {
    public DadosListagemLancamento(Lancamento lancamento) {
        this(
                lancamento.getCodigo(),
                lancamento.getDescricao(),
                lancamento.getDataVencimento(),
                lancamento.getValor(),
                lancamento.getTipo(),
                lancamento.getCategoria().getNome(),
                lancamento.getPessoa().getNome()
        );
    }
}
