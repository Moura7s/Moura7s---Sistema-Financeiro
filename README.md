# Financeiro API

Projeto de API REST em Spring Boot para um sistema financeiro simples, seguindo os assuntos das aulas: POST, GET, JPA, Repository, Flyway, Bean Validation, paginacao, ordenacao, PUT, DELETE, `ResponseEntity`, codigo `201 Created`, detalhamento e tratamento de erros.

## Como rodar

1. Crie um banco MySQL local ou deixe o Flyway criar automaticamente:

```sql
CREATE DATABASE financeiro_api;
```

2. Confira usuario e senha em `src/main/resources/application.properties`.

3. Rode a API:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Para testar sem MySQL, use o perfil H2:

```bash
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=h2
```

## Rotas principais

| Metodo | URL | Funcao |
| --- | --- | --- |
| POST | `/categorias` | Cadastra categoria |
| GET | `/categorias` | Lista categorias com paginacao |
| GET | `/categorias/{codigo}` | Detalha categoria |
| POST | `/pessoas` | Cadastra pessoa |
| GET | `/pessoas` | Lista pessoas ativas com paginacao |
| GET | `/pessoas/{codigo}` | Detalha pessoa |
| PUT | `/pessoas` | Atualiza pessoa |
| DELETE | `/pessoas/{codigo}` | Faz exclusao logica da pessoa |
| POST | `/lancamentos` | Cadastra lancamento |
| GET | `/lancamentos` | Lista lancamentos com paginacao |
| GET | `/lancamentos/{codigo}` | Detalha lancamento |
| PUT | `/lancamentos` | Atualiza lancamento |
| DELETE | `/lancamentos/{codigo}` | Remove lancamento |

## Exemplos JSON

Cadastrar categoria:

```json
{
  "nome": "Imposto"
}
```

Cadastrar pessoa:

```json
{
  "nome": "Ze Maria",
  "endereco": {
    "logradouro": "Rua das Silvas",
    "numero": "25",
    "bairro": "Valentina",
    "cep": "58028-30",
    "cidade": "Joao Pessoa",
    "estado": "PB"
  },
  "ativo": true
}
```

Cadastrar lancamento:

```json
{
  "descricao": "Faculdade",
  "dataVencimento": "2026-06-10",
  "valor": 500,
  "tipo": "DESPESA",
  "categoria": {
    "codigo": 5
  },
  "pessoa": {
    "codigo": 1
  }
}
```

Listagem paginada e ordenada:

```text
GET /lancamentos?page=0&size=5&sort=valor,desc
```
