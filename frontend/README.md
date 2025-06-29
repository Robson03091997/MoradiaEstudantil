# Frontend - Sistema de Locação de Imóveis

## 📋 Descrição

Este é o frontend do sistema de locação de imóveis desenvolvido em React. A aplicação oferece uma interface moderna e responsiva para gerenciar imóveis, usuários e transações de aluguel.

## 🚀 Funcionalidades

### Para Usuários

- **Cadastro e Login**: Sistema completo de autenticação
- **Busca de Imóveis**: Filtros por tipo, localização e preço
- **Visualização Detalhada**: Informações completas de cada imóvel
- **Favoritos**: Salvar imóveis de interesse
- **Perfil Completo**: Gerenciar dados pessoais

### Para Proprietários

- **Cadastro de Imóveis**: Formulário completo para novos anúncios
- **Gerenciamento**: Editar e remover imóveis cadastrados
- **Dashboard**: Visão geral dos imóveis e estatísticas
- **Relatórios**: Estatísticas detalhadas de visualizações

### Tipos de Imóveis Suportados

- 🏠 **Casas**: Residências completas
- 🏢 **Kitnets**: Apartamentos compactos
- 🛏️ **Quartos**: Quartos individuais
- 👥 **Repúblicas**: Moradia compartilhada
- 🏘️ **Pensionatos**: Com refeições inclusas
- 🏡 **Edículas**: Casas pequenas no fundo
- 🏬 **Prédios de Kitnets**: Múltiplas opções

## 🛠️ Tecnologias Utilizadas

- **React 18**: Framework principal
- **React Router**: Navegação entre páginas
- **Context API**: Gerenciamento de estado
- **CSS3**: Estilos modernos e responsivos
- **React Icons**: Ícones da aplicação
- **LocalStorage**: Persistência de dados

## 📦 Instalação

### Pré-requisitos

- Node.js (versão 14 ou superior)
- npm ou yarn

### Passos para Instalação

1. **Navegue para a pasta frontend**

   ```bash
   cd frontend
   ```

2. **Instale as dependências**

   ```bash
   npm install
   ```

3. **Execute o projeto**

   ```bash
   npm start
   ```

4. **Acesse a aplicação**
   Abra [http://localhost:3000](http://localhost:3000) no seu navegador.

## 🎯 Como Usar

### Primeiro Acesso

1. Acesse a página inicial
2. Clique em "Cadastrar" para criar uma conta
3. Preencha seus dados pessoais
4. Faça login com suas credenciais

### Contas de Demonstração

Para testar rapidamente, use uma das contas pré-cadastradas:

- **Email**: joao@email.com / **Senha**: 123
- **Email**: maria@email.com / **Senha**: 456
- **Email**: carlos@email.com / **Senha**: 789

### Navegando pelo Sistema

#### Como Usuário

1. **Buscar Imóveis**: Use a barra de busca na página inicial
2. **Filtrar**: Selecione o tipo de imóvel desejado
3. **Ver Detalhes**: Clique em qualquer imóvel para mais informações
4. **Contatar**: Entre em contato com o proprietário

#### Como Proprietário

1. **Anunciar**: Clique em "Anunciar" no menu
2. **Preencher Dados**: Complete todas as informações do imóvel
3. **Gerenciar**: Acesse "Meus Imóveis" para editar ou remover
4. **Acompanhar**: Veja estatísticas no Dashboard

## 📱 Responsividade

O sistema é totalmente responsivo e funciona perfeitamente em:

- 📱 Smartphones
- 📱 Tablets
- 💻 Desktops
- 🖥️ Monitores grandes

## 🎨 Design System

### Cores Principais

- **Azul Primário**: #3b82f6
- **Azul Escuro**: #2563eb
- **Verde**: #059669
- **Vermelho**: #dc2626
- **Cinza**: #6b7280

### Componentes

- **Botões**: Primário, Secundário e Outline
- **Cards**: Para exibição de imóveis
- **Formulários**: Validação e feedback visual
- **Modais**: Para ações importantes
- **Badges**: Para status e categorias

## 📊 Estrutura do Projeto

```
frontend/src/
├── components/          # Componentes reutilizáveis
│   ├── Header.js       # Cabeçalho da aplicação
│   └── Footer.js       # Rodapé da aplicação
├── contexts/           # Contextos React
│   ├── AuthContext.js  # Autenticação
│   └── PropertyContext.js # Gerenciamento de imóveis
├── pages/              # Páginas da aplicação
│   ├── Home.js         # Página inicial
│   ├── Login.js        # Login
│   ├── Register.js     # Cadastro
│   ├── Dashboard.js    # Painel do usuário
│   ├── PropertyList.js # Lista de imóveis
│   ├── PropertyDetail.js # Detalhes do imóvel
│   ├── AddProperty.js  # Adicionar imóvel
│   ├── MyProperties.js # Meus imóveis
│   └── Statistics.js   # Estatísticas
├── App.js              # Componente principal
├── App.css             # Estilos da aplicação
├── index.js            # Ponto de entrada
└── index.css           # Estilos globais
```

## 🔧 Scripts Disponíveis

```bash
# Executar em modo desenvolvimento
npm start

# Criar build de produção
npm run build

# Executar testes
npm test

# Ejetar configurações (irreversível)
npm run eject
```

## 🌟 Recursos Avançados

### Sistema de Busca

- Busca por endereço
- Filtros por tipo de imóvel
- Ordenação por preço
- Resultados em tempo real

### Dashboard Interativo

- Gráficos de estatísticas
- Resumo de imóveis
- Atividades recentes
- Métricas de performance

### Gerenciamento de Estado

- Context API para estado global
- LocalStorage para persistência
- Estados de loading e erro
- Validação de formulários

## 🔗 Integração com Backend

O frontend está preparado para integração com o backend Java, podendo:

- Consumir APIs REST
- Sincronizar dados em tempo real
- Implementar autenticação JWT
- Gerenciar uploads de imagens

## 🚀 Próximas Atualizações

- [ ] Integração com API backend
- [ ] Sistema de pagamentos
- [ ] Chat entre usuários
- [ ] Notificações push
- [ ] App mobile
- [ ] Sistema de avaliações
- [ ] Filtros avançados
- [ ] Mapa interativo

## 👥 Desenvolvimento

Este frontend foi desenvolvido como parte do curso MC322 da Unicamp, aplicando conceitos de:

- Desenvolvimento Web Moderno
- React Hooks e Context API
- Componentização
- Responsividade
- UX/UI Design

---

**Desenvolvido com ❤️ para facilitar a busca por moradia**
