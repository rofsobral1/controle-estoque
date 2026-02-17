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
```
## 🚀 Telas da Aplicação

### ✅ Tela de login 

<img width="1366" height="720" alt="Captura de tela 2026-02-17 002917" src="https://github.com/user-attachments/assets/dbf0fd8f-7beb-4007-9477-2061e9e7d857" />

### ✅ Menu inicial

<img width="1366" height="720" alt="Captura de tela 2026-02-17 003003" src="https://github.com/user-attachments/assets/e9a74d86-615b-4d33-9d19-e4a339c85ba6" />

### ✅ Tela inicial de clientes

<img width="1366" height="720" alt="Captura de tela 2026-02-17 003022" src="https://github.com/user-attachments/assets/04a61572-c541-43fe-a20d-a5e550a18153" />

### ✅ Tela de listagem de clientes

<img width="1366" height="720" alt="Captura de tela 2026-02-17 003035" src="https://github.com/user-attachments/assets/ece71d1e-5d80-4c81-8d52-35f36ecc7e11" />

### ✅ Tela de cadastro de clientes

<img width="1366" height="720" alt="Captura de tela 2026-02-17 003103" src="https://github.com/user-attachments/assets/d707aba8-33d4-4b7b-9e0b-d814a5edb9da" />

### ✅ Tela de exclusão de clientes

<img width="1366" height="720" alt="Captura de tela 2026-02-17 003118" src="https://github.com/user-attachments/assets/b0136300-a5ec-4b26-bb32-c5ae37d740a0" />
