package com.moura.financeiro.controller;

import com.moura.financeiro.domain.Categoria;
import com.moura.financeiro.dto.DadosCadastroCategoria;
import com.moura.financeiro.dto.DadosDetalhamentoCategoria;
import com.moura.financeiro.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCategoria> cadastrar(@RequestBody @Valid DadosCadastroCategoria dados, UriComponentsBuilder uriBuilder) {
        var categoria = repository.save(new Categoria(dados));
        var uri = uriBuilder.path("/categorias/{codigo}").buildAndExpand(categoria.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategoria(categoria));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCategoria>> listar(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        var page = repository.findAll(pageable).map(DadosDetalhamentoCategoria::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DadosDetalhamentoCategoria> detalhar(@PathVariable Long codigo) {
        var categoria = repository.getReferenceById(codigo);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }
}
