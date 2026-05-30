package com.moura.financeiro.controller;

import com.moura.financeiro.domain.Lancamento;
import com.moura.financeiro.dto.DadosAtualizacaoLancamento;
import com.moura.financeiro.dto.DadosCadastroLancamento;
import com.moura.financeiro.dto.DadosDetalhamentoLancamento;
import com.moura.financeiro.dto.DadosListagemLancamento;
import com.moura.financeiro.repository.CategoriaRepository;
import com.moura.financeiro.repository.LancamentoRepository;
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
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoRepository lancamentoRepository;
    private final CategoriaRepository categoriaRepository;
    private final PessoaRepository pessoaRepository;

    public LancamentoController(LancamentoRepository lancamentoRepository, CategoriaRepository categoriaRepository, PessoaRepository pessoaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.categoriaRepository = categoriaRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoLancamento> cadastrar(@RequestBody @Valid DadosCadastroLancamento dados, UriComponentsBuilder uriBuilder) {
        var categoria = categoriaRepository.getReferenceById(dados.categoria().codigo());
        var pessoa = pessoaRepository.getReferenceById(dados.pessoa().codigo());
        var lancamento = lancamentoRepository.save(new Lancamento(dados, categoria, pessoa));
        var uri = uriBuilder.path("/lancamentos/{codigo}").buildAndExpand(lancamento.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoLancamento(lancamento));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemLancamento>> listar(@PageableDefault(size = 10, sort = "dataVencimento") Pageable pageable) {
        var page = lancamentoRepository.findAll(pageable).map(DadosListagemLancamento::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DadosDetalhamentoLancamento> detalhar(@PathVariable Long codigo) {
        var lancamento = lancamentoRepository.getReferenceById(codigo);
        return ResponseEntity.ok(new DadosDetalhamentoLancamento(lancamento));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoLancamento> atualizar(@RequestBody @Valid DadosAtualizacaoLancamento dados) {
        var lancamento = lancamentoRepository.getReferenceById(dados.codigo());
        var categoria = dados.categoria() == null ? null : categoriaRepository.getReferenceById(dados.categoria().codigo());
        var pessoa = dados.pessoa() == null ? null : pessoaRepository.getReferenceById(dados.pessoa().codigo());
        lancamento.atualizar(dados, categoria, pessoa);

        return ResponseEntity.ok(new DadosDetalhamentoLancamento(lancamento));
    }

    @DeleteMapping("/{codigo}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
        lancamentoRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
