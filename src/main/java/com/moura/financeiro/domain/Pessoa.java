package com.moura.financeiro.domain;

import com.moura.financeiro.dto.DadosAtualizacaoPessoa;
import com.moura.financeiro.dto.DadosCadastroPessoa;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Pessoa() {
    }

    public Pessoa(DadosCadastroPessoa dados) {
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = dados.ativo() == null || dados.ativo();
    }

    public void atualizar(DadosAtualizacaoPessoa dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.ativo() != null) {
            this.ativo = dados.ativo();
        }
        if (this.endereco == null && dados.endereco() != null) {
            this.endereco = new Endereco(dados.endereco());
        } else if (this.endereco != null) {
            this.endereco.atualizar(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
