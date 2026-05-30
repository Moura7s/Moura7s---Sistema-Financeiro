package com.moura.financeiro.dto;

import com.moura.financeiro.domain.Pessoa;

public record DadosListagemPessoa(
        Long codigo,
        String nome,
        String cidade,
        String estado,
        Boolean ativo
) {
    public DadosListagemPessoa(Pessoa pessoa) {
        this(
                pessoa.getCodigo(),
                pessoa.getNome(),
                pessoa.getEndereco().getCidade(),
                pessoa.getEndereco().getEstado(),
                pessoa.getAtivo()
        );
    }
}
