# Sistema de Cadastro de Produtos

![Angular](https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white)
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## 📄 Sobre o Projeto

Este é um projeto Full Stack para um sistema de cadastro de produtos. A aplicação consiste em:

* **Backend:** Uma API RESTful construída com Java 17 e Spring Boot, responsável por todas as regras de negócio e persistência dos dados.
* **Frontend:** Uma interface de usuário reativa e moderna, desenvolvida com Angular, para interagir com a API e realizar o gerenciamento dos produtos.

O sistema permite realizar as operações CRUD (Create, Read, Update, Delete) para os produtos cadastrados.

## 🗂️ Estrutura do Projeto

O repositório está organizado em duas pastas principais, uma para o backend e outra para o frontend:

├── backend/     
└── frontend/     

É necessário executar ambas as aplicações para que o sistema funcione completamente.

## 🛠️ Tecnologias Utilizadas

### Backend

* **[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html):** Versão LTS da linguagem Java.
* **[Spring Boot 3](https://spring.io/projects/spring-boot):** Framework para criação da API REST.
* **Spring Web:** Módulo para endpoints web.
* **Spring Data JPA:** Camada de abstração para persistência de dados.
* **[PostgreSQL](https://www.postgresql.org/):** Banco de dados relacional para armazenamento dos produtos.
* **[Maven](https://maven.apache.org/):** Gerenciador de dependências e build do projeto.

### Frontend

* **[Angular 17](https://angular.io/):** Framework para a construção da interface do usuário.
* **[TypeScript](https://www.typescriptlang.org/):** Superset do JavaScript utilizado pelo Angular.
* **[Node.js](https://nodejs.org/):** Ambiente de execução para o frontend.

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e executar o ambiente de desenvolvimento local.

### Pré-requisitos

* [Java 17 (JDK)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven 3.8+](https://maven.apache.org/download.cgi)
* [PostgreSQL](https://www.postgresql.org/download/) (é necessário ter uma instância ativa)
* [Node.js e npm](https://nodejs.org/en/download/)
* [Angular CLI](https://angular.io/cli): `npm install -g @angular/cli`

---

### 1. Configuração do Banco de Dados

Antes de iniciar o backend, você precisa ter um banco de dados PostgreSQL criado.

1.  Acesse seu servidor PostgreSQL.
2.  Crie um novo banco de dados. Ex: `CREATE DATABASE produtos_db;`
3.  Anote o host, porta, nome do banco, usuário e senha. Você precisará dessas informações a seguir.

---

### 2. Executando o Backend (API)

1.  **Navegue até a pasta do backend:**
    ```bash
    cd backend
    ```

2.  **Configure a conexão com o banco de dados:**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com os dados da sua instância do PostgreSQL.

    **Exemplo:**
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/produtos_db
    spring.datasource.username=seu_usuario_postgres
    spring.datasource.password=sua_senha_postgres

    # Hibernate irá criar/atualizar as tabelas automaticamente
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Compile e execute a aplicação Spring Boot:**
    Use o Maven para iniciar o servidor.
    ```bash
    mvn spring-boot:run
    ```
    **Pronto!** O backend estará em execução, por padrão, na porta `8081`.

---

### 3. Executando o Frontend (Angular)

1.  **Abra um novo terminal.**
2.  **Navegue até a pasta do frontend:**
    ```bash
    cd frontend
    ```
3.  **Instale as dependências do projeto:**
    ```bash
    npm install
    ```
4.  **Execute a aplicação Angular:**
    ```bash
    ng serve
    ```
    **Pronto!** A interface do usuário estará acessível no seu navegador através do endereço:
    ➡️ **[http://localhost:4200/](http://localhost:4200/)**

A aplicação Angular já deve estar configurada para fazer requisições para o backend na `http://localhost:8081`.

## 📖 Endpoints da API

O backend expõe os seguintes endpoints para o gerenciamento de produtos. A URL base é `http://localhost:8081/api/produtos`.

| Método HTTP | Endpoint | Descrição | Exemplo de Body (Request) |
| :--- | :--- | :--- | :--- |
| `POST` | `/` | Cria um novo produto. | `{"nome": "Notebook Gamer", "preco": 7500.00, "quantidade": 15}` |
| `GET` | `/` | Lista todos os produtos. | N/A |
| `PUT` | `/{id}` | Atualiza um produto existente. | `{"nome": "Notebook Ultra Pro", "preco": 8200.50, "quantidade": 10}` |
| `DELETE` | `/{id}` | Deleta um produto pelo seu ID. | N/A |

*Nota: Os campos no body do request (`nome`, `preco`, `quantidade`) são exemplos e devem corresponder ao modelo de dados (`Product`) definido na sua aplicação Java.*

## ✒️ Autor

* **[Victor Oliveira]**
* **Linkedin:** [@VictorOliveira28](https://www.linkedin.com/in/victoroliveira28/)
