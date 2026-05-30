package com.moura.financeiro.domain;

import com.moura.financeiro.dto.DadosCadastroCategoria;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    public Categoria() {
    }

    public Categoria(DadosCadastroCategoria dados) {
        this.nome = dados.nome();
    }

    public void atualizarNome(String nome) {
        if (nome != null) {
            this.nome = nome;
        }
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
