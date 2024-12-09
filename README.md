# **Auth API - Sistema de Gerenciamento de Usuários, Pedidos e Produtos**

## 📖 **Descrição do Projeto**

A **Auth API** é uma aplicação desenvolvida em **Java com Spring Boot**, criada para gerenciar **usuários**, **pedidos** e **produtos** de forma eficiente e segura. Com uma estrutura modular e clara, a aplicação oferece **CRUD completo**, autenticação e validação robusta, sendo ideal para aplicações empresariais ou educacionais.

---

## 🚀 **Funcionalidades**

### **Usuários**
- Cadastro, consulta, edição e exclusão de usuários.
- Autenticação segura com hash de senhas.
- Endpoints para listagem e busca de usuários.

### **Pedidos**
- Registro de pedidos associados a usuários cadastrados.
- Gerenciamento completo com funcionalidades de CRUD.
- Listagem de pedidos por usuário.

### **Produtos**
- Cadastro e controle de produtos vinculados aos pedidos.
- Validações de preço e nome do produto.
- Busca de produtos por ID do pedido.

---

## ⚙️ **Tecnologias Utilizadas**

- **Java 17**
- **Spring Boot Framework**:
  - Spring Security
  - Spring Data JPA
  - Spring Web
- **Banco de Dados Relacional**:
  - **SQL Server** configurado com Spring Data JPA.
- **Maven**: Gerenciamento de dependências.
- **JPA/Hibernate**: ORM para persistência de dados.

---

## 📂 **Estrutura do Projeto**

A organização do código segue uma arquitetura modular para maior legibilidade e manutenção:

```plaintext
com.project2.auth_api
├── config/              # Configurações gerais da aplicação
│   └── SecurityConfig   # Configuração de autenticação e segurança
├── controller/          # Controladores responsáveis pelos endpoints REST
│   ├── PedidoController
│   ├── ProdutoController
│   └── UserController
├── dto/                 # Objetos de Transferência de Dados (DTO)
│   ├── PedidoDTO
│   ├── ProdutoDTO
│   └── UserDTO
├── exception/           # Classes para tratamento de exceções
├── model/               # Representação das entidades do sistema
│   ├── Pedido
│   ├── Produto
│   └── User
├── repository/          # Interfaces JPA para acesso ao banco de dados
│   ├── PedidoRepository
│   ├── ProdutoRepository
│   └── UserRepository
├── service/             # Regras de negócio e lógica de aplicação
│   ├── PedidoService
│   ├── ProdutoService
│   └── UserService
└── AuthApiApplication   # Classe principal da aplicação
```
## 🧪 **Principais Endpoints**

### **Usuários**
- `GET /users`: Lista todos os usuários.
- `POST /users`: Cadastra um novo usuário.
- `GET /users/{id}`: Consulta um usuário pelo ID.
- `PUT /users/{id}`: Atualiza informações de um usuário.
- `DELETE /users/{id}`: Remove um usuário.

### **Pedidos**
- `GET /pedidos`: Lista todos os pedidos.
- `POST /pedidos`: Cria um novo pedido.
- `GET /pedidos/user/{userId}`: Lista pedidos de um usuário específico.
- `PUT /pedidos/{id}`: Atualiza um pedido.
- `DELETE /pedidos/{id}`: Remove um pedido.

### **Produtos**
- `GET /produtos`: Lista todos os produtos.
- `POST /produtos`: Cadastra um novo produto.
- `GET /produtos/pedido/{pedidoId}`: Lista produtos vinculados a um pedido.
- `PUT /produtos/{id}`: Atualiza um produto.
- `DELETE /produtos/{id}`: Remove um produto.
