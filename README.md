# API REST - Central de Erro

Projeto prático e final do AceleraDev Java Online

## Objetivo

Em projetos modernos é cada vez mais comum o uso de arquiteturas baseadas em serviços ou microsserviços. Nestes ambientes complexos, erros podem surgir em diferentes camadas da aplicação (backend, frontend, mobile, desktop) e mesmo em serviços distintos. Desta forma, é muito importante que os desenvolvedores possam centralizar todos os registros de erros em um local, de onde podem monitorar e tomar decisões mais acertadas. Neste projeto vamos implementar uma API Rest para centralizar registros de erros de aplicações.
Utilizar tecnologia base da aceleração para o desenvolvimento. (Java)

## Começando
Segue abaixo os requisitos e instruções para rodar o projeto local.

### Pré-requisitos
É necessário ter instalado na máquina:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

### Instruções para execução do projeto
Segue abaixo o passo a passo para execução do projeto:

1. Clone o projeto
```
  git clone https://github.com/igorbatistadev/api-central-de-erros.git
```
2. Navegue para a pasta do projeto
```
  cd bureau-credit
```
3. Construa o projeto com o docker-compose
```
  docker-compose up --build
```

### Demo do Projeto
https://api-central-erros.herokuapp.com/

### Collection com request no postgres
1. Importe o 'API - Central de erros.postman_collection.json' no postman.

**Observação:** Caso esteja executando o projeto via docker o host 'localhost:8080' permanece, caso queira testar o demo do projeto que está no heroku altere de 'localhost:8080' para 'https://api-central-erros.herokuapp.com'

### Recursos da API

#### POST /api/v1/usuarios

Cadastrar um usuário

##### Request Body
```
{
  "email": "string",
  "nome": "string",
  "senha": "string"
}
```

#### POST /api/v1/oauth/token
Autenticar e buscar token

##### Parameters

grant_type (string) *

username (string) *

password (string) *

##### Headers

Authorization: Basic Y29kZW5hdGlvbjpjb2RlbmF0aW9u

##### Response

```
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTI3NjA4ODksInVzZXJfbmFtZSI6InNlZ3VyYW5jYUBlbWFpbC5jb20iLCJqdGkiOiI5ZDM0NmQ4YS1mNzhlLTRhNWEtYTZkOS1hNWE1YzE5YjdmM2QiLCJjbGllbnRfaWQiOiJjb2RlbmF0aW9uIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl19.uMdT9k1fFtHcBvSnJMAZBokW2fHvVQyjeGPiOcVgZaQ",
  "token_type": "bearer",
  "expires_in": 1799,
  "scope": "read write",
  "jti": "9d346d8a-f78e-4a5a-a6d9-a5a5c19b7f3d"
}
```
Exemplo URI: http://localhost:8080/api/v1/oauth/token?grant_type=password&username=usuario@email.com&password=senhausuario

### POST /api/v1/eventos
Cadastrar evento de erro

#### Body

##### Request Body
```
{
  "data": "dd-MM-yyyy",
  "descricao": "string",
  "level": "ERROR",
  "log": "string",
  "origem": "string",
  "quantidade": 0
}
```
#### Headers

Authorization - Bearer <access_token>


### GET /api/v1/eventos
Buscar lista de eventos

#### Parameters

size (number)

page (number)

sort (string)

data (string) **formato:** dd-MM-yyyy

descricao (string)

level (string) **valores aceitos:** ERROR, WARNING, INFO

log (string)

origem (string)

pageNumber (integer)

pageSize (integer)

quantidade (integer)

#### Headers
Authorization - Bearer <access_token>

#### Response
```
{
  "content": [
    {
      "data": "dd-MM-yyyy",
      "descricao": "string",
      "id": 0,
      "idUsuario": 0,
      "level": "ERROR",
      "origem": "string",
      "quantidade": 0
    }
  ],
  "empty": true,
  "first": true,
  "last": true,
  "number": 0,
  "numberOfElements": 0,
  "pageable": {
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 0,
    "paged": true,
    "sort": {
      "empty": true,
      "sorted": true,
      "unsorted": true
    },
    "unpaged": true
  },
  "size": 0,
  "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
  },
  "totalElements": 0,
  "totalPages": 0
}
```

Exemplo URI: http://localhost:8080/api/v1/eventos?size=5&page=1&level=WARNING


### GET /api/v1/eventos/{id}
Buscar evento por id

#### Headers
Authorization - Bearer <access_token>

#### Response
```
{
  "data": "dd-MM-yyyy",
  "descricao": "string",
  "id": 0,
  "idUsuario": 0,
  "level": "ERROR",
  "log": "string",
  "origem": "string",
  "quantidade": 0
}
```

Exemplo URI: http://localhost:8080/api/v1/eventos/6
