package locais;

import perfil.Perfil;
import java.util.List;
import java.util.ArrayList;

public class ExemploSimplificado {
    public static void main(String[] args) {
        // Obter instância do repositório
        RepositorioLocais repositorio = RepositorioLocais.getInstancia();
        
        // Criar perfis básicos
        Perfil proprietario = new Perfil("João Silva", "joao@email.com", "(11) 99999-9999");
        Perfil responsavel = new Perfil("Carlos Oliveira", "carlos@email.com", "(11) 77777-7777");
        
        // 1. CASA - com quartos e garagem
        Casa casa = new Casa(
            "Rua das Flores, 123 - Centro",  // endereco
            2,                              // numeroBanheiros
            120.0,                          // areaConstruida
            3,                              // numeroMaximoInquilinos
            2500.0,                         // valorAluguel
            3,                              // numeroQuartos
            1                               // vagasGaragem
        );
        
        // 2. KITNET - com cozinha e sacada
        Kitnet kitnet = new Kitnet(
            "Av. Principal, 456 - Jardim",  // endereco
            35.0,                           // areaConstruida
            1200.0,                         // valorAluguel
            true,                           // temCozinha
            false                           // temSacada
        );
        
        // 3. QUARTO - com banheiro privativo
        Quarto quarto = new Quarto(
            "Rua da Universidade, 789 - Campus", // endereco
            15.0,                                // areaConstruida
            800.0,                               // valorAluguel
            false,                               // temBanheiroPrivativo
            true                                 // temArCondicionado
        );
        
        // 4. REPÚBLICA - herda de Casa, compartilhada
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
        
        // 5. PENSIONATO - herda de Casa, com rigor maior
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
        
        // 6. EDÍCULA - com entrada independente
        Edicula edicula = new Edicula(
            "Rua do Comércio, 654 - Centro", // endereco
            1,                               // numeroBanheiros
            45.0,                            // areaConstruida
            2,                               // numeroMaximoInquilinos
            1500.0,                          // valorAluguel
            true,                            // temCozinha
            true                             // temEntradaIndependente
        );
        
        // 7. PRÉDIO DE KITNETS - com estrutura
        PredioKit predioKit = new PredioKit(
            "Av. das Indústrias, 987 - Industrial", // endereco
            1,                                      // numeroBanheiros
            40.0,                                   // areaConstruida
            1,                                      // numeroMaximoInquilinos
            1100.0,                                 // valorAluguel
            3,                                      // numeroAndares
            12,                                     // numeroKitnets
            true,                                   // temElevador
            true                                    // temPortaria
        );
        
        // Adicionar proprietários
        casa.adicionarProprietario(proprietario);
        kitnet.adicionarProprietario(proprietario);
        quarto.adicionarProprietario(proprietario);
        republica.adicionarProprietario(proprietario);
        pensionato.adicionarProprietario(proprietario);
        edicula.adicionarProprietario(proprietario);
        predioKit.adicionarProprietario(proprietario);
        
        // Adicionar responsável
        casa.setResponsavel(responsavel);
        kitnet.setResponsavel(responsavel);
        republica.setResponsavel(responsavel);
        pensionato.setResponsavel(responsavel);
        
        // Adicionar ao repositório
        repositorio.adicionarLocal(casa);
        repositorio.adicionarLocal(kitnet);
        repositorio.adicionarLocal(quarto);
        repositorio.adicionarLocal(republica);
        repositorio.adicionarLocal(pensionato);
        repositorio.adicionarLocal(edicula);
        repositorio.adicionarLocal(predioKit);
        
        // Demonstrar funcionalidades
        System.out.println("=== SISTEMA SIMPLIFICADO DE LOCAIS ===");
        System.out.println("Total de locais: " + repositorio.getQuantidadeLocais());
        
        // Listar todos os locais
        System.out.println("\n--- Todos os locais ---");
        List<Local> todosLocais = repositorio.listarTodos();
        for (Local local : todosLocais) {
            System.out.println(local);
        }
        
        // Funcionalidades específicas da Casa
        System.out.println("\n--- Casa ---");
        System.out.println("Quartos: " + casa.getNumeroQuartos());
        System.out.println("Vagas de garagem: " + casa.getVagasGaragem());
        System.out.println("Valor por quarto: R$" + String.format("%.2f", casa.getValorPorQuarto()));
        
        // Funcionalidades específicas da Kitnet
        System.out.println("\n--- Kitnet ---");
        System.out.println("Tem cozinha: " + kitnet.isTemCozinha());
        System.out.println("Tem sacada: " + kitnet.isTemSacada());
        System.out.println("Valor por m²: R$" + String.format("%.2f", kitnet.getValorPorMetroQuadrado()));
        
        // Funcionalidades específicas do Quarto
        System.out.println("\n--- Quarto ---");
        System.out.println("Tem banheiro privativo: " + quarto.isTemBanheiroPrivativo());
        System.out.println("Tem ar condicionado: " + quarto.isTemArCondicionado());
        
        // Funcionalidades específicas da República (herda de Casa)
        System.out.println("\n--- República ---");
        System.out.println("Quartos: " + republica.getNumeroQuartos());
        System.out.println("Vagas de garagem: " + republica.getVagasGaragem());
        System.out.println("Tem cozinha compartilhada: " + republica.isTemCozinhaCompartilhada());
        System.out.println("Tem internet: " + republica.isTemInternet());
        System.out.println("Valor por quarto: R$" + String.format("%.2f", republica.getValorPorQuarto()));
        
        // Funcionalidades específicas do Pensionato (herda de Casa)
        System.out.println("\n--- Pensionato ---");
        System.out.println("Quartos: " + pensionato.getNumeroQuartos());
        System.out.println("Vagas de garagem: " + pensionato.getVagasGaragem());
        System.out.println("Tem refeições inclusas: " + pensionato.isTemRefeicoesInclusas());
        System.out.println("Tem limpeza inclusa: " + pensionato.isTemLimpezaInclusa());
        System.out.println("Tem horário de fechamento: " + pensionato.isTemHorarioFechamento());
        System.out.println("Tem regras rigorosas: " + pensionato.isTemRegrasRigorosas());
        System.out.println("Valor por quarto: R$" + String.format("%.2f", pensionato.getValorPorQuarto()));
        System.out.println("Valor por quarto com serviços: R$" + String.format("%.2f", pensionato.getValorPorQuartoComServicos()));
        
        // Funcionalidades específicas da Edícula
        System.out.println("\n--- Edícula ---");
        System.out.println("Tem cozinha: " + edicula.isTemCozinha());
        System.out.println("Tem entrada independente: " + edicula.isTemEntradaIndependente());
        
        // Funcionalidades específicas do Prédio de Kitnets
        System.out.println("\n--- Prédio de Kitnets ---");
        System.out.println("Andares: " + predioKit.getNumeroAndares());
        System.out.println("Kitnets: " + predioKit.getNumeroKitnets());
        System.out.println("Tem elevador: " + predioKit.isTemElevador());
        System.out.println("Tem portaria: " + predioKit.isTemPortaria());
        
        // Estatísticas
        System.out.println("\n--- Estatísticas por tipo ---");
        var estatisticas = repositorio.getEstatisticasPorTipo();
        for (var entry : estatisticas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " locais");
        }
        
        // Demonstrar herança - República e Pensionato são tipos de Casa
        System.out.println("\n--- Demonstração de Herança ---");
        System.out.println("República é uma Casa? " + (republica instanceof Casa));
        System.out.println("Pensionato é uma Casa? " + (pensionato instanceof Casa));
        System.out.println("República é um Local? " + (republica instanceof Local));
        System.out.println("Pensionato é um Local? " + (pensionato instanceof Local));
    }
} 