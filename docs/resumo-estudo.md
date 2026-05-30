# Resumo para estudar

## API REST

Uma API REST recebe requisicoes HTTP e devolve respostas, geralmente em JSON. No projeto, os controllers recebem chamadas como `POST /pessoas`, `GET /lancamentos`, `PUT /pessoas` e `DELETE /pessoas/{codigo}`.

## Controller

Controller e a camada que conversa com quem esta usando a API. As anotacoes principais sao:

- `@RestController`: diz que a classe responde requisicoes REST.
- `@RequestMapping`: define o caminho base, como `/pessoas`.
- `@PostMapping`, `@GetMapping`, `@PutMapping`, `@DeleteMapping`: definem o verbo HTTP.
- `@RequestBody`: pega o JSON enviado no corpo da requisicao.
- `@PathVariable`: pega valores da URL, como o codigo em `/pessoas/1`.

## DTO com record

DTO e uma classe usada para entrada ou saida de dados. Ela evita expor a entidade inteira diretamente. No projeto existem DTOs de cadastro, atualizacao, listagem e detalhamento.

Exemplo: `DadosCadastroPessoa` recebe o JSON de cadastro. `DadosDetalhamentoPessoa` devolve a resposta formatada.

## Bean Validation

Bean Validation valida os dados antes de salvar. Exemplos:

- `@NotBlank`: texto obrigatorio, nao aceita vazio.
- `@NotNull`: valor obrigatorio.
- `@Valid`: manda validar objetos internos, como `endereco`.
- `@DecimalMin("0.01")`: valor precisa ser maior ou igual ao minimo.

Quando a validacao falha, o Spring gera erro 400.

## JPA e Entity

JPA mapeia classes Java para tabelas do banco.

- `@Entity`: transforma a classe em entidade do banco.
- `@Table`: informa o nome da tabela.
- `@Id`: define a chave primaria.
- `@GeneratedValue`: deixa o banco gerar o codigo automaticamente.
- `@Embedded`: inclui os campos de uma classe dentro da tabela principal.
- `@Enumerated(EnumType.STRING)`: salva enum como texto, por exemplo `RECEITA`.

## Repository

Repository e a camada que acessa o banco. Ao estender `JpaRepository<Pessoa, Long>`, o Spring ja entrega metodos como:

- `save()`: salvar.
- `findAll()`: listar.
- `getReferenceById()`: buscar por id.
- `deleteById()`: remover.

## Relacionamentos

No projeto, `Lancamento` tem `@ManyToOne` com `Categoria` e `Pessoa`. Isso significa que muitos lancamentos podem pertencer a uma categoria e a uma pessoa.

`@JoinColumn(name = "codigo_categoria")` informa qual coluna sera a chave estrangeira.

## Flyway

Flyway controla as migrations do banco. Os arquivos ficam em `src/main/resources/db/migration`.

- `V1__cria_tabelas.sql`: cria tabelas.
- `V2__dados_iniciais.sql`: insere dados iniciais.

## Pageable

`Pageable` permite paginacao e ordenacao. Exemplo:

```text
GET /pessoas?page=0&size=5&sort=nome,asc
```

O retorno vem dentro de um `Page`, com dados e informacoes da pagina.

## ResponseEntity

`ResponseEntity` permite controlar status HTTP e corpo da resposta.

- `ResponseEntity.ok(...)`: 200 OK.
- `ResponseEntity.created(uri).body(...)`: 201 Created, usado no cadastro.
- `ResponseEntity.noContent().build()`: 204 No Content, usado no DELETE.
- `ResponseEntity.notFound().build()`: 404 Not Found.
- `ResponseEntity.badRequest().body(...)`: 400 Bad Request.

## Tratamento de erros

`@RestControllerAdvice` centraliza erros da API. No projeto:

- `EntityNotFoundException` vira 404.
- `MethodArgumentNotValidException` vira 400 com campo e mensagem.
