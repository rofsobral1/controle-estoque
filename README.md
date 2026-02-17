# 📦 Controle de Estoque - Sistema Fullstack

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3-green"/>
  <img src="https://img.shields.io/badge/Next.js-14-black"/>
  <img src="https://img.shields.io/badge/PostgreSQL-DB-blue"/>
  <img src="https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow"/>
</p>

---

## 🚀 Sobre o Projeto

Sistema **completo de controle de estoque**, desenvolvido com foco em:

✅ Escalabilidade  
✅ Segurança  
✅ Arquitetura limpa  
✅ Boas práticas de mercado  
✅ Performance  

O sistema permite gerenciar:

- 📦 Produtos  
- 👥 Usuários  
- 🔐 Autenticação JWT  
- 🔄 Recuperação de senha  
- 📊 Relatórios (em evolução)

Projeto criado como **portfólio profissional**, simulando uma aplicação real de mercado.

---

## 🛠️ Tecnologias Utilizadas

### Backend
- ☕ Java 21  
- 🌱 Spring Boot  
- 🔐 Spring Security + JWT  
- 🗄️ PostgreSQL  
- 📦 Maven  
- 📑 JPA / Hibernate  

### Frontend
- ⚛️ Next.js 14  
- 🎨 Tailwind CSS  
- 📱 Layout Responsivo  
- 🌐 Fetch API  

---

---

## ⚙️ Funcionalidades Principais

### 🔐 Autenticação & Segurança
- Login com JWT
- Cadastro de usuários
- Recuperação de senha por e-mail
- Rotas protegidas
- Criptografia com BCrypt

### 📦 Gestão de Produtos
- Cadastro de produtos
- Atualização de informações
- Exclusão
- Consulta paginada
- Controle de estoque em tempo real

### 👥 Gestão de Usuários
- Perfis de acesso
- Controle de permissões
- Ativação/Inativação de usuários

### 📊 Relatórios (Em Evolução)
- Movimentação de estoque
- Histórico de alterações
- Exportação futura (PDF / Excel)

---

## 🗄️ Banco de Dados

O sistema utiliza **PostgreSQL** como banco relacional principal, garantindo:

✅ Alta confiabilidade  
✅ Integridade dos dados  
✅ Escalabilidade  
✅ Performance  

### 📋 Principais Tabelas

| Tabela   | Descrição                     |
|----------|-------------------------------|
| users    | Usuários do sistema           |
| products | Produtos cadastrados          |
| stock    | Controle de estoque           |
| roles    | Perfis de acesso              |
| logs     | Histórico de ações (futuro)   |

### 🔧 Configuração

Arquivo:

```properties
backend/src/main/resources/application.properties

