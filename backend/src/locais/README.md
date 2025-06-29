# Sistema de Gerenciamento de Locais para Aluguel

Sistema orientado a objetos para gerenciar diferentes tipos de locais que podem ser alugados.

## Estrutura do Sistema

### Classe Base: `Local`

Classe abstrata com atributos comuns a todos os tipos de locais:

**Atributos:**

- `endereco`: Endereço do local
- `numeroBanheiros`: Número de banheiros
- `areaConstruida`: Área construída em m²
- `numeroMaximoInquilinos`: Capacidade máxima de inquilinos
- `valorAluguel`: Valor do aluguel
- `compartilhado`: Se o local é compartilhado
- `numeroInquilinos`: Número atual de inquilinos
- `tipoCompartilhamento`: Tipo de compartilhamento (MASCULINO, FEMININO, MISTO)
- `descricao`: Descrição do local
- `formasCaucao`: Lista de formas de caução aceitas
- `aceitaSeguro`: Se aceita seguro
- `valorSeguro`: Valor do seguro
- `responsavel`: Perfil do responsável pelo local

### Classes Específicas

#### 1. `Casa`

**Atributos:**

- `numeroQuartos`: Número de quartos
- `vagasGaragem`: Número de vagas de garagem

**Métodos:**

- `getValorPorQuarto()`: Calcula valor por quarto

#### 2. `Kitnet`

**Atributos:**

- `temCozinha`: Se possui cozinha
- `temSacada`: Se possui sacada

**Métodos:**

- `getValorPorMetroQuadrado()`: Calcula valor por m²

#### 3. `Quarto`

**Atributos:**

- `temBanheiroPrivativo`: Se possui banheiro privativo
- `temArCondicionado`: Se possui ar condicionado

**Métodos:**

- `getValorPorMetroQuadrado()`: Calcula valor por m²

#### 4. `Republica` (herda de Casa)

**Atributos:**

- `temCozinhaCompartilhada`: Se possui cozinha compartilhada
- `temInternet`: Se possui internet

**Métodos:**

- Herda todos os métodos de Casa
- `getValorPorQuarto()`: Calcula valor por quarto

#### 5. `Pensionato` (herda de Casa)

**Atributos:**

- `temRefeicoesInclusas`: Se possui refeições inclusas
- `temLimpezaInclusa`: Se possui limpeza inclusa
- `temHorarioFechamento`: Se possui horário de fechamento
- `temRegrasRigorosas`: Se possui regras rigorosas

**Métodos:**

- Herda todos os métodos de Casa
- `getValorPorQuarto()`: Calcula valor por quarto
- `getValorPorQuartoComServicos()`: Calcula valor incluindo serviços

#### 6. `Edicula`

**Atributos:**

- `temCozinha`: Se possui cozinha
- `temEntradaIndependente`: Se possui entrada independente

**Métodos:**

- `getValorPorMetroQuadrado()`: Calcula valor por m²

#### 7. `PredioKit`

**Atributos:**

- `numeroAndares`: Número de andares
- `numeroKitnets`: Número de kitnets
- `temElevador`: Se possui elevador
- `temPortaria`: Se possui portaria

**Métodos:**

- `getValorPorKitnet()`: Calcula valor por kitnet

### Classe `Perfil`

Representa proprietários, moradores e responsáveis:

- `nome`: Nome da pessoa
- `email`: Email de contato
- `telefone`: Telefone de contato

### Classe `RepositorioLocais`

Singleton para gerenciar todos os locais:

- `adicionarLocal(Local local)`: Adiciona um local
- `removerLocal(Local local)`: Remove um local
- `buscarPorEndereco(String endereco)`: Busca por endereço
- `listarTodos()`: Lista todos os locais
- `getQuantidadeLocais()`: Retorna quantidade total
- `getEstatisticasPorTipo()`: Estatísticas por tipo de local

## Hierarquia de Herança

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

## Exemplos de Uso

### Criando uma Casa

```java
Casa casa = new Casa(
    "Rua das Flores, 123 - Centro",  // endereco
    2,                              // numeroBanheiros
    120.0,                          // areaConstruida
    3,                              // numeroMaximoInquilinos
    2500.0,                         // valorAluguel
    3,                              // numeroQuartos
    1                               // vagasGaragem
);
```

### Criando uma República (herda de Casa)

```java
Republica republica = new Republica(
    "Rua dos Estudantes, 321 - Vila Nova", // endereco
    2,                                     // numeroBanheiros
    100.0,                                 // areaConstruida
    5,                                     // numeroMaximoInquilinos
    2500.0,                                // valorAluguel
    5,                                     // numeroQuartos
    0,                                     // vagasGaragem
    true,                                  // temCozinhaCompartilhada
    true                                   // temInternet
);
```

### Criando um Pensionato (herda de Casa)

```java
Pensionato pensionato = new Pensionato(
    "Rua dos Estudantes, 654 - Vila Nova", // endereco
    2,                                     // numeroBanheiros
    120.0,                                 // areaConstruida
    6,                                     // numeroMaximoInquilinos
    3000.0,                                // valorAluguel
    6,                                     // numeroQuartos
    0,                                     // vagasGaragem
    true,                                  // temRefeicoesInclusas
    true,                                  // temLimpezaInclusa
    true,                                  // temHorarioFechamento
    true                                   // temRegrasRigorosas
);
```

### Criando uma Kitnet

```java
Kitnet kitnet = new Kitnet(
1. **Simplicidade**: Apenas atributos essenciais para cada tipo
2. **Flexibilidade**: Múltiplos construtores para diferentes cenários
3. **Funcionalidades Específicas**: Métodos específicos para cada tipo
4. **Gerenciamento Centralizado**: Repositório singleton para todos os locais
5. **Suporte a Compartilhamento**: Sistema de compartilhamento
6. **Caução e Seguro**: Múltiplas formas de caução e seguro
7. **Responsáveis**: Sistema de responsáveis por local
8. **Estatísticas**: Relatórios por tipo

O sistema está pronto para integração com backend web.
```
