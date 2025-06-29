# 🚀 Guia do Desenvolvedor - Sistema de Locação de Imóveis

## 📋 Visão Geral

Este guia fornece instruções completas para configurar e executar o sistema de locação de imóveis localmente, incluindo tanto o **frontend React** quanto o **backend Java**.

## 🏗️ Arquitetura do Sistema

```
ProjetoMC322/
├── frontend/          # Interface React (Porta 3000)
│   ├── src/
│   ├── public/
│   └── package.json
├── backend/           # Sistema Java (Porta 8080)
│   ├── src/
│   ├── pom.xml
│   └── locacao_imoveis.db
└── docs/              # Documentação
```

## 🛠️ Pré-requisitos

### **Para o Frontend (React)**

- ✅ **Node.js** 16.0 ou superior
- ✅ **npm** ou **pnpm** (recomendado)
- ✅ **Git**

### **Para o Backend (Java)**

- ✅ **Java JDK 11** ou superior
- ✅ **Maven 3.6** ou superior
- ✅ **Git**

### **Verificação dos Pré-requisitos**

```bash
# Verificar Node.js
node --version
# Deve retornar: v16.0.0 ou superior

# Verificar npm
npm --version
# Deve retornar: 8.0.0 ou superior

# Verificar Java
java --version
# Deve retornar: Java 11 ou superior

# Verificar Maven
mvn --version
# Deve retornar: Apache Maven 3.6 ou superior

# Verificar Git
git --version
# Deve retornar: git version 2.x.x
```

## 🟨 Frontend React - Configuração e Execução

### **1. Primeira Configuração**

```bash
# 1. Navegar para a pasta do frontend
cd frontend

# 2. Instalar dependências (PRIMEIRA VEZ)
npm install
# ou usando pnpm (mais rápido)
pnpm install

# 3. Verificar se tudo foi instalado corretamente
npm list --depth=0
```

### **2. Executar o Frontend**

```bash
# Desenvolvimento (com hot reload)
npm start

# O site abrirá automaticamente em: http://localhost:3000
```

### **3. Comandos Úteis do Frontend**

```bash
# Instalar nova dependência
npm install nome-do-pacote

# Build para produção
npm run build

# Executar testes
npm test

# Verificar problemas de linting
npm run lint

# Limpar cache
npm cache clean --force

# Remover node_modules e reinstalar
rm -rf node_modules package-lock.json
npm install
```

### **4. Estrutura do Frontend**

```
frontend/
├── public/
│   └── index.html          # HTML principal
├── src/
│   ├── components/         # Componentes reutilizáveis
│   │   ├── Header.js       # Cabeçalho da aplicação
│   │   └── Footer.js       # Rodapé da aplicação
│   ├── contexts/           # Contextos React
│   │   ├── AuthContext.js  # Autenticação
│   │   └── PropertyContext.js # Gerenciamento de imóveis
│   ├── pages/              # Páginas da aplicação
│   │   ├── Home.js         # Página inicial
│   │   ├── Login.js        # Login
│   │   ├── Register.js     # Cadastro
│   │   ├── Dashboard.js    # Painel do usuário
│   │   ├── PropertyList.js # Lista de imóveis
│   │   ├── PropertyDetail.js # Detalhes do imóvel
│   │   ├── AddProperty.js  # Adicionar imóvel
│   │   ├── MyProperties.js # Meus imóveis
│   │   └── Statistics.js   # Estatísticas
│   ├── App.js              # Componente principal
│   ├── App.css             # Estilos globais
│   ├── index.js            # Ponto de entrada
│   └── index.css           # Estilos base
├── package.json            # Dependências e scripts
└── README.md               # Documentação do frontend
```

### **5. Tecnologias do Frontend**

- **React 18** - Biblioteca principal
- **React Router** - Navegação
- **React Icons** - Ícones
- **CSS3** - Estilização
- **Context API** - Gerenciamento de estado

## ☕ Backend Java - Configuração e Execução

### **1. Primeira Configuração**

```bash
# 1. Navegar para a pasta do backend
cd backend

# 2. Verificar se o Maven está configurado
mvn --version

# 3. Compilar o projeto (PRIMEIRA VEZ)
mvn clean compile

# 4. Baixar dependências
mvn dependency:resolve
```

### **2. Executar o Backend**

```bash
# Desenvolvimento
mvn exec:java

# Ou compilar e executar
mvn clean package
java -jar target/sistema-locacao-imoveis-1.0.0.jar
```

### **3. Comandos Úteis do Backend**

```bash
# Compilar
mvn clean compile

# Executar testes
mvn test

# Gerar JAR executável
mvn clean package

# Executar com perfil específico
mvn exec:java -Pprod

# Limpar e reinstalar dependências
mvn clean install

# Verificar dependências
mvn dependency:tree

# Executar com argumentos
mvn exec:java -Dexec.args="backup"
```

### **4. Estrutura do Backend**

```
backend/
├── src/
│   ├── ambiente/
│   │   └── Ambiente.java
│   ├── locais/
│   │   ├── Local.java              # Classe abstrata base
│   │   ├── Casa.java               # Implementação Casa
│   │   ├── Kitnet.java             # Implementação Kitnet
│   │   ├── Quarto.java             # Implementação Quarto
│   │   ├── Republica.java          # Implementação República
│   │   ├── Pensionato.java         # Implementação Pensionato
│   │   ├── Edicula.java            # Implementação Edícula
│   │   ├── PredioKit.java          # Implementação Prédio Kit
│   │   └── RepositorioLocais.java  # Gerenciamento de imóveis
│   ├── perfil/
│   │   ├── Perfil.java             # Classe de perfil
│   │   └── Genero.java             # Enum de gênero
│   ├── sistema/
│   │   ├── SistemaLocacao.java     # Sistema principal
│   │   ├── DatabaseManager.java    # Gerenciador de banco
│   │   ├── SecurityManager.java    # Gerenciador de segurança
│   │   ├── UserRepository.java     # Repositório de usuários
│   │   └── ExemploSistemaCompleto.java # Exemplo de uso
│   └── vaga/
│       └── Vaga.java
├── pom.xml                         # Configuração Maven
├── locacao_imoveis.db              # Banco SQLite (criado automaticamente)
└── README.md                       # Documentação do backend
```

### **5. Tecnologias do Backend**

- **Java 11+** - Linguagem principal
- **SQLite** - Banco de dados
- **Maven** - Gerenciamento de dependências
- **SHA-256** - Criptografia de senhas
- **JUnit 5** - Testes unitários

## 🔄 Executando o Sistema Completo

### **Opção 1: Terminais Separados (Recomendado)**

```bash
# Terminal 1 - Backend
cd backend
mvn exec:java

# Terminal 2 - Frontend
cd frontend
npm start
```

### **Opção 2: Scripts Automatizados**

#### **Windows (PowerShell)**

```powershell
# Criar arquivo: start-system.ps1
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd backend; mvn exec:java"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd frontend; npm start"

# Executar
.\start-system.ps1
```

#### **Linux/Mac (Bash)**

```bash
# Criar arquivo: start-system.sh
#!/bin/bash
cd backend && mvn exec:java &
cd frontend && npm start &

# Executar
chmod +x start-system.sh
./start-system.sh
```

## 🌐 Acessando o Sistema

### **Frontend**

- **URL**: http://localhost:3000
- **Página inicial**: http://localhost:3000/
- **Login**: http://localhost:3000/login
- **Cadastro**: http://localhost:3000/register

### **Backend**

- **Banco de dados**: `locacao_imoveis.db` (arquivo local)
- **Logs**: Console do terminal
- **Estatísticas**: Executadas automaticamente

## 🔧 Configurações de Desenvolvimento

### **Frontend - Variáveis de Ambiente**

Criar arquivo `.env` na pasta `frontend/`:

```env
# Configurações do React
REACT_APP_API_URL=http://localhost:8080
REACT_APP_ENV=development
REACT_APP_VERSION=1.0.0

# Configurações de desenvolvimento
GENERATE_SOURCEMAP=false
CHOKIDAR_USEPOLLING=true
```

### **Backend - Configurações Maven**

Editar `pom.xml` para diferentes perfis:

```xml
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <db.url>jdbc:sqlite:locacao_imoveis_dev.db</db.url>
            <log.level>DEBUG</log.level>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <db.url>jdbc:sqlite:locacao_imoveis_prod.db</db.url>
            <log.level>INFO</log.level>
        </properties>
    </profile>
</profiles>
```

## 🐛 Solução de Problemas

### **Problemas Comuns do Frontend**

#### **1. Erro: "Module not found"**

```bash
# Limpar cache e reinstalar
rm -rf node_modules package-lock.json
npm install
```

#### **2. Erro: "Port 3000 is already in use"**

```bash
# Encontrar processo usando a porta
netstat -ano | findstr :3000
# ou
lsof -i :3000

# Matar o processo
taskkill /PID <numero_do_processo>
```

#### **3. Erro: "npm start não funciona"**

```bash
# Verificar se está na pasta correta
pwd
# Deve mostrar: .../ProjetoMC322/frontend

# Verificar se package.json existe
ls package.json

# Reinstalar dependências
npm install
```

### **Problemas Comuns do Backend**

#### **1. Erro: "Java not found"**

```bash
# Verificar variável JAVA_HOME
echo $JAVA_HOME

# Configurar JAVA_HOME (Windows)
set JAVA_HOME=C:\Program Files\Java\jdk-11

# Configurar JAVA_HOME (Linux/Mac)
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
```

#### **2. Erro: "Maven not found"**

```bash
# Verificar se Maven está no PATH
mvn --version

# Instalar Maven (Ubuntu/Debian)
sudo apt install maven

# Instalar Maven (Windows)
# Baixar de: https://maven.apache.org/download.cgi
```

#### **3. Erro: "Database connection failed"**

```bash
# Verificar permissões da pasta
ls -la locacao_imoveis.db

# Remover banco corrompido
rm locacao_imoveis.db

# Reexecutar (será criado automaticamente)
mvn exec:java
```

## 📊 Monitoramento e Logs

### **Frontend - DevTools**

- **F12** - Abrir DevTools
- **Console** - Ver erros e logs
- **Network** - Verificar requisições
- **React DevTools** - Inspecionar componentes

### **Backend - Logs**

```bash
# Ver logs em tempo real
mvn exec:java | tee logs.txt

# Ver estatísticas do banco
# Executar no código: dbManager.printDatabaseStats()
```

## 🔄 Atualizações e Manutenção

### **Atualizar Frontend**

```bash
cd frontend
git pull origin main
npm install
npm start
```

### **Atualizar Backend**

```bash
cd backend
git pull origin main
mvn clean compile
mvn exec:java
```

### **Backup do Banco de Dados**

```bash
cd backend
cp locacao_imoveis.db backup_$(date +%Y%m%d_%H%M%S).db
```

## 🧪 Testes

### **Frontend - Testes**

```bash
cd frontend
npm test
npm run test:coverage
```

### **Backend - Testes**

```bash
cd backend
mvn test
mvn test -Dtest=ExemploSistemaCompleto
```

## 📚 Recursos Adicionais

### **Documentação**

- [Guia de Persistência](GUIA_PERSISTENCIA.md) - Detalhes do banco de dados
- [Instruções de Execução](INSTRUCOES_EXECUCAO.md) - Como rodar o sistema
- [README Principal](../README.md) - Visão geral do projeto

### **Ferramentas Úteis**

- **Postman** - Testar APIs
- **SQLite Browser** - Visualizar banco de dados
- **VS Code** - Editor recomendado
- **IntelliJ IDEA** - IDE Java

### **Comandos Rápidos**

```bash
# Iniciar tudo rapidamente
cd frontend && npm start & cd backend && mvn exec:java

# Parar todos os processos
pkill -f "npm start"
pkill -f "mvn exec:java"

# Limpar tudo
cd frontend && rm -rf node_modules package-lock.json
cd backend && mvn clean
```

## 🎯 Checklist de Primeira Execução

### **Frontend**

- [ ] Node.js instalado (v16+)
- [ ] Navegar para pasta `frontend/`
- [ ] Executar `npm install`
- [ ] Executar `npm start`
- [ ] Acessar http://localhost:3000

### **Backend**

- [ ] Java JDK 11+ instalado
- [ ] Maven 3.6+ instalado
- [ ] Navegar para pasta `backend/`
- [ ] Executar `mvn clean compile`
- [ ] Executar `mvn exec:java`
- [ ] Verificar criação do banco `locacao_imoveis.db`

### **Sistema Completo**

- [ ] Frontend rodando em http://localhost:3000
- [ ] Backend executando sem erros
- [ ] Banco de dados criado
- [ ] Pode fazer login/cadastro
- [ ] Pode visualizar imóveis

---

**🎓 Desenvolvido para o curso MC322 da Unicamp**

**📧 Suporte**: Em caso de problemas, verifique os logs e consulte a documentação adicional.
