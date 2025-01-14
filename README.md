
# Processo Empresa Equilibrium Web  Teste Desenvolvedor  Backend Java


# ProcessoJudicialApi

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


# Processo com Docker Container - ProcessoJudiciaisApi

## Pré-requisitos  

Antes de começar, certifique-se de que você possui as seguintes ferramentas instaladas em sua máquina:  

- **Git**  
- **Docker**  
- **Docker Compose** (se necessário para múltiplos containers)  

## Como executar  

Siga os passos abaixo para clonar o repositório, configurar o ambiente e rodar a API:  

### 1. Clone o repositório  

```bash
git clone <URL-DO-SEU-REPOSITORIO>
cd ProcessoJudiciaisApi


2. Certifique-se de que o Docker está rodando
Verifique se o Docker está ativo na sua máquina. Caso contrário, inicie o Docker antes de prosseguir.

3. Build da imagem Docker
No diretório raiz do projeto, execute o comando abaixo para criar a imagem Docker da aplicação:

docker build -t processo-judiciais-api .

4. Execute o container
Para rodar a aplicação em um container, use o comando abaixo:

docker run -d -p 8080:8080 --name processo-judiciais-container processo-judiciais-api

5. Teste a API
Acesse a API pelo seu navegador ou por uma ferramenta como o Postman no endereço:

http://localhost:8080

