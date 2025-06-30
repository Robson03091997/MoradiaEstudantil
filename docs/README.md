# Documentação - Sistema de Locação de Imóveis

## 📋 Visão Geral

Esta pasta contém toda a documentação do projeto Sistema de Locação de Imóveis (ProjetoMC322), incluindo guias de desenvolvimento, instruções de execução, scripts de automação e **opções de deploy gratuito**.

## 🚀 **OPÇÕES DE DEPLOY**

### **🆓 GRATUITO - Netlify + Render (RECOMENDADO)**

- **Custo**: $0/mês - 100% GRATUITO!
- **Frontend**: Netlify (100GB/mês)
- **Backend**: Render (750h/mês)
- **Guia**: [GUIA_DEPLOY_GRATUITO.md](GUIA_DEPLOY_GRATUITO.md)

### **💰 ECONÔMICO - Vercel + Railway**

- **Custo**: $5/mês
- **Frontend**: Vercel (gratuito)
- **Backend**: Railway ($5/mês)
- **Guia**: [GUIA_DEPLOY.md](GUIA_DEPLOY.md)

## 📁 Arquivos de Documentação

### 📖 GUIA_DESENVOLVEDOR.md

**Guia completo para desenvolvedores**

Este é o **guia principal** para configurar e executar o sistema localmente:

- ✅ **Pré-requisitos** - Node.js, Java, Maven
- ✅ **Configuração inicial** - Frontend e Backend
- ✅ **Comandos úteis** - Desenvolvimento e produção
- ✅ **Estrutura do projeto** - Organização dos arquivos
- ✅ **Solução de problemas** - Erros comuns e soluções
- ✅ **Monitoramento** - Logs e estatísticas
- ✅ **Testes** - Como executar testes
- ✅ **Checklist** - Verificação de primeira execução

### 📖 GUIA_DEPLOY_GRATUITO.md

**Deploy TOTALMENTE GRATUITO**

Guia para hospedar o sistema sem custos:

- ✅ **Netlify** - Frontend React (gratuito)
- ✅ **Render** - Backend Java (gratuito)
- ✅ **Configuração automática** - Deploy no push
- ✅ **SSL gratuito** - HTTPS automático
- ✅ **Domínio personalizado** - Suportado
- ✅ **Monitoramento** - Logs e métricas

### 📖 GUIA_DEPLOY.md

**Deploy com opções pagas**

Guia para deploy com mais recursos:

- ✅ **Vercel** - Frontend React
- ✅ **Railway** - Backend Java
- ✅ **PostgreSQL** - Banco de dados
- ✅ **CI/CD** - GitHub Actions
- ✅ **Monitoramento avançado**

### 📖 GUIA_PERSISTENCIA.md

**Detalhes sobre banco de dados e segurança**

Este guia explica as estratégias de persistência implementadas:

- ✅ **Banco SQLite** - Estrutura e configuração
- ✅ **Criptografia** - SHA-256 + Salt para senhas
- ✅ **Sessões** - Gerenciamento de tokens
- ✅ **Migração** - Para PostgreSQL, MySQL, MongoDB
- ✅ **Backup** - Estratégias de backup
- ✅ **Monitoramento** - Estatísticas do sistema

### 📖 FLUXOGRAMA_OO.md

**Fluxograma da estrutura orientada a objetos**

Documento visual da arquitetura OO:

- ✅ **Diagrama de classes** - Hierarquia completa
- ✅ **Fluxo de execução** - Como o sistema funciona
- ✅ **Padrões de design** - Singleton, Template Method, Strategy
- ✅ **Relacionamentos** - Herança, composição, agregação
- ✅ **Princípios OO** - Encapsulamento, herança, polimorfismo

### 📖 INSTRUCOES_EXECUCAO.md

**Instruções básicas de execução**

Instruções rápidas para rodar o sistema:

- ✅ **Comandos básicos** - npm start, mvn exec:java
- ✅ **Verificação** - Se tudo está funcionando
- ✅ **Acesso** - URLs do sistema

### 📖 INSTRUCOES_REACT.md

**Instruções específicas do frontend React**

Guia detalhado do frontend:

- ✅ **Configuração** - Node.js e dependências
- ✅ **Desenvolvimento** - Hot reload e debugging
- ✅ **Build** - Produção e otimização
- ✅ **Estrutura** - Componentes e páginas

## 🚀 Scripts de Automação

### **🆓 Deploy Gratuito (Netlify + Render)**

```bash
# Executar script de deploy gratuito
chmod +x docs/deploy-gratuito.sh
./docs/deploy-gratuito.sh
```

**Funcionalidades:**

- ✅ Build automático do backend e frontend
- ✅ Commit e push para GitHub
- ✅ Instruções para Netlify e Render
- ✅ Configuração de variáveis de ambiente
- ✅ URLs finais gratuitas

### **Windows (PowerShell)**

```powershell
# Executar script de inicialização
.\docs\start-system.ps1
```

**Funcionalidades:**

- ✅ Verifica pré-requisitos (Node.js, Java, Maven)
- ✅ Instala dependências automaticamente
- ✅ Compila o backend
- ✅ Inicia frontend e backend em janelas separadas
- ✅ Mostra URLs de acesso

### **Linux/Mac (Bash)**

```bash
# Dar permissão de execução
chmod +x docs/start-system.sh

# Executar script de inicialização
./docs/start-system.sh
```

**Funcionalidades:**

- ✅ Verifica pré-requisitos
- ✅ Instala dependências automaticamente
- ✅ Compila o backend
- ✅ Inicia serviços em background
- ✅ Logs separados para frontend e backend
- ✅ Limpeza automática ao parar (Ctrl+C)

## 🛠️ Configurações de Ambiente

### **Frontend**

```bash
# Copiar arquivo de exemplo
cp frontend/env.example frontend/.env

# Editar configurações
nano frontend/.env
```

**Variáveis importantes:**

- `REACT_APP_API_URL` - URL do backend
- `REACT_APP_ENV` - Ambiente (development/production)
- `REACT_APP_DEBUG` - Modo debug

### **Backend**

```bash
# Executar com perfil específico
mvn exec:java -Pdev    # Desenvolvimento
mvn exec:java -Pprod   # Produção
```

## 🎯 Início Rápido

### **Para Deploy Gratuito**

1. **Clone o repositório**

   ```bash
   git clone <url-do-repositorio>
   cd ProjetoMC322
   ```

2. **Execute o script de deploy gratuito**

   ```bash
   chmod +x docs/deploy-gratuito.sh
   ./docs/deploy-gratuito.sh
   ```

3. **Siga as instruções para Netlify e Render**
   - Frontend: https://netlify.com
   - Backend: https://render.com

### **Para Desenvolvedores**

1. **Clone o repositório**

   ```bash
   git clone <url-do-repositorio>
   cd ProjetoMC322
   ```

2. **Execute o script de inicialização**

   ```bash
   # Windows
   .\docs\start-system.ps1

   # Linux/Mac
   chmod +x docs/start-system.sh
   ./docs/start-system.sh
   ```

3. **Acesse o sistema**
   - Frontend: http://localhost:3000
   - Backend: http://localhost:8080

### **Para Usuários Finais**

1. **Siga o [GUIA_DESENVOLVEDOR.md](GUIA_DESENVOLVEDOR.md)**
2. **Use os scripts de automação**
3. **Consulte a solução de problemas se necessário**

## 📊 Monitoramento

### **Logs Disponíveis**

- **Frontend**: `frontend/frontend.log` (Linux/Mac)
- **Backend**: `backend/backend.log` (Linux/Mac)
- **Console**: Janelas do PowerShell (Windows)

### **Estatísticas do Sistema**

- 👥 Usuários ativos
- 🏠 Imóveis cadastrados
- ❤️ Favoritos
- 👁️ Visualizações
- 📞 Contatos

## 🔧 Manutenção

### **Atualizações**

```bash
# Atualizar código
git pull origin main

# Reinstalar dependências (se necessário)
cd frontend && npm install
cd backend && mvn clean install
```

### **Backup**

```bash
# Backup do banco de dados
cd backend
cp locacao_imoveis.db backup_$(date +%Y%m%d_%H%M%S).db
```

### **Limpeza**

```bash
# Limpar cache do frontend
cd frontend && npm cache clean --force

# Limpar build do backend
cd backend && mvn clean
```

## 🐛 Solução de Problemas

### **Problemas Comuns**

#### **1. Porta 3000 em uso**

```bash
# Encontrar processo
netstat -ano | findstr :3000  # Windows
lsof -i :3000                 # Linux/Mac

# Matar processo
taskkill /PID <numero>        # Windows
kill <numero>                 # Linux/Mac
```

#### **2. Dependências não instaladas**

```bash
# Frontend
cd frontend && rm -rf node_modules package-lock.json && npm install

# Backend
cd backend && mvn clean install
```

#### **3. Banco de dados corrompido**

```bash
cd backend
rm locacao_imoveis.db
mvn exec:java  # Será recriado automaticamente
```

## 📚 Recursos Adicionais

### **Ferramentas Recomendadas**

- **VS Code** - Editor para frontend
- **IntelliJ IDEA** - IDE para backend
- **Postman** - Testar APIs
- **SQLite Browser** - Visualizar banco

### **Documentação Externa**

- [React Documentation](https://reactjs.org/docs/)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [SQLite Documentation](https://www.sqlite.org/docs.html)

### **Comandos Rápidos**

```bash
# Iniciar tudo
./docs/start-system.sh

# Deploy gratuito
./docs/deploy-gratuito.sh

# Parar tudo
pkill -f "npm start" && pkill -f "mvn exec:java"

# Ver logs
tail -f backend/backend.log
tail -f frontend/frontend.log

# Backup rápido
cp backend/locacao_imoveis.db backup_$(date +%Y%m%d).db
```

## 🎓 Informações do Projeto

- **Curso**: MC322 - Programação Orientada a Objetos
- **Instituição**: Unicamp
- **Desenvolvido**: 2025
- **Tecnologias**: React, Java, SQLite, Maven

## 📧 Suporte

Em caso de problemas:

1. **Consulte o [GUIA_DESENVOLVEDOR.md](GUIA_DESENVOLVEDOR.md)**
2. **Para deploy gratuito**: [GUIA_DEPLOY_GRATUITO.md](GUIA_DEPLOY_GRATUITO.md)
3. **Verifique os logs de erro**
4. **Use os scripts de automação**
5. **Consulte a seção de solução de problemas**

---

**🎓 Desenvolvido para o curso MC322 da Unicamp**

**📧 Para dúvidas**: Consulte a documentação ou entre em contato com a equipe de desenvolvimento.
