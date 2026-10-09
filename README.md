# Reserva Segura — Backend

API REST do aplicativo gamificado de educação financeira que suporta as funcionalidades de autenticação, caixinhas, missões, estatísticas e movimentações.

---

## 👥 Equipe — Squad 29

**Código/Sigla:** RS · **Nome do Projeto:** RESERVA SEGURA

| Nome | Papel |
|------|-------|
| Agnes Letícia Soares Ribeiro | Front-End |
| Allan Harrison Lemos de Barros Falcão | Front-End |
| Ana Carolina da Silva Santos | Dados |
| Arthur Henrique Silveira de Paula | Design |
| Edmael Paulo Ribeiro Barreto | Back-End / QA |
| Gabriel Gleydson Lima dos Santos | Dados / Gestão |

**Programa:** Residência Tecnológica Take Off · Bradesco 2026.1

---

## 🔗 Links do Projeto

| Recurso | Link |
|---------|------|
| 🌐 Aplicação em Produção | [projeto-reserva-segura-front-end.vercel.app](https://projeto-reserva-segura-front-end.vercel.app/) |
| 🎨 Repositório Front-End | [Projeto-Reserva-Segura_Take-Off](https://github.com/AllanLemosFalcao/projeto-reserva-segura-frontend) |
| ⚙️ Repositório Back-End + Script Banco de dados MySQL | [ProjetoReservaSegura_Back-End + Banco de dados](https://github.com/AllanLemosFalcao/ProjetoReservaSegura_Back-End-main-End) |
| 🛡️ Repositório QA | [Reserva-Segura-QA](https://github.com/EdmaelBarretto/Reserva-Segura-QA) |

---

## 🦖 Sobre o Projeto

O backend do **Reserva Segura** é uma API desenvolvida em Spring Boot que fornece todos os endpoints necessários para o funcionamento do aplicativo, incluindo autenticação JWT, gerenciamento de metas de economia, missões diárias e estatísticas de gamificação.

---

## ✅ Funcionalidades

- 🔐 **Autenticação**: Registro e login com JWT
- 💰 **Caixinhas (Metas)**: Criação e gerenciamento de objetivos de economia
- 💸 **Movimentações**: Depósitos e saques nas caixinhas
- 🎯 **Missões**: Listagem de missões por usuário
- 📊 **Estatísticas**: XP, nível, streak e conquistas
- 👤 **Perfil**: Atualização de dados e senha

---

## 🛠️ Tecnologias

- **Java 17**
- **Spring Boot 3**
- **Spring Security** com JWT
- **Spring Data JPA** com Hibernate
- **MySQL** como banco de dados relacional
- **Maven** como gerenciador de dependências
- **Lombok** para redução de boilerplate
- **Railway** para deploy em produção

---

## 📋 Endpoints Principais

### Auth
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/auth/register` | Cadastro de usuário |
| POST | `/auth/login` | Login e geração de token JWT |
| GET | `/auth/usuario/{id}` | Buscar usuário por ID |
| PATCH | `/auth/usuario/{id}` | Atualizar perfil |
| POST | `/auth/usuario/{id}/senha` | Alterar senha |

### Metas (Caixinhas)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/metas?userId={id}` | Listar metas do usuário |
| POST | `/metas` | Criar nova meta |
| GET | `/metas/{id}` | Buscar meta por ID |

### Movimentações
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/movimentacoes` | Registrar depósito ou saque |
| GET | `/movimentacoes?userId={id}` | Listar movimentações |

### Missões
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/missoes/{userId}` | Listar missões do usuário |

### Estatísticas
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/estatisticas/{userId}` | Buscar estatísticas do usuário |

---

## 📦 Como Rodar Localmente

### 1. Pré-requisitos

- [Java 17](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (recomendado)
- **MySQL local** instalado (ou via XAMPP / MySQL Workbench)

### 2. Clonar o Repositório

```bash
git clone [https://github.com/AllanLemosFalcao/ProjetoReservaSegura_Back-End-main.git](https://github.com/AllanLemosFalcao/ProjetoReservaSegura_Back-End-main.git)
cd ProjetoReservaSegura_Back-End-main
```

### 3. Configurar a Base de Dados

Edite o ficheiro `src/main/resources/application.properties` com as credenciais do seu ambiente local:

```properties
# Configuração da Base de Dados MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/reserva_segura?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=sua_senha_aqui

# Driver do MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configurações do Hibernate/JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Configuração da Porta da API
server.port=8080

# 🔐 Configurações do JWT
jwt.secret=sua_chave_secreta_super_segura_aqui
jwt.expiration=86400000

*(Lembre-se de criar o schema `reserva_segura` no seu MySQL antes de rodar a aplicação)*

### 4. Rodar o Projeto

No IntelliJ, clique em **▶️ Run** na classe `ReservaSeguraApplication.java`.

Ou via terminal:

```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

> ### 🔑 Perfis de Teste para Login
> Utilize as credenciais abaixo para acessar o sistema localmente com dados já preenchidos:
> 
> | Perfil | E-mail | Senha |
> | :--- | :--- | :--- |
> | 🧑‍💻 **Allan** | `allan@email.com` | `123456` |
> | 👩‍💻 **Maria** | `maria@email.com` | `123456` |
> | 👨‍💻 **João**  | `joao@email.com` | `123456` |
---

## 📁 Estrutura do Projeto

```text
reservasegura/
├── src/main/java/br/com/reservasegura/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── repository/
│   ├── security/
│   └── service/
└── src/main/resources/
    └── application.properties
```

---

## 🚀 Deploy no Railway

### Variáveis de Ambiente necessárias

| Variável | Descrição |
|----------|-----------|
| `SPRING_DATASOURCE_URL` | URL JDBC do banco MySQL |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco |
| `JWT_SECRET` | Chave secreta para geração de tokens |
| `FRONTEND_URL` | URL do frontend na Vercel |

### URL de Produção

`https://projetoreservaseguraback-end-production.up.railway.app`

---

## 🔒 Segurança

- Autenticação via **JWT Bearer Token**
- Endpoints públicos: `/auth/register` e `/auth/login`
- Todos os demais endpoints exigem token válido no header:
  `Authorization: Bearer <token>`

---

## 🗄️ Banco de Dados

| Tabela | Descrição |
|--------|-----------|
| `usuario` | Dados dos usuários |
| `meta_reserva` | Caixinhas/metas de economia |
| `movimentacoes` | Depósitos e saques |
| `missao` | Missões disponíveis |
| `progresso_missao` | Progresso das missões por usuário |

---

## 📝 Licença

Este projeto é de código aberto para fins educacionais.

---

Desenvolvido usando Java + Spring Boot · Squad 29 · Bradesco 2026.1
