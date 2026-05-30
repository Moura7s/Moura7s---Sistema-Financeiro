package com.moura.financeiro.dto;

import com.moura.financeiro.domain.Pessoa;

public record DadosDetalhamentoPessoa(
        Long codigo,
        String nome,
        DadosDetalhamentoEndereco endereco,
        Boolean ativo
) {
    public DadosDetalhamentoPessoa(Pessoa pessoa) {
        this(
                pessoa.getCodigo(),
                pessoa.getNome(),
                new DadosDetalhamentoEndereco(pessoa.getEndereco()),
                pessoa.getAtivo()
        );
    }
}
