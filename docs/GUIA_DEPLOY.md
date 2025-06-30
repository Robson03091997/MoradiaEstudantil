# 🚀 Guia de Deploy - Sistema de Locação de Imóveis

## 📋 Visão Geral

Este guia te ajudará a hospedar seu sistema de locação de imóveis de forma fácil e econômica. Vamos usar **Vercel** para o frontend React e **Railway** para o backend Java.

## 🎯 Opções de Hospedagem

### **1. 🆓 GRATUITO - Vercel + Railway (Recomendado)**

| Serviço               | Preço    | Limitações | Vantagens                     |
| --------------------- | -------- | ---------- | ----------------------------- |
| **Vercel (Frontend)** | Gratuito | 100GB/mês  | Deploy automático, SSL, CDN   |
| **Railway (Backend)** | $5/mês   | 500h/mês   | Deploy automático, PostgreSQL |

### **2. 🆓 GRATUITO - Netlify + Render**

| Serviço                | Preço    | Limitações | Vantagens                     |
| ---------------------- | -------- | ---------- | ----------------------------- |
| **Netlify (Frontend)** | Gratuito | 100GB/mês  | Deploy automático, SSL        |
| **Render (Backend)**   | Gratuito | 750h/mês   | Deploy automático, PostgreSQL |

### **3. 💰 ECONÔMICO - DigitalOcean**

| Serviço     | Preço  | Limitações | Vantagens                    |
| ----------- | ------ | ---------- | ---------------------------- |
| **Droplet** | $5/mês | 1GB RAM    | Controle total, SSL gratuito |

## 🚀 Deploy na Vercel + Railway

### **Passo 1: Preparar o Repositório**

```bash
# 1. Fazer commit das alterações
git add .
git commit -m "Preparar para deploy"
git push origin main

# 2. Verificar se tudo está funcionando localmente
cd backend
mvn clean package
java -jar target/sistema-locacao-imoveis-1.0.0.jar

# 3. Em outro terminal
cd frontend
npm run build
```

### **Passo 2: Deploy do Backend no Railway**

1. **Acessar Railway**: https://railway.app
2. **Criar conta** com GitHub
3. **Criar novo projeto**
4. **Conectar repositório** do GitHub
5. **Configurar variáveis de ambiente**:

```env
# Variáveis de ambiente no Railway
DATABASE_URL=postgresql://user:pass@host:5432/dbname
JAVA_VERSION=11
PORT=8080
```

6. **Deploy automático** será iniciado

### **Passo 3: Deploy do Frontend na Vercel**

1. **Acessar Vercel**: https://vercel.com
2. **Criar conta** com GitHub
3. **Importar projeto** do GitHub
4. **Configurar variáveis de ambiente**:

```env
# Variáveis de ambiente no Vercel
REACT_APP_API_URL=https://seu-backend.railway.app
REACT_APP_ENV=production
```

5. **Deploy automático** será iniciado

## 🔧 Configurações Específicas

### **Backend - Railway**

#### **Arquivo `railway.json`**

```json
{
  "$schema": "https://railway.app/railway.schema.json",
  "build": {
    "builder": "NIXPACKS"
  },
  "deploy": {
    "startCommand": "java -jar target/sistema-locacao-imoveis-1.0.0.jar",
    "healthcheckPath": "/api/health",
    "healthcheckTimeout": 100,
    "restartPolicyType": "ON_FAILURE",
    "restartPolicyMaxRetries": 10
  }
}
```

#### **Dockerfile (Alternativo)**

```dockerfile
FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/sistema-locacao-imoveis-1.0.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

### **Frontend - Vercel**

#### **Arquivo `vercel.json`**

```json
{
  "version": 2,
  "builds": [
    {
      "src": "package.json",
      "use": "@vercel/static-build",
      "config": {
        "distDir": "build"
      }
    }
  ],
  "routes": [
    {
      "src": "/static/(.*)",
      "dest": "/static/$1"
    },
    {
      "src": "/(.*)",
      "dest": "/index.html"
    }
  ],
  "env": {
    "REACT_APP_API_URL": "https://seu-backend.railway.app"
  }
}
```

## 🌐 URLs de Acesso

Após o deploy, você terá:

- **Frontend**: `https://seu-projeto.vercel.app`
- **Backend**: `https://seu-projeto.railway.app`
- **API Docs**: `https://seu-projeto.railway.app/swagger-ui.html`

## 🔒 Configurações de Segurança

### **CORS (Cross-Origin Resource Sharing)**

```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("https://seu-frontend.vercel.app")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true);
        }
    };
}
```

### **SSL/HTTPS**

- ✅ **Vercel**: SSL automático
- ✅ **Railway**: SSL automático
- ✅ **Custom domains**: Suportado

## 📊 Monitoramento

### **Vercel Analytics**

- Visitas diárias
- Performance
- Erros de build

### **Railway Metrics**

- CPU/Memory usage
- Request logs
- Error tracking

## 🔄 Deploy Automático

### **GitHub Actions (Opcional)**

```yaml
# .github/workflows/deploy.yml
name: Deploy to Production

on:
  push:
    branches: [main]

jobs:
  deploy-backend:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Deploy to Railway
        uses: railway/deploy@v1
        with:
          railway_token: ${{ secrets.RAILWAY_TOKEN }}

  deploy-frontend:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Deploy to Vercel
        uses: amondnet/vercel-action@v20
        with:
          vercel-token: ${{ secrets.VERCEL_TOKEN }}
          vercel-org-id: ${{ secrets.ORG_ID }}
          vercel-project-id: ${{ secrets.PROJECT_ID }}
```

## 🐛 Solução de Problemas

### **Problemas Comuns**

#### **1. Build Failed no Railway**

```bash
# Verificar logs
railway logs

# Verificar Java version
java -version

# Verificar Maven
mvn --version
```

#### **2. CORS Errors**

```javascript
// No frontend, verificar URL da API
console.log(process.env.REACT_APP_API_URL);
```

#### **3. Database Connection**

```bash
# Verificar variáveis de ambiente
railway variables

# Testar conexão
railway run java -jar app.jar
```

### **Comandos Úteis**

```bash
# Railway CLI
npm install -g @railway/cli
railway login
railway link
railway up

# Vercel CLI
npm install -g vercel
vercel login
vercel --prod
```

## 💰 Custos Estimados

### **Opção Gratuita**

- **Vercel**: $0/mês
- **Railway**: $0/mês (500h)
- **Total**: $0/mês

### **Opção Paga**

- **Vercel**: $0/mês
- **Railway**: $5/mês
- **Total**: $5/mês

## 🎯 Próximos Passos

1. **Deploy inicial** seguindo este guia
2. **Configurar domínio personalizado**
3. **Implementar CI/CD**
4. **Adicionar monitoramento**
5. **Otimizar performance**

## 📞 Suporte

- **Vercel**: https://vercel.com/support
- **Railway**: https://railway.app/support
- **Documentação**: Este guia

---

**🎓 Desenvolvido para o curso MC322 da Unicamp**

**📧 Para dúvidas**: Consulte a documentação ou entre em contato com a equipe de desenvolvimento.
