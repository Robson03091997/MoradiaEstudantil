# 🆓 Guia de Deploy GRATUITO - Netlify + Render

## 📋 Visão Geral

Este guia te ajudará a hospedar seu sistema de locação de imóveis **TOTALMENTE GRATUITO** usando **Netlify** para o frontend React e **Render** para o backend Java.

## 🎯 Por que Netlify + Render?

| Serviço                | Preço    | Limitações | Vantagens                     |
| ---------------------- | -------- | ---------- | ----------------------------- |
| **Netlify (Frontend)** | Gratuito | 100GB/mês  | Deploy automático, SSL, CDN   |
| **Render (Backend)**   | Gratuito | 750h/mês   | Deploy automático, PostgreSQL |

**✅ TOTAL: $0/mês - 100% GRATUITO!**

## 🚀 Passo a Passo Completo

### **Passo 1: Preparar o Repositório**

```bash
# 1. Verificar se está na pasta correta
cd ProjetoMC322

# 2. Verificar se é um repositório Git
git status

# 3. Se não for, inicializar
git init
git add .
git commit -m "Primeiro commit"

# 4. Conectar ao GitHub (se ainda não fez)
git remote add origin https://github.com/seu-usuario/seu-repositorio.git
git push -u origin main
```

### **Passo 2: Deploy do Backend no Render**

#### **2.1 Acessar Render**

1. Vá para: https://render.com
2. Clique em **"Sign Up"**
3. Escolha **"Continue with GitHub"**
4. Autorize o acesso ao seu GitHub

#### **2.2 Criar Novo Serviço**

1. Clique em **"New +"**
2. Selecione **"Web Service"**
3. Clique em **"Connect"** no seu repositório

#### **2.3 Configurar o Serviço**

Preencha os campos:

```
Name: sistema-locacao-backend
Environment: Java
Region: Oregon (US West)
Branch: main
Root Directory: backend
Build Command: mvn clean package -DskipTests
Start Command: java -jar target/sistema-locacao-imoveis-1.0.0.jar
```

#### **2.4 Configurar Variáveis de Ambiente**

Clique em **"Environment"** e adicione:

```
JAVA_VERSION=11
PORT=8080
SPRING_PROFILES_ACTIVE=production
CORS_ORIGINS=https://seu-frontend.netlify.app
```

#### **2.5 Deploy**

1. Clique em **"Create Web Service"**
2. Aguarde o build (pode demorar 5-10 minutos)
3. Anote a URL gerada: `https://seu-backend.onrender.com`

### **Passo 3: Deploy do Frontend no Netlify**

#### **3.1 Acessar Netlify**

1. Vá para: https://netlify.com
2. Clique em **"Sign up"**
3. Escolha **"Continue with GitHub"**
4. Autorize o acesso ao seu GitHub

#### **3.2 Importar Projeto**

1. Clique em **"New site from Git"**
2. Escolha **"GitHub"**
3. Selecione seu repositório
4. Configure:

```
Base directory: frontend
Build command: npm run build
Publish directory: build
```

#### **3.3 Configurar Variáveis de Ambiente**

1. Vá em **"Site settings"** → **"Environment variables"**
2. Adicione:

```
REACT_APP_API_URL=https://seu-backend.onrender.com
REACT_APP_ENV=production
```

#### **3.4 Deploy**

1. Clique em **"Deploy site"**
2. Aguarde o build (2-3 minutos)
3. Anote a URL gerada: `https://seu-frontend.netlify.app`

## 🔧 Configurações Específicas

### **Backend - Render**

#### **Arquivo `render.yaml`**

```yaml
services:
  - type: web
    name: sistema-locacao-backend
    env: java
    plan: free
    buildCommand: mvn clean package -DskipTests
    startCommand: java -jar target/sistema-locacao-imoveis-1.0.0.jar
    envVars:
      - key: JAVA_VERSION
        value: 11
      - key: PORT
        value: 8080
      - key: SPRING_PROFILES_ACTIVE
        value: production
    healthCheckPath: /api/health
    autoDeploy: true
```

#### **Configuração de Produção**

```properties
# application-prod.properties
server.port=${PORT:8080}
spring.datasource.url=${DATABASE_URL:jdbc:sqlite:locacao_imoveis.db}
spring.web.cors.allowed-origins=${CORS_ORIGINS:https://seu-frontend.netlify.app}
```

### **Frontend - Netlify**

#### **Arquivo `netlify.toml`**

```toml
[build]
  publish = "build"
  command = "npm run build"

[build.environment]
  NODE_VERSION = "16"
  NPM_VERSION = "8"

[[redirects]]
  from = "/*"
  to = "/index.html"
  status = 200

[context.production.environment]
  REACT_APP_API_URL = "https://seu-backend.onrender.com"
  REACT_APP_ENV = "production"
```

## 🌐 URLs Finais

Após o deploy, você terá:

- **Frontend**: `https://seu-frontend.netlify.app`
- **Backend**: `https://seu-backend.onrender.com`
- **Health Check**: `https://seu-backend.onrender.com/api/health`
- **API Info**: `https://seu-backend.onrender.com/api/info`

## 🔒 Configurações de Segurança

### **CORS Configurado**

```java
@CrossOrigin(origins = {"http://localhost:3000", "https://seu-frontend.netlify.app"})
```

### **SSL/HTTPS**

- ✅ **Netlify**: SSL automático
- ✅ **Render**: SSL automático
- ✅ **Custom domains**: Suportado

## 📊 Monitoramento

### **Netlify Analytics**

- Visitas diárias
- Performance
- Erros de build
- Deploy previews

### **Render Metrics**

- CPU/Memory usage
- Request logs
- Error tracking
- Uptime monitoring

## 🔄 Deploy Automático

### **Configuração Automática**

- ✅ **Netlify**: Deploy automático no push
- ✅ **Render**: Deploy automático no push
- ✅ **Preview deployments**: Disponível

### **GitHub Actions (Opcional)**

```yaml
# .github/workflows/deploy.yml
name: Deploy to Netlify + Render

on:
  push:
    branches: [main]

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Deploy to Netlify
        uses: nwtgck/actions-netlify@v1.2
        with:
          publish-dir: "./frontend/build"
          production-branch: main
          github-token: ${{ secrets.GITHUB_TOKEN }}
          deploy-message: "Deploy from GitHub Actions"
```

## 🐛 Solução de Problemas

### **Problemas Comuns**

#### **1. Build Failed no Render**

```bash
# Verificar logs no Render Dashboard
# Verificar se o pom.xml está correto
# Verificar se o Java 11 está configurado
```

#### **2. Build Failed no Netlify**

```bash
# Verificar se o package.json está na pasta frontend
# Verificar se todas as dependências estão instaladas
# Verificar se o build command está correto
```

#### **3. CORS Errors**

```javascript
// Verificar se a URL da API está correta
console.log(process.env.REACT_APP_API_URL);
```

#### **4. Backend não responde**

```bash
# Verificar health check
curl https://seu-backend.onrender.com/api/health

# Verificar logs no Render Dashboard
```

### **Comandos Úteis**

```bash
# Testar localmente antes do deploy
cd backend && mvn clean package
cd frontend && npm run build

# Verificar se tudo está funcionando
curl http://localhost:8080/api/health
```

## 💰 Custos

### **Plano Gratuito**

- **Netlify**: $0/mês (100GB bandwidth)
- **Render**: $0/mês (750h/mês)
- **Total**: $0/mês

### **Limitações do Plano Gratuito**

- **Netlify**: 100GB de bandwidth por mês
- **Render**: 750 horas de execução por mês
- **Render**: Sleep após 15 minutos de inatividade

## 🎯 Próximos Passos

1. **Deploy inicial** seguindo este guia
2. **Testar todas as funcionalidades**
3. **Configurar domínio personalizado** (opcional)
4. **Implementar CI/CD** (opcional)
5. **Adicionar monitoramento**

## 📞 Suporte

- **Netlify**: https://docs.netlify.com
- **Render**: https://render.com/docs
- **Documentação**: Este guia

## 🎉 Parabéns!

Você agora tem seu sistema de locação de imóveis **TOTALMENTE GRATUITO** e online!

**URLs do seu sistema:**

- 🌐 **Site**: https://seu-frontend.netlify.app
- 🔧 **API**: https://seu-backend.onrender.com

---

**🎓 Desenvolvido para o curso MC322 da Unicamp**

**📧 Para dúvidas**: Consulte a documentação ou entre em contato com a equipe de desenvolvimento.
