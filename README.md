
# Processo Empresa Equilibrium Web  Teste Desenvolvedor  Backend Java

## Descrição do Projeto

Este projeto é uma aplicação desenvolvida em **Spring Boot** utilizando a arquitetura **MVC** com foco em microserviços. Ele gerencia processos e consome informações sobre tipos de processo de outro microserviço simulado pelo **Json Server**.

O projeto foi desenvolvido como parte do teste para a Equilibrium Web, abrangendo operações **CRUD**, integração de microserviços, uso de **Docker** e boas práticas de desenvolvimento.

# ApiProcesso

API desenvolvida em **Java Spring Boot** para gerenciar processos. O projeto segue a arquitetura **MVC** e está configurado para rodar localmente utilizando **PostgreSQL** como banco de dados.

---

## Descrição do Projeto

Este projeto é uma aplicação desenvolvida em **Spring Boot** utilizando a arquitetura **MVC** com foco em microserviços. Ele gerencia processos e consome informações sobre tipos de processo de outro microserviço simulado pelo **Json Server**.

O projeto foi desenvolvido como parte do teste para a **Equilibrium Web**, abrangendo operações **CRUD**, integração de microserviços, uso de **Docker** e boas práticas de desenvolvimento.

---

## Funcionalidades

### Processo Service

- **Cadastrar processos**
- **Editar processos**
- **Excluir processos**
- **Consultar processos** com paginação
- **Integração com cadastro\_service** para consultar os tipos de processo

### Cadastro Service (simulado com Json Server)

- Fornece dados sobre tipos de processo, como:
  - **id**: Identificador do tipo de processo
  - **nome**: Nome do tipo de processo

---

## Tecnologias Utilizadas

- **Java 17 ou superior**
- **Spring Boot 3+**
- **SpringData**
- **PostgreSQL**
- **Docker**
- **Swagger**

---

## Requisitos de Instalação

Antes de iniciar, certifique-se de ter:

- **JDK 17** instalado
- **Docker** e **Docker Compose** instalados
- **Git** instalado
- Um cliente para APIs REST (como Postman ou curl)

---

## Como Rodar o Projeto

### 1. Clonar o Repositório

Clone o projeto para sua máquina local usando o Git:

```bash
git clone <URL_DO_REPOSITORIO>
```

Acesse a pasta do projeto:

```bash
cd ApiProcesso
```

---

### 2. Configurar o Banco de Dados

Certifique-se de que o PostgreSQL esteja instalado e configurado na sua máquina.

Acesse o PostgreSQL via terminal ou uma ferramenta gráfica (como DBeaver ou pgAdmin):

```bash
psql -U postgres
```

Crie o banco de dados chamado `processo_db`:

```sql
CREATE DATABASE processo_db;
```
```sql
CREATE DATATABLE  processo;
```
```sql
CREATE DATATABLE  tipo_processo;
```



---

### 3. Configurar o `application.properties`

No diretório do projeto, localize o arquivo `src/main/resources/application.properties` e configure as credenciais de conexão com o PostgreSQL. Um exemplo de configuração seria:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/processo_db
spring.datasource.username=postgres
spring.datasource.password=123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
```

---

### 4. Executar o Projeto

Certifique-se de ter o Maven instalado e configurado.

No terminal, na raiz do projeto, compile e execute o projeto:

```bash
./mvnw spring-boot:run
```

A API estará disponível no endereço:

```bash
http://localhost:8080
```

---

### 5. Testar a API

Utilize ferramentas como Postman, Insomnia ou cURL para testar os endpoints.

Exemplo para listar os dados:

```bash
curl http://localhost:8080/api/processos
```

---

## Endpoints Principais

# Endpoints da API de Gestão de Processos Judiciais

## Processos

### GET /api/processo/{id}
- **Descrição**: Retorna um processo com base no ID fornecido.
- **Resposta**:
  - **200 OK**: Retorna o processo com os dados correspondentes.
  - **404 Not Found**: Se o processo não for encontrado.
  
### GET /api/processo/{numeroProcesso}
- **Descrição**: Retorna o processo baseado no número do processo.
- **Resposta**:
  - **200 OK**: Retorna o processo encontrado.
  - **404 Not Found**: Se o processo não for encontrado.

### POST /api/processo/salvar
- **Descrição**: Cria ou atualiza um processo com base nos dados fornecidos.
- **Corpo da Requisição**:
  - Exemplo:
    ```json
    {
      "dataEntrada": "2025-01-22",
      "valorRecurso": 1000.00,
      "objetivo": "teste 9",
      "tipoProcesso": {
        "id": 4
      }
    }
    ```
- **Resposta**:
  - **201 Created**: Processo criado ou atualizado com sucesso.
  - **400 Bad Request**: Se os dados fornecidos estiverem malformados.
  - **404 Not Found**: Se o tipo de processo fornecido não for encontrado.

### DELETE /api/processo//{id}
- **Descrição**: Remove um processo com base no ID fornecido.
- **Resposta**:
  - **200 OK**: Processo removido com sucesso.
  - **404 Not Found**: Se o processo não for encontrado.

### GET /api/processo/processos
- **Descrição**: Retorna uma lista de processos com paginação.
- **Parâmetros**:
  - `page`: Página da consulta.
  - `limit`: Número máximo de processos por página.
- **Resposta**:
  - **200 OK**: Retorna a lista de processos.

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# Requisitos de Instalação e Execução da API com Docker

Este guia fornece as instruções necessárias para baixar e executar sua API utilizando Docker. Siga os passos abaixo para configurar e acessar a API.

## Passo 1: Baixar a Imagem
Antes de começar, certifique-se de que o Docker está instalado e configurado em sua máquina. Para baixar a imagem da API, execute o seguinte comando no terminal:

```bash
docker pull eb123/ApiProcesso
```

## Passo 2: Executar a API
Após baixar a imagem, execute o seguinte comando para iniciar a API:

```bash
docker run -p 8080:8080 eb123/ApiProcesso
```

Esse comando irá iniciar a API e mapeá-la para a porta 8080 no host local. Certifique-se de que essa porta está disponível.

## Passo 3: Acessar os Endpoints da API
Com a API em execução, você pode começar a realizar requisições. Utilize uma ferramenta como o Postman para testar os endpoints disponíveis. Por exemplo:

```
http://localhost:8080/processo
```

## Passo 4: Acessar a Documentação no Swagger
O Swagger UI está disponível para facilitar o entendimento e o teste da API. Para acessar a documentação interativa, abra o seguinte endereço no navegador:

```
http://localhost:8080/swagger-ui/index.html
```

Certifique-se de que a API está em execução para acessar essa página.

---


Estrutura Da Api

![estrutura da api](https://github.com/user-attachments/assets/c49b5fac-61dd-4d69-a9e8-902c9aca54bc)


Esses passos garantem que você consiga baixar, executar e explorar a API facilmente. Caso tenha dúvidas ou problemas, consulte a documentação oficial do Docker ou entre em contato com o desenvolvedor responsável.

