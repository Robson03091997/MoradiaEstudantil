# Sistema de Locação de Imóveis - Instruções de Execução

## Como Executar o Sistema

### 1. Executar a Interface Principal

Para usar o sistema com interface de console:

```bash
# Compilar
javac -cp src src/sistema/SistemaLocacao.java

# Executar
java -cp src sistema.SistemaLocacao
```

### 2. Executar Exemplos

Para ver exemplos completos do sistema:

```bash
# Exemplo simplificado
javac -cp src src/locais/ExemploSimplificado.java
java -cp src locais.ExemploSimplificado

# Exemplo completo
javac -cp src src/sistema/ExemploSistemaCompleto.java
java -cp src sistema.ExemploSistemaCompleto
```

## Funcionalidades do Sistema

### Interface Principal (`SistemaLocacao.java`)

#### **Menu Principal:**

1. **Fazer Login** - Acessar com email e senha
2. **Criar Perfil** - Cadastrar novo usuário
3. **Sair** - Encerrar o sistema

#### **Menu do Usuário:**

1. **Ver Imóveis Disponíveis** - Listar todos os imóveis
2. **Disponibilizar Imóvel** - Cadastrar novo imóvel
3. **Meus Imóveis** - Ver imóveis do usuário logado
4. **Buscar por Tipo** - Filtrar por tipo de imóvel
5. **Estatísticas** - Ver estatísticas do sistema
6. **Fazer Logout** - Sair da conta

### Usuários de Exemplo

O sistema vem com 3 usuários pré-cadastrados:

- **Email:** joao@email.com / **Senha:** 123
- **Email:** maria@email.com / **Senha:** 456
- **Email:** carlos@email.com / **Senha:** 789

### Tipos de Imóveis Disponíveis

#### **1. Casa**

- Quartos e vagas de garagem
- Individual ou compartilhada
- Herda de Local

#### **2. República** (herda de Casa)

- Casa compartilhada por estudantes
- Cozinha compartilhada e internet
- Ambiente mais informal

#### **3. Pensionato** (herda de Casa)

- Casa com rigor maior
- Refeições e limpeza inclusas
- Horário de fechamento e regras rigorosas

#### **4. Kitnet**

- Apartamento pequeno
- Cozinha e sacada
- Individual ou compartilhada

#### **5. Quarto**

- Quarto individual
- Banheiro privativo e ar condicionado
- Individual ou compartilhado

#### **6. Edícula**

- Casa pequena no fundo
- Cozinha e entrada independente
- Individual ou compartilhada

#### **7. Prédio de Kitnets**

- Prédio com múltiplas kitnets
- Elevador e portaria
- Estrutura completa

### Funcionalidades Específicas

#### **Cálculos Automáticos:**

- Valor por quarto (Casa, República, Pensionato)
- Valor por metro quadrado (Kitnet, Quarto, Edícula)
- Valor por kitnet (Prédio de Kitnets)
- Valor por pessoa (locais compartilhados)

#### **Sistema de Compartilhamento:**

- Tipos: MASCULINO, FEMININO, MISTO
- Número de inquilinos
- Valor por pessoa

#### **Sistema de Caução:**

- FIADOR
- DEPÓSITO
- SEGURO
- SEM_CAUCAO

#### **Gerenciamento de Perfis:**

- Proprietários
- Moradores
- Responsáveis

### Exemplos de Uso

#### **Criar uma Casa:**

```
Tipo: 1 (Casa)
Endereço: Rua das Flores, 123 - Centro
Banheiros: 2
Área: 120.0 m²
Inquilinos: 3
Valor: R$ 2500.0
Quartos: 3
Garagem: 1
```

#### **Criar uma República:**

```
Tipo: 2 (República)
Endereço: Rua dos Estudantes, 321 - Vila Nova
Banheiros: 2
Área: 100.0 m²
Inquilinos: 5
Valor: R$ 2500.0
Quartos: 5
Garagem: 0
Cozinha compartilhada: Sim
Internet: Sim
```

#### **Criar um Pensionato:**

```
Tipo: 3 (Pensionato)
Endereço: Rua dos Estudantes, 654 - Vila Nova
Banheiros: 2
Área: 120.0 m²
Inquilinos: 6
Valor: R$ 3000.0
Quartos: 6
Garagem: 0
Refeições inclusas: Sim
Limpeza inclusa: Sim
Horário de fechamento: Sim
Regras rigorosas: Sim
```

### Estrutura de Arquivos

```
src/
├── locais/
│   ├── Local.java (classe abstrata)
│   ├── Casa.java
│   ├── Republica.java (herda de Casa)
│   ├── Pensionato.java (herda de Casa)
│   ├── Kitnet.java
│   ├── Quarto.java
│   ├── Edicula.java
│   ├── PredioKit.java
│   ├── RepositorioLocais.java
│   ├── ExemploSimplificado.java
│   └── README.md
├── perfil/
│   └── Perfil.java
└── sistema/
    ├── SistemaLocacao.java (interface principal)
    └── ExemploSistemaCompleto.java
```

### Hierarquia de Herança

```
Local (abstrata)
├── Casa
│   ├── Republica
│   └── Pensionato
├── Kitnet
├── Quarto
├── Edicula
└── PredioKit
```

### Características do Sistema

1. **Simplicidade** - Apenas atributos essenciais
2. **Herança Lógica** - República e Pensionato herdam de Casa
3. **Flexibilidade** - Múltiplos construtores
4. **Funcionalidades Específicas** - Métodos específicos por tipo
5. **Gerenciamento Centralizado** - Repositório singleton
6. **Suporte a Compartilhamento** - Sistema completo
7. **Caução e Seguro** - Múltiplas formas
8. **Responsáveis** - Sistema de responsáveis
9. **Estatísticas** - Relatórios por tipo

O sistema está pronto para integração com backend web e oferece uma base sólida para gerenciamento de locais para aluguel.
