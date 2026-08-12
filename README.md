# Bookstore Manager API 📚

Este projeto é uma **API REST** para o gerenciamento de uma livraria, desenvolvida em **Java 21** com o framework **Spring Boot 4.0.1**.  
A aplicação permite o **registo, atualizar e consulta de livros**, bem como o gerenciamento dos seus respetivos **autores**, utilizando uma arquitetura moderna, mapeamento de dados eficiente e um sistema de tratamento de erros especializado.

🌐 **API em Produção / Documentação Live:**  
👉 [https://bookstore-manager-be2d.onrender.com/swagger-ui/index.html](https://bookstore-manager-be2d.onrender.com/swagger-ui/index.html)
---

## 🚀 Tecnologias e Ferramentas

- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database** (em memória)
- **MapStruct 1.5.5.Final**
- **Lombok**
- **JUnit 5**
- **Mockito**
- **Java Faker**
- **Docker**

---

## 🏗️ Arquitetura do Projeto

A aplicação segue uma **arquitetura em camadas**, garantindo separação de responsabilidades, manutenibilidade e escalabilidade:

- **Controller**
    - Exposição dos endpoints REST
    - Validação de requests
- **Service**
    - Regras de negócio
    - Comunicação entre controller e repositório
- **Repository**
    - Persistência de dados via Spring Data JPA
- **DTO (Data Transfer Object)**
    - Abstração das entidades
    - Proteção da camada de domínio
- **Entity**
    - Representação das tabelas `Book` e `Author`
- **Mapper**
    - Conversão entre Entidades e DTOs usando **MapStruct**
- **Exception Handler**
    - Tratamento global de exceções
    - Respostas padronizadas para erros da API

---

## 📦 Estrutura de Pastas

```bash
src/main/java/com/example/bookstore
│
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
└── service
```

---

## 🛣️ Endpoints da API

### 🔹 Criar um Novo Livro
**POST** `/api/v1/books`

#### Request Body
```json
{
  "name": "Spring Boot Practical Guide",
  "pages": 350,
  "chapters": 12,
  "isbn": "0-596-52068-9",
  "publisherName": "Tech Press",
  "author": {
    "name": "Thiago Prioto",
    "age": 28
  }
}
```

---

## 🧪 Testes

Execução dos testes:
```bash
mvn test
```

---

## 🐳 Docker

```bash
docker build -t bookstore-manager .
docker run -p 8080:8080 bookstore-manager
```

---

## ▶️ Como Executar o Projeto

```bash
git clone https://github.com/Thiagoprioto/bookstore_manager.git
cd bookstore_manager
mvn spring-boot:run
```

---

## 📌 Autor

Desenvolvido por **Thiago Prioto** 🚀

---

## 📄 Licença

MIT
