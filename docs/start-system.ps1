# Script para iniciar o sistema completo no Windows
# Uso: .\start-system.ps1

Write-Host "🚀 Iniciando Sistema de Locação de Imóveis..." -ForegroundColor Green
Write-Host "================================================" -ForegroundColor Green

# Verificar se estamos na pasta raiz do projeto
if (-not (Test-Path "frontend") -or -not (Test-Path "backend")) {
    Write-Host "❌ Erro: Execute este script na pasta raiz do projeto (ProjetoMC322)" -ForegroundColor Red
    Write-Host "Estrutura esperada:" -ForegroundColor Yellow
    Write-Host "ProjetoMC322/" -ForegroundColor Yellow
    Write-Host "├── frontend/" -ForegroundColor Yellow
    Write-Host "├── backend/" -ForegroundColor Yellow
    Write-Host "└── docs/" -ForegroundColor Yellow
    exit 1
}

# Verificar pré-requisitos
Write-Host "🔍 Verificando pré-requisitos..." -ForegroundColor Cyan

# Verificar Node.js
try {
    $nodeVersion = node --version
    Write-Host "✅ Node.js: $nodeVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Node.js não encontrado. Instale em: https://nodejs.org/" -ForegroundColor Red
    exit 1
}

# Verificar Java
try {
    $javaVersion = java --version 2>&1 | Select-Object -First 1
    Write-Host "✅ Java: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Java não encontrado. Instale JDK 11+ em: https://adoptium.net/" -ForegroundColor Red
    exit 1
}

# Verificar Maven
try {
    $mvnVersion = mvn --version 2>&1 | Select-Object -First 1
    Write-Host "✅ Maven: $mvnVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Maven não encontrado. Instale em: https://maven.apache.org/" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "📦 Configurando dependências..." -ForegroundColor Cyan

# Configurar frontend
Write-Host "🟨 Configurando Frontend..." -ForegroundColor Yellow
Set-Location frontend

if (-not (Test-Path "node_modules")) {
    Write-Host "Instalando dependências do frontend..." -ForegroundColor Yellow
    npm install
    if ($LASTEXITCODE -ne 0) {
        Write-Host "❌ Erro ao instalar dependências do frontend" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "✅ Dependências do frontend já instaladas" -ForegroundColor Green
}

Set-Location ..

# Configurar backend
Write-Host "☕ Configurando Backend..." -ForegroundColor Yellow
Set-Location backend

Write-Host "Compilando backend..." -ForegroundColor Yellow
mvn clean compile
if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ Erro ao compilar backend" -ForegroundColor Red
    exit 1
}

Set-Location ..

Write-Host ""
Write-Host "🚀 Iniciando serviços..." -ForegroundColor Cyan

# Iniciar backend em nova janela
Write-Host "☕ Iniciando Backend..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd backend; Write-Host '☕ Backend iniciado em:' -ForegroundColor Green; Write-Host 'http://localhost:8080' -ForegroundColor Cyan; mvn exec:java"

# Aguardar um pouco para o backend inicializar
Start-Sleep -Seconds 3

# Iniciar frontend em nova janela
Write-Host "🟨 Iniciando Frontend..." -ForegroundColor Yellow
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd frontend; Write-Host '🟨 Frontend iniciado em:' -ForegroundColor Green; Write-Host 'http://localhost:3000' -ForegroundColor Cyan; npm start"

Write-Host ""
Write-Host "✅ Sistema iniciado com sucesso!" -ForegroundColor Green
Write-Host "================================================" -ForegroundColor Green
Write-Host "🌐 Frontend: http://localhost:3000" -ForegroundColor Cyan
Write-Host "☕ Backend: http://localhost:8080" -ForegroundColor Cyan
Write-Host "🗄️ Banco: locacao_imoveis.db" -ForegroundColor Cyan
Write-Host ""
Write-Host "📋 Para parar os serviços:" -ForegroundColor Yellow
Write-Host "   - Feche as janelas do PowerShell" -ForegroundColor Yellow
Write-Host "   - Ou use: pkill -f 'npm start' && pkill -f 'mvn exec:java'" -ForegroundColor Yellow
Write-Host ""
Write-Host "🎓 Sistema de Locação de Imóveis - MC322" -ForegroundColor Magenta 