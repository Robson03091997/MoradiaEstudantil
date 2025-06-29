# Backend - Sistema de Locação de Imóveis

## 📋 Descrição

Este é o backend do sistema de locação de imóveis desenvolvido em Java. O sistema implementa um modelo orientado a objetos completo para gerenciar diferentes tipos de imóveis, perfis de usuários e locações, com persistência robusta em banco de dados SQLite.

## 🏗️ Estrutura do Código

```
backend/src/
├── ambiente/
│   └── Ambiente.java          # Classe base para ambientes
├── locais/
│   ├── Local.java             # Classe abstrata base para todos os tipos de imóveis
│   ├── Casa.java              # Implementação específica para casas
│   ├── Kitnet.java            # Implementação específica para kitnets
│   ├── Quarto.java            # Implementação específica para quartos
│   ├── Republica.java         # Implementação específica para repúblicas
│   ├── Pensionato.java        # Implementação específica para pensionatos
│   ├── Edicula.java           # Implementação específica para edículas
│   ├── PredioKit.java         # Implementação específica para prédios de kitnets
│   └── RepositorioLocais.java # Gerenciamento de imóveis
├── perfil/
│   ├── Perfil.java            # Classe de perfil de usuário
│   └── Genero.java            # Enum para gênero
├── sistema/
│   ├── SistemaLocacao.java    # Sistema principal de locação
│   ├── DatabaseManager.java   # Gerenciador de banco de dados SQLite
│   ├── SecurityManager.java   # Gerenciador de segurança e criptografia
│   ├── UserRepository.java    # Repositório de usuários com persistência
│   └── ExemploSistemaCompleto.java # Exemplo completo de uso
└── vaga/
    └── Vaga.java              # Classe relacionada a vagas
```

## 🗄️ Sistema de Persistência

### **Banco de Dados SQLite**

- ✅ **Persistência completa** de todos os dados
- ✅ **Criptografia de senhas** com SHA-256 + Salt
- ✅ **Gerenciamento de sessões** seguro
- ✅ **Backup automático** e estatísticas

### **Tabelas Principais**

- `usuarios` - Dados dos usuários (nome, email, senha criptografada)
- `imoveis` - Cadastro de imóveis com detalhes completos
- `favoritos` - Relacionamento usuário-imóvel favoritado
- `visualizacoes` - Controle de visualizações de imóveis
- `contatos` - Mensagens de contato sobre imóveis

### **Segurança Implementada**

- 🔐 **Senhas criptografadas** com salt único por usuário
- 🎫 **Sessões seguras** com tokens únicos e expiração
- ✅ **Validação de força** de senha
- 🛡️ **Proteção contra SQL injection**

## 🚀 Funcionalidades

### **Gestão de Usuários**

- ✅ Cadastro com validação de dados
- ✅ Autenticação segura
- ✅ Perfis personalizados
- ✅ Alteração de senha
- ✅ Desativação de conta

### **Gestão de Imóveis**

- ✅ Cadastro de diferentes tipos (Casa, Kitnet, Quarto, etc.)
- ✅ Busca por tipo, localização e preço
- ✅ Sistema de favoritos
- ✅ Controle de visualizações
- ✅ Sistema de contato

### **Tipos de Imóveis Suportados**

- 🏠 **Casa**: Residências completas
- 🏢 **Kitnet**: Apartamentos compactos
- 🛏️ **Quarto**: Quartos individuais
- 🏘️ **República**: Compartilhamento estudantil
- 🏨 **Pensionato**: Hospedagem temporária
- 🏡 **Edícula**: Construções anexas
- 🏬 **Prédio Kit**: Conjuntos de kitnets

### **Sistema de Caução**

- 💰 **Dinheiro**: Caução em dinheiro
- 📄 **Fiador**: Caução com fiador
- 🏦 **Seguro**: Caução com seguro
- 📋 **Responsável**: Caução com responsável

### **Tipos de Compartilhamento**

- 👤 **Individual**: Uso exclusivo
- 👥 **Compartilhado**: Uso compartilhado

## 🛠️ Tecnologias Utilizadas

### **Core**

- **Java 11+** - Linguagem principal
- **SQLite** - Banco de dados
- **Maven** - Gerenciamento de dependências

### **Segurança**

- **SHA-256** - Criptografia de senhas
- **Salt único** - Proteção adicional
- **Tokens JWT-like** - Sessões seguras

### **Dependências**

- **SQLite JDBC** - Driver do banco
- **JUnit 5** - Testes unitários
- **SLF4J + Logback** - Sistema de logs
- **Jackson** - Processamento JSON
- **Hibernate Validator** - Validação de dados

## 📦 Instalação e Configuração

### **Pré-requisitos**

- Java 11 ou superior
- Maven 3.6+
- Git

### **Passos de Instalação**

```bash
# 1. Clonar o repositório
git clone <url-do-repositorio>
cd ProjetoMC322/backend

# 2. Compilar o projeto
mvn clean compile

# 3. Executar o sistema
mvn exec:java
```

### **Configuração do Banco de Dados**

O banco SQLite é criado automaticamente na primeira execução:

- **Arquivo**: `locacao_imoveis.db`
- **Localização**: Diretório raiz do backend
- **Backup**: `backup_YYYY_MM_DD.db`

## 🔧 Comandos Úteis

### **Desenvolvimento**

```bash
# Compilar
mvn clean compile

# Executar
mvn exec:java

# Testes
mvn test

# Limpar
mvn clean
```

### **Produção**

```bash
# Build completo
mvn clean package

# Executar JAR
java -jar target/sistema-locacao-imoveis-1.0.0.jar

# Com perfil de produção
mvn exec:java -Pprod
```

## 📊 Exemplo de Uso

```java
// Inicializar componentes
DatabaseManager dbManager = DatabaseManager.getInstance();
SecurityManager securityManager = SecurityManager.getInstance();
UserRepository userRepo = new UserRepository();
SistemaLocacao sistema = new SistemaLocacao();

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

// Cadastrar imóvel
Casa casa = new Casa(
    "Casa com 3 quartos",
    "Rua das Flores, 123 - São Paulo",
    2500.0,
    "Casa espaçosa com jardim",
    "João Silva",
    "joao@email.com",
    "(11) 99999-9999",
    Local.FormaCaucao.DINHEIRO,
    Local.TipoCompartilhamento.INDIVIDUAL
);

sistema.cadastrarLocal(casa);

// Buscar imóveis
List<Local> casas = sistema.buscarPorTipo("Casa");
List<Local> imoveisAte1500 = sistema.buscarPorPreco(0.0, 1500.0);
```

## 🔒 Segurança

### **Criptografia de Senhas**

- Algoritmo: SHA-256
- Salt único por usuário
- Impossível de reverter
- Validação de força

### **Sessões**

- Tokens únicos de 32 bytes
- Expiração em 24 horas
- Limpeza automática
- Proteção contra ataques

### **Banco de Dados**

- Prepared Statements
- Validação de entrada
- Controle de acesso
- Backup regular

## 📈 Monitoramento

### **Estatísticas Disponíveis**

- 👥 Número de usuários ativos
- 🏠 Número de imóveis cadastrados
- ❤️ Número de favoritos
- 👁️ Número de visualizações
- 📞 Número de contatos

### **Logs**

- Tentativas de login
- Alterações de dados
- Erros do sistema
- Ações administrativas

## 🔄 Migração para Produção

### **Opções de Banco**

1. **PostgreSQL** (Recomendado)
2. **MySQL**
3. **MongoDB** (NoSQL)

### **Configurações**

- Pool de conexões
- Logs estruturados
- Monitoramento
- Backup automático

## 📚 Documentação Adicional

- [Guia de Persistência](GUIA_PERSISTENCIA.md) - Detalhes sobre banco de dados e segurança
- [Instruções de Execução](INSTRUCOES_EXECUCAO.md) - Como rodar o sistema
- [Exemplos de Uso](src/sistema/ExemploSistemaCompleto.java) - Código de exemplo

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## 📄 Licença

Este projeto foi desenvolvido para o curso MC322 da Unicamp.

---

**Desenvolvido com ❤️ para o curso MC322 da Unicamp** 🎓
