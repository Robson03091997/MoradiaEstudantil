#!/bin/bash

# Script para iniciar o sistema completo no Linux/Mac
# Uso: ./start-system.sh

echo "🚀 Iniciando Sistema de Locação de Imóveis..."
echo "================================================"

# Verificar se estamos na pasta raiz do projeto
if [ ! -d "frontend" ] || [ ! -d "backend" ]; then
    echo "❌ Erro: Execute este script na pasta raiz do projeto (ProjetoMC322)"
    echo "Estrutura esperada:"
    echo "ProjetoMC322/"
    echo "├── frontend/"
    echo "├── backend/"
    echo "└── docs/"
    exit 1
fi

# Verificar pré-requisitos
echo "🔍 Verificando pré-requisitos..."

# Verificar Node.js
if command -v node &> /dev/null; then
    NODE_VERSION=$(node --version)
    echo "✅ Node.js: $NODE_VERSION"
else
    echo "❌ Node.js não encontrado. Instale em: https://nodejs.org/"
    exit 1
fi

# Verificar Java
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java --version 2>&1 | head -n 1)
    echo "✅ Java: $JAVA_VERSION"
else
    echo "❌ Java não encontrado. Instale JDK 11+ em: https://adoptium.net/"
    exit 1
fi

# Verificar Maven
if command -v mvn &> /dev/null; then
    MVN_VERSION=$(mvn --version 2>&1 | head -n 1)
    echo "✅ Maven: $MVN_VERSION"
else
    echo "❌ Maven não encontrado. Instale em: https://maven.apache.org/"
    exit 1
fi

echo ""
echo "📦 Configurando dependências..."

# Configurar frontend
echo "🟨 Configurando Frontend..."
cd frontend

if [ ! -d "node_modules" ]; then
    echo "Instalando dependências do frontend..."
    npm install
    if [ $? -ne 0 ]; then
        echo "❌ Erro ao instalar dependências do frontend"
        exit 1
    fi
else
    echo "✅ Dependências do frontend já instaladas"
fi

cd ..

# Configurar backend
echo "☕ Configurando Backend..."
cd backend

echo "Compilando backend..."
mvn clean compile
if [ $? -ne 0 ]; then
    echo "❌ Erro ao compilar backend"
    exit 1
fi

cd ..

echo ""
echo "🚀 Iniciando serviços..."

# Função para limpar processos ao sair
cleanup() {
    echo ""
    echo "🛑 Parando serviços..."
    pkill -f "npm start" 2>/dev/null
    pkill -f "mvn exec:java" 2>/dev/null
    echo "✅ Serviços parados"
    exit 0
}

# Capturar Ctrl+C para limpeza
trap cleanup SIGINT

# Iniciar backend em background
echo "☕ Iniciando Backend..."
cd backend
mvn exec:java > backend.log 2>&1 &
BACKEND_PID=$!
cd ..

# Aguardar um pouco para o backend inicializar
sleep 3

# Verificar se o backend iniciou
if ! ps -p $BACKEND_PID > /dev/null; then
    echo "❌ Erro ao iniciar backend. Verifique backend.log"
    exit 1
fi

# Iniciar frontend em background
echo "🟨 Iniciando Frontend..."
cd frontend
npm start > frontend.log 2>&1 &
FRONTEND_PID=$!
cd ..

# Aguardar um pouco para o frontend inicializar
sleep 5

# Verificar se o frontend iniciou
if ! ps -p $FRONTEND_PID > /dev/null; then
    echo "❌ Erro ao iniciar frontend. Verifique frontend.log"
    kill $BACKEND_PID 2>/dev/null
    exit 1
fi

echo ""
echo "✅ Sistema iniciado com sucesso!"
echo "================================================"
echo "🌐 Frontend: http://localhost:3000"
echo "☕ Backend: http://localhost:8080"
echo "🗄️ Banco: locacao_imoveis.db"
echo ""
echo "📋 Logs disponíveis em:"
echo "   - Backend: backend/backend.log"
echo "   - Frontend: frontend/frontend.log"
echo ""
echo "📋 Para parar os serviços:"
echo "   - Pressione Ctrl+C"
echo "   - Ou use: pkill -f 'npm start' && pkill -f 'mvn exec:java'"
echo ""
echo "🎓 Sistema de Locação de Imóveis - MC322"

# Manter script rodando
while true; do
    sleep 1
done 