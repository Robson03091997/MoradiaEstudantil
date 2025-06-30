#!/bin/bash

# Script de Deploy Automatizado - Sistema de Locação de Imóveis
# Uso: ./deploy.sh

echo "🚀 Iniciando Deploy do Sistema de Locação de Imóveis..."
echo "=================================================="

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
    echo "❌ Não é um repositório Git. Execute: git init"
    exit 1
fi

echo "✅ Pré-requisitos verificados"
echo ""

# Build do Backend
echo "☕ Build do Backend..."
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
echo "🟨 Build do Frontend..."
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
git commit -m "Deploy automático - $(date '+%Y-%m-%d %H:%M:%S')"
git push origin main

if [ $? -ne 0 ]; then
    echo "❌ Erro ao fazer push"
    exit 1
fi

echo "✅ Código enviado para o repositório"
echo ""

# Instruções finais
echo "🎉 Deploy iniciado com sucesso!"
echo "=================================================="
echo ""
echo "📋 Próximos passos:"
echo ""
echo "1. 🌐 Vercel (Frontend):"
echo "   - Acesse: https://vercel.com"
echo "   - Conecte seu repositório GitHub"
echo "   - Configure variáveis de ambiente:"
echo "     REACT_APP_API_URL=https://seu-backend.railway.app"
echo ""
echo "2. 🚂 Railway (Backend):"
echo "   - Acesse: https://railway.app"
echo "   - Conecte seu repositório GitHub"
echo "   - Configure variáveis de ambiente:"
echo "     DATABASE_URL=postgresql://..."
echo "     JAVA_VERSION=11"
echo "     PORT=8080"
echo ""
echo "3. 🔗 URLs finais:"
echo "   - Frontend: https://seu-projeto.vercel.app"
echo "   - Backend: https://seu-projeto.railway.app"
echo ""
echo "📚 Para mais detalhes, consulte: docs/GUIA_DEPLOY.md"
echo ""
echo "🎓 Sistema de Locação de Imóveis - MC322" 