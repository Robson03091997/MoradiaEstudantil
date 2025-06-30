# Fluxograma da Estrutura Orientada a Objetos - Sistema de Locação de Imóveis

## 🏗️ Visão Geral da Arquitetura OO

Este documento apresenta um fluxograma visual da estrutura orientada a objetos do Sistema de Locação de Imóveis, mostrando as classes, heranças, interfaces e relacionamentos.

## 📊 Diagrama de Classes Principal

```mermaid
classDiagram
    %% Classe Abstrata Base
    class Local {
        <<abstract>>
        -String endereco
        -List~Perfil~ proprietarios
        -List~Perfil~ moradores
        -String id
        -int numeroBanheiros
        -double areaConstruida
        -int numeroMaximoInquilinos
        -boolean disponivel
        -double valorAluguel
        -boolean compartilhado
        -int numeroInquilinos
        -TipoCompartilhamento tipoCompartilhamento
        -String descricao
        -List~FormaCaucao~ formasCaucao
        -boolean aceitaSeguro
        -double valorSeguro
        -Perfil responsavel
        +Local(...)
        +getTipoLocal()* String
        +getValorPorPessoa() double
        +temVagasDisponiveis() boolean
        +getVagasDisponiveis() int
        +getValorTotalInicial() double
        +aceitaFormaCaucao(FormaCaucao) boolean
    }

    %% Enumerações
    class TipoCompartilhamento {
        <<enumeration>>
        MASCULINO
        FEMININO
        MISTO
    }

    class FormaCaucao {
        <<enumeration>>
        FIADOR
        DEPOSITO
        SEGURO
        CALCAO
        SEM_CAUCAO
    }

    %% Classe Casa e suas subclasses
    class Casa {
        -int numeroQuartos
        -int vagasGaragem
        +Casa(...)
        +getNumeroQuartos() int
        +getVagasGaragem() int
        +getValorPorQuarto() double
        +getTipoLocal() String
    }

    class Republica {
        -boolean temCozinhaCompartilhada
        -boolean temInternet
        +Republica(...)
        +isTemCozinhaCompartilhada() boolean
        +isTemInternet() boolean
        +getTipoLocal() String
    }

    class Pensionato {
        -boolean temRefeicoesInclusas
        -boolean temLimpezaInclusa
        -boolean temHorarioFechamento
        -boolean temRegrasRigorosas
        +Pensionato(...)
        +isTemRefeicoesInclusas() boolean
        +isTemLimpezaInclusa() boolean
        +isTemHorarioFechamento() boolean
        +isTemRegrasRigorosas() boolean
        +getValorPorQuartoComServicos() double
        +getTipoLocal() String
    }

    %% Outras classes de Local
    class Kitnet {
        -boolean temCozinha
        -boolean temEntradaIndependente
        +Kitnet(...)
        +isTemCozinha() boolean
        +isTemEntradaIndependente() boolean
        +getValorPorMetroQuadrado() double
        +getTipoLocal() String
    }

    class Quarto {
        -boolean temArmario
        -boolean temVentilador
        -boolean temArCondicionado
        +Quarto(...)
        +isTemArmario() boolean
        +isTemVentilador() boolean
        +isTemArCondicionado() boolean
        +getTipoLocal() String
    }

    class Edicula {
        -boolean temCozinha
        -boolean temEntradaIndependente
        +Edicula(...)
        +isTemCozinha() boolean
        +isTemEntradaIndependente() boolean
        +getTipoLocal() String
    }

    class PredioKit {
        -int numeroAndares
        -int numeroKitnets
        -boolean temElevador
        -boolean temPortaria
        +PredioKit(...)
        +getNumeroAndares() int
        +getNumeroKitnets() int
        +isTemElevador() boolean
        +isTemPortaria() boolean
        +getValorPorKitnet() double
        +getTipoLocal() String
    }

    %% Interface Vaga
    class Vaga {
        <<interface>>
    }

    %% Classe Perfil
    class Perfil {
        -String nome
        -String email
        -String telefone
        -String id
        +Perfil(String, String, String)
        +getNome() String
        +getEmail() String
        +getTelefone() String
        +getId() String
        +equals(Object) boolean
        +hashCode() int
    }

    %% Classe Repositório (Singleton)
    class RepositorioLocais {
        -static RepositorioLocais instancia
        -Map~String, Local~ locais
        -RepositorioLocais()
        +getInstancia() RepositorioLocais
        +adicionarLocal(Local) void
        +removerLocal(String) boolean
        +buscarPorId(String) Local
        +buscarPorEndereco(String) List~Local~
        +buscarPorTipo(String) List~Local~
        +listarTodos() List~Local~
        +getQuantidadeLocais() int
        +getEstatisticasPorTipo() Map~String, Long~
        +getLocaisDisponiveis() List~Local~
        +getLocaisOcupados() List~Local~
    }

    %% Classe Sistema Principal
    class SistemaLocacao {
        -static Scanner scanner
        -static RepositorioLocais repositorio
        -static List~Perfil~ usuarios
        -static Perfil usuarioLogado
        +main(String[]) void
        -mostrarMenuPrincipal() void
        -mostrarMenuUsuario() void
        -fazerLogin() void
        -criarPerfil() void
        -verImoveisDisponiveis() void
        -disponibilizarImovel() void
        -meusImoveis() void
        -buscarPorTipo() void
        -mostrarEstatisticas() void
    }

    %% Relacionamentos de Herança
    Local <|-- Casa
    Local <|-- Kitnet
    Local <|-- Quarto
    Local <|-- Edicula
    Local <|-- PredioKit
    Casa <|-- Republica
    Casa <|-- Pensionato

    %% Relacionamentos de Implementação
    Kitnet ..|> Vaga
    Quarto ..|> Vaga
    Edicula ..|> Vaga
    PredioKit ..|> Vaga

    %% Relacionamentos de Composição
    Local *-- TipoCompartilhamento
    Local *-- FormaCaucao
    Local *-- Perfil
    RepositorioLocais *-- Local
    SistemaLocacao *-- RepositorioLocais
    SistemaLocacao *-- Perfil

    %% Relacionamentos de Agregação
    Local o-- Perfil : proprietarios
    Local o-- Perfil : moradores
    Local o-- Perfil : responsavel
```

## 🔄 Fluxo de Execução do Sistema

```mermaid
flowchart TD
    A[Início do Sistema] --> B[SistemaLocacao.main]
    B --> C[Inicializar Dados Exemplo]
    C --> D[Mostrar Menu Principal]

    D --> E{Opção Escolhida}
    E -->|1. Login| F[Fazer Login]
    E -->|2. Criar Perfil| G[Criar Perfil]
    E -->|3. Sair| H[Encerrar Sistema]

    F --> I{Login Válido?}
    I -->|Sim| J[Menu do Usuário]
    I -->|Não| K[Erro de Login]
    K --> D

    G --> L[Adicionar Usuário]
    L --> D

    J --> M{Opção do Usuário}
    M -->|1. Ver Imóveis| N[Listar Imóveis Disponíveis]
    M -->|2. Disponibilizar| O[Cadastrar Novo Imóvel]
    M -->|3. Meus Imóveis| P[Mostrar Imóveis do Usuário]
    M -->|4. Buscar por Tipo| Q[Filtrar por Tipo]
    M -->|5. Estatísticas| R[Mostrar Estatísticas]
    M -->|6. Logout| S[Voltar ao Menu Principal]

    N --> T[RepositorioLocais.listarTodos]
    T --> U[Mostrar Detalhes]
    U --> J

    O --> V[Criar Objeto Local]
    V --> W[RepositorioLocais.adicionarLocal]
    W --> J

    P --> X[Filtrar por Proprietário]
    X --> J

    Q --> Y[RepositorioLocais.buscarPorTipo]
    Y --> J

    R --> Z[RepositorioLocais.getEstatisticasPorTipo]
    Z --> J

    S --> D
```

## 🏠 Hierarquia de Tipos de Imóveis

```mermaid
graph TD
    A[Local - Classe Abstrata] --> B[Casa]
    A --> C[Kitnet]
    A --> D[Quarto]
    A --> E[Edicula]
    A --> F[PredioKit]

    B --> G[Republica]
    B --> H[Pensionato]

    C --> I[Implementa Vaga]
    D --> I
    E --> I
    F --> I

    style A fill:#e1f5fe
    style B fill:#f3e5f5
    style G fill:#fff3e0
    style H fill:#fff3e0
    style I fill:#e8f5e8
```

## 🔐 Padrões de Design Utilizados

### 1. **Singleton Pattern**

```java
// RepositorioLocais - Garante uma única instância
public class RepositorioLocais {
    private static RepositorioLocais instancia;

    public static RepositorioLocais getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioLocais();
        }
        return instancia;
    }
}
```

### 2. **Template Method Pattern**

```java
// Local - Método abstrato
public abstract class Local {
    public abstract String getTipoLocal();

    // Método template que usa o método abstrato
    public String getDescricaoCompleta() {
        return getTipoLocal() + " - " + getEndereco();
    }
}
```

### 3. **Strategy Pattern**

```java
// Enum FormaCaucao - Diferentes estratégias de caução
public enum FormaCaucao {
    FIADOR, DEPOSITO, SEGURO, CALCAO, SEM_CAUCAO
}
```

## 📋 Atributos e Métodos Principais

### **Classe Local (Abstrata)**

- **Atributos**: endereco, proprietarios, moradores, areaConstruida, valorAluguel
- **Métodos**: getTipoLocal()\*, getValorPorPessoa(), temVagasDisponiveis()

### **Classe Casa**

- **Atributos**: numeroQuartos, vagasGaragem
- **Métodos**: getValorPorQuarto(), getTipoLocal()

### **Classe Republica**

- **Atributos**: temCozinhaCompartilhada, temInternet
- **Métodos**: isTemCozinhaCompartilhada(), isTemInternet()

### **Classe Pensionato**

- **Atributos**: temRefeicoesInclusas, temLimpezaInclusa, temHorarioFechamento
- **Métodos**: getValorPorQuartoComServicos()

## 🔗 Relacionamentos entre Classes

### **Herança (extends)**

- `Casa extends Local`
- `Republica extends Casa`
- `Pensionato extends Casa`
- `Kitnet extends Local`
- `Quarto extends Local`
- `Edicula extends Local`
- `PredioKit extends Local`

### **Implementação (implements)**

- `Kitnet implements Vaga`
- `Quarto implements Vaga`
- `Edicula implements Vaga`
- `PredioKit implements Vaga`

### **Composição (has-a)**

- `Local has TipoCompartilhamento`
- `Local has FormaCaucao`
- `Local has Perfil (responsavel)`
- `RepositorioLocais has Local`

### **Agregação (uses)**

- `Local uses Perfil (proprietarios, moradores)`
- `SistemaLocacao uses RepositorioLocais`
- `SistemaLocacao uses Perfil`

## 🎯 Princípios OO Aplicados

### **1. Encapsulamento**

- Atributos privados com getters/setters
- Controle de acesso aos dados

### **2. Herança**

- Hierarquia clara de tipos de imóveis
- Reutilização de código comum

### **3. Polimorfismo**

- Método `getTipoLocal()` implementado diferentemente
- Uso de `instanceof` para comportamentos específicos

### **4. Abstração**

- Classe `Local` abstrata define interface comum
- Interface `Vaga` para comportamentos específicos

## 📊 Estatísticas da Estrutura OO

- **Total de Classes**: 12
- **Classes Abstratas**: 1 (Local)
- **Interfaces**: 1 (Vaga)
- **Enums**: 2 (TipoCompartilhamento, FormaCaucao)
- **Níveis de Herança**: 3 (Local → Casa → Republica/Pensionato)
- **Padrões de Design**: 3 (Singleton, Template Method, Strategy)

---

**🎓 Desenvolvido para o curso MC322 da Unicamp**

**📧 Para dúvidas**: Consulte a documentação ou entre em contato com a equipe de desenvolvimento.
