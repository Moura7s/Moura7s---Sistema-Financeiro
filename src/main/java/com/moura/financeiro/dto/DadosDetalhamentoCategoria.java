package com.moura.financeiro.dto;

import com.moura.financeiro.domain.Categoria;

public record DadosDetalhamentoCategoria(
        Long codigo,
        String nome
) {
    public DadosDetalhamentoCategoria(Categoria categoria) {
        this(categoria.getCodigo(), categoria.getNome());
    }
}
