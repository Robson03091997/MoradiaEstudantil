#!/bin/bash

# Script de Deploy GRATUITO - Netlify + Render
# Uso: ./deploy-gratuito.sh

echo "🆓 Iniciando Deploy GRATUITO - Netlify + Render"
echo "================================================"
echo "💰 Custo: $0/mês - 100% GRATUITO!"
echo ""

# Verificar se estamos na pasta raiz do projeto
if [ ! -d "frontend" ] || [ ! -d "backend" ]; then
    echo "❌ Erro: Execute este script na pasta raiz do projeto (ProjetoMC322)"
    exit 1
fi

# Verificar pré-requisitos
echo "🔍 Verificando pré-requisitos..."

# Verificar Git
if ! command -v git &> /dev/null; then
    echo "❌ Git não encontrado. Instale em: https://git-scm.com/"
    exit 1
fi

# Verificar se é um repositório Git
if [ ! -d ".git" ]; then
    echo "❌ Não é um repositório Git. Inicializando..."
    git init
    git add .
    git commit -m "Primeiro commit - Deploy gratuito"
    echo "✅ Repositório Git inicializado"
fi

echo "✅ Pré-requisitos verificados"
echo ""

# Build do Backend
echo "☕ Build do Backend (Render)..."
cd backend

# Limpar e compilar
echo "Compilando backend..."
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo "❌ Erro ao compilar backend"
    exit 1
fi

echo "✅ Backend compilado com sucesso"
cd ..

# Build do Frontend
echo "🟨 Build do Frontend (Netlify)..."
cd frontend

# Instalar dependências se necessário
if [ ! -d "node_modules" ]; then
    echo "Instalando dependências..."
    npm install
    if [ $? -ne 0 ]; then
        echo "❌ Erro ao instalar dependências"
        exit 1
    fi
fi

# Build de produção
echo "Gerando build de produção..."
npm run build
if [ $? -ne 0 ]; then
    echo "❌ Erro ao gerar build"
    exit 1
fi

echo "✅ Frontend buildado com sucesso"
cd ..

# Commit e Push
echo "📝 Commit e Push..."
git add .
git commit -m "Deploy gratuito - $(date '+%Y-%m-%d %H:%M:%S')"
git push origin main

if [ $? -ne 0 ]; then
    echo "❌ Erro ao fazer push"
    exit 1
fi

echo "✅ Código enviado para o repositório"
echo ""

# Instruções finais
echo "🎉 Deploy GRATUITO iniciado com sucesso!"
echo "================================================"
echo ""
echo "📋 Próximos passos:"
echo ""
echo "1. 🚂 Render (Backend) - GRATUITO:"
echo "   - Acesse: https://render.com"
echo "   - Crie conta com GitHub"
echo "   - Clique em 'New +' → 'Web Service'"
echo "   - Conecte seu repositório"
echo "   - Configure:"
echo "     Name: sistema-locacao-backend"
echo "     Environment: Java"
echo "     Root Directory: backend"
echo "     Build Command: mvn clean package -DskipTests"
echo "     Start Command: java -jar target/sistema-locacao-imoveis-1.0.0.jar"
echo "   - Variáveis de ambiente:"
echo "     JAVA_VERSION=11"
echo "     PORT=8080"
echo "     SPRING_PROFILES_ACTIVE=production"
echo ""
echo "2. 🌐 Netlify (Frontend) - GRATUITO:"
echo "   - Acesse: https://netlify.com"
echo "   - Crie conta com GitHub"
echo "   - Clique em 'New site from Git'"
echo "   - Conecte seu repositório"
echo "   - Configure:"
echo "     Base directory: frontend"
echo "     Build command: npm run build"
echo "     Publish directory: build"
echo "   - Variáveis de ambiente:"
echo "     REACT_APP_API_URL=https://seu-backend.onrender.com"
echo "     REACT_APP_ENV=production"
echo ""
echo "3. 🔗 URLs finais (GRATUITAS):"
echo "   - Frontend: https://seu-frontend.netlify.app"
echo "   - Backend: https://seu-backend.onrender.com"
echo "   - Health Check: https://seu-backend.onrender.com/api/health"
echo ""
echo "💰 CUSTO TOTAL: $0/mês"
echo "✅ 100% GRATUITO!"
echo ""
echo "📚 Para mais detalhes, consulte: docs/GUIA_DEPLOY_GRATUITO.md"
echo ""
echo "🎓 Sistema de Locação de Imóveis - MC322"
echo "🆓 Deploy Gratuito - Netlify + Render" 