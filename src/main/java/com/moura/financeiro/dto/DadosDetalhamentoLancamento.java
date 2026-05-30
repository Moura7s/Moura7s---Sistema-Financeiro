package com.moura.financeiro.dto;

import com.moura.financeiro.domain.Lancamento;
import com.moura.financeiro.domain.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoLancamento(
        Long codigo,
        String descricao,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        BigDecimal valor,
        String observacao,
        TipoLancamento tipo,
        DadosDetalhamentoCategoria categoria,
        DadosListagemPessoa pessoa
) {
    public DadosDetalhamentoLancamento(Lancamento lancamento) {
        this(
                lancamento.getCodigo(),
                lancamento.getDescricao(),
                lancamento.getDataVencimento(),
                lancamento.getDataPagamento(),
                lancamento.getValor(),
                lancamento.getObservacao(),
                lancamento.getTipo(),
                new DadosDetalhamentoCategoria(lancamento.getCategoria()),
                new DadosListagemPessoa(lancamento.getPessoa())
        );
    }
}
