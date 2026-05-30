package com.moura.financeiro.controller;

import com.moura.financeiro.domain.Pessoa;
import com.moura.financeiro.dto.DadosAtualizacaoPessoa;
import com.moura.financeiro.dto.DadosCadastroPessoa;
import com.moura.financeiro.dto.DadosDetalhamentoPessoa;
import com.moura.financeiro.dto.DadosListagemPessoa;
import com.moura.financeiro.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPessoa> cadastrar(@RequestBody @Valid DadosCadastroPessoa dados, UriComponentsBuilder uriBuilder) {
        var pessoa = repository.save(new Pessoa(dados));
        var uri = uriBuilder.path("/pessoas/{codigo}").buildAndExpand(pessoa.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPessoa(pessoa));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPessoa>> listar(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(DadosListagemPessoa::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DadosDetalhamentoPessoa> detalhar(@PathVariable Long codigo) {
        var pessoa = repository.getReferenceById(codigo);
        return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPessoa> atualizar(@RequestBody @Valid DadosAtualizacaoPessoa dados) {
        var pessoa = repository.getReferenceById(dados.codigo());
        pessoa.atualizar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa));
    }

    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
        var pessoa = repository.getReferenceById(codigo);
        pessoa.excluir();

        return ResponseEntity.noContent().build();
    }
}
