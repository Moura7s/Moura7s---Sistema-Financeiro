# Perguntas subjetivas provaveis

## 1. Explique o fluxo de cadastro de uma pessoa.

Resposta: A requisicao chega no `PessoaController` pelo metodo `POST /pessoas`. O JSON e convertido para `DadosCadastroPessoa`, que e validado com Bean Validation por causa do `@Valid`. Depois o controller cria uma entidade `Pessoa`, chama `repository.save(pessoa)` e devolve `201 Created` com `ResponseEntity.created(...)`.

## 2. Por que usar DTO em vez de receber a entidade direto?

Resposta: O DTO separa o formato da API do formato do banco. Assim eu controlo quais campos entram e saem, aplico validacoes especificas e evito expor detalhes internos da entidade.

## 3. O que e Repository?

Resposta: Repository e a interface responsavel pelo acesso ao banco. Como ela estende `JpaRepository`, o Spring cria automaticamente metodos de CRUD, como `save`, `findAll`, `getReferenceById` e `deleteById`.

## 4. Como funciona o relacionamento entre lancamento, pessoa e categoria?

Resposta: Em `Lancamento`, existem dois relacionamentos `@ManyToOne`: um para `Categoria` e outro para `Pessoa`. Isso quer dizer que varios lancamentos podem estar ligados a uma mesma pessoa e a uma mesma categoria. No banco, isso vira chave estrangeira nas colunas `codigo_categoria` e `codigo_pessoa`.

## 5. Por que o DELETE de pessoa e chamado de exclusao logica?

Resposta: Porque a pessoa nao e apagada fisicamente do banco. O metodo `excluir()` apenas muda o campo `ativo` para `false`. Depois, a listagem usa `findAllByAtivoTrue`, mostrando somente pessoas ativas.

## 6. O que acontece quando envio dados invalidos?

Resposta: O `@Valid` aciona as validacoes dos DTOs. Se algum campo obrigatorio estiver vazio ou nulo, o Spring gera uma `MethodArgumentNotValidException`. O `TratadorDeErros` captura essa excecao e devolve erro 400 com a lista de campos invalidos.

## 7. O que e Flyway?

Resposta: Flyway e a ferramenta que versiona o banco. Quando a aplicacao inicia, ela executa os arquivos SQL de migration na ordem, como `V1__cria_tabelas.sql` e `V2__dados_iniciais.sql`.

## 8. Qual a diferenca entre POST, GET, PUT e DELETE?

Resposta: `POST` cria um novo registro, `GET` consulta dados, `PUT` atualiza dados existentes e `DELETE` remove ou desativa um registro. No projeto, esses verbos aparecem nos controllers.

## 9. Por que o cadastro retorna 201?

Resposta: O codigo 201 significa `Created`, ou seja, um novo recurso foi criado. A API tambem devolve a URI do novo recurso no cabecalho `Location`, montada com `UriComponentsBuilder`.

## 10. Como funciona paginacao e ordenacao?

Resposta: O controller recebe um `Pageable`, e o repository usa esse objeto no `findAll`. A URL pode mandar `page`, `size` e `sort`, como `/lancamentos?page=0&size=5&sort=valor,desc`.
