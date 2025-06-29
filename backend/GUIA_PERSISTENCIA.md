# 🗄️ Guia de Persistência - Sistema de Locação de Imóveis

## 📋 Visão Geral

Este guia explica as estratégias de persistência implementadas no sistema de locação de imóveis, incluindo banco de dados, criptografia de senhas e gerenciamento de sessões.

## 🎯 Estratégias Implementadas

### 1. **Banco de Dados SQLite** 🗃️

#### **Por que SQLite?**

- ✅ **Simples**: Não requer servidor separado
- ✅ **Portátil**: Arquivo único
- ✅ **Confiável**: Muito testado e estável
- ✅ **Gratuito**: Sem licenças
- ✅ **Perfeito para desenvolvimento**: Fácil de usar

#### **Estrutura do Banco**

```sql
-- Tabela de usuários
CREATE TABLE usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    senha_hash TEXT NOT NULL,
    salt TEXT NOT NULL,
    telefone TEXT,
    genero TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela de imóveis
CREATE TABLE imoveis (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL,
    titulo TEXT NOT NULL,
    endereco TEXT NOT NULL,
    preco REAL NOT NULL,
    descricao TEXT,
    proprietario_id INTEGER,
    forma_caucao TEXT,
    tipo_compartilhamento TEXT,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (proprietario_id) REFERENCES usuarios (id)
);

-- Tabela de favoritos
CREATE TABLE favoritos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    usuario_id INTEGER,
    imovel_id INTEGER,
    data_favorito TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    FOREIGN KEY (imovel_id) REFERENCES imoveis (id),
    UNIQUE(usuario_id, imovel_id)
);

-- Tabela de visualizações
CREATE TABLE visualizacoes (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    imovel_id INTEGER,
    data_visualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_visitor TEXT,
    FOREIGN KEY (imovel_id) REFERENCES imoveis (id)
);

-- Tabela de contatos
CREATE TABLE contatos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    imovel_id INTEGER,
    nome_contato TEXT NOT NULL,
    email_contato TEXT,
    telefone_contato TEXT,
    mensagem TEXT,
    data_contato TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    respondido BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (imovel_id) REFERENCES imoveis (id)
);
```

### 2. **Criptografia de Senhas** 🔐

#### **Algoritmo: SHA-256 + Salt**

```java
// Geração de salt aleatório
String salt = securityManager.generateSalt();

// Criptografia da senha
String senhaHash = securityManager.hashPassword(senha, salt);

// Verificação da senha
boolean senhaCorreta = securityManager.verifyPassword(senha, salt, storedHash);
```

#### **Características de Segurança**

- ✅ **Salt único**: Cada usuário tem um salt diferente
- ✅ **Hash SHA-256**: Algoritmo criptográfico robusto
- ✅ **Validação de força**: Senhas devem ser fortes
- ✅ **Impossível de reverter**: Hash unidirecional

#### **Validação de Força de Senha**

```java
// Senha fraca: "123"
// Senha média: "Senha123"
// Senha forte: "MinhaSenha123!@#"

SecurityManager.PasswordStrength strength =
    securityManager.validatePasswordStrength(senha);
```

### 3. **Gerenciamento de Sessões** 🎫

#### **Sistema de Tokens**

```java
// Criar sessão
String token = securityManager.createUserSession(userId, email);

// Verificar sessão
boolean valida = securityManager.isValidSession(token);

// Obter dados da sessão
UserSession session = securityManager.getSession(token);
```

#### **Características**

- ✅ **Tokens únicos**: 32 bytes aleatórios
- ✅ **Expiração**: 24 horas
- ✅ **Limpeza automática**: Sessões expiradas são removidas
- ✅ **Seguro**: Tokens não podem ser adivinhados

## 🚀 Como Usar

### **1. Configuração Inicial**

```bash
# Navegar para o backend
cd backend

# Compilar com Maven
mvn clean compile

# Executar o sistema
mvn exec:java
```

### **2. Exemplo de Uso**

```java
// Inicializar componentes
DatabaseManager dbManager = DatabaseManager.getInstance();
SecurityManager securityManager = SecurityManager.getInstance();
UserRepository userRepo = new UserRepository();

// Cadastrar usuário
boolean sucesso = userRepo.cadastrarUsuario(
    "João Silva",
    "joao@email.com",
    "Senha123!",
    "(11) 99999-9999",
    Genero.MASCULINO
);

// Autenticar usuário
Optional<Perfil> perfil = userRepo.autenticarUsuario(
    "joao@email.com",
    "Senha123!"
);
```

### **3. Backup e Restauração**

```java
// Fazer backup
dbManager.backupDatabase("backup_2025_06_28.db");

// Verificar estatísticas
dbManager.printDatabaseStats();
```

## 📊 Vantagens da Implementação

### **Segurança** 🔒

- ✅ Senhas criptografadas com salt
- ✅ Validação de força de senha
- ✅ Sessões seguras com expiração
- ✅ Proteção contra SQL injection

### **Performance** ⚡

- ✅ Conexões reutilizadas
- ✅ Índices otimizados
- ✅ Queries preparadas
- ✅ Cache de sessões

### **Manutenibilidade** 🔧

- ✅ Código bem estruturado
- ✅ Padrão Singleton para managers
- ✅ Separação de responsabilidades
- ✅ Logs detalhados

### **Escalabilidade** 📈

- ✅ Fácil migração para outros bancos
- ✅ Arquitetura modular
- ✅ Configuração flexível
- ✅ Backup automático

## 🔄 Migração para Produção

### **Opções de Banco de Dados**

#### **1. PostgreSQL (Recomendado)**

```xml
<!-- Adicionar no pom.xml -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.6.0</version>
</dependency>
```

#### **2. MySQL**

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

#### **3. MongoDB (NoSQL)**

```xml
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver-sync</artifactId>
    <version>4.10.2</version>
</dependency>
```

### **Configuração de Produção**

```properties
# application.properties
db.url=jdbc:postgresql://localhost:5432/locacao_imoveis
db.username=admin
db.password=senha_segura
db.pool.size=10

security.session.timeout=3600
security.password.min.length=8
```

## 🛠️ Ferramentas de Desenvolvimento

### **1. Maven**

- Gerenciamento de dependências
- Compilação automatizada
- Execução de testes
- Build de produção

### **2. Logging**

- SLF4J + Logback
- Logs estruturados
- Diferentes níveis (DEBUG, INFO, ERROR)
- Rotação de arquivos

### **3. Testes**

- JUnit 5
- Testes unitários
- Testes de integração
- Cobertura de código

## 📈 Monitoramento

### **Estatísticas Disponíveis**

- 👥 Número de usuários ativos
- 🏠 Número de imóveis cadastrados
- ❤️ Número de favoritos
- 👁️ Número de visualizações
- 📞 Número de contatos

### **Logs de Segurança**

- Tentativas de login
- Alterações de senha
- Criação de sessões
- Ações administrativas

## 🔧 Manutenção

### **Tarefas Regulares**

1. **Backup diário** do banco de dados
2. **Limpeza** de sessões expiradas
3. **Verificação** de logs de erro
4. **Atualização** de dependências

### **Comandos Úteis**

```bash
# Backup
mvn exec:java -Dexec.args="backup"

# Estatísticas
mvn exec:java -Dexec.args="stats"

# Limpeza
mvn exec:java -Dexec.args="cleanup"
```

## 🎯 Conclusão

Esta implementação oferece:

- ✅ **Segurança robusta** para dados sensíveis
- ✅ **Performance otimizada** para operações
- ✅ **Facilidade de manutenção** e evolução
- ✅ **Escalabilidade** para crescimento futuro
- ✅ **Conformidade** com boas práticas

O sistema está pronto para uso em desenvolvimento e pode ser facilmente adaptado para produção com mínimas modificações.

---

**Desenvolvido para o curso MC322 da Unicamp** 🎓
