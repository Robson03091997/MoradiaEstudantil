package locais;

import perfil.Perfil;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ExemploClassesAtualizadas {
    public static void main(String[] args) {
        // Obter instância do repositório
        RepositorioLocais repositorio = RepositorioLocais.getInstancia();
        
        // Criar alguns perfis de exemplo
        Perfil proprietario1 = new Perfil("João Silva", "joao@email.com", "(11) 99999-9999");
        Perfil proprietario2 = new Perfil("Maria Santos", "maria@email.com", "(11) 88888-8888");
        Perfil responsavel1 = new Perfil("Carlos Oliveira", "carlos@email.com", "(11) 77777-7777");
        Perfil morador1 = new Perfil("Pedro Costa", "pedro@email.com", "(11) 55555-5555");
        
        // Criar formas de caução
        List<Local.FormaCaucao> formasCaucao1 = new ArrayList<>();
        formasCaucao1.add(Local.FormaCaucao.FIADOR);
        formasCaucao1.add(Local.FormaCaucao.DEPOSITO);
        
        List<Local.FormaCaucao> formasCaucao2 = new ArrayList<>();
        formasCaucao2.add(Local.FormaCaucao.SEM_CAUCAO);
        
        // 1. CRIAR UMA CASA (com quartos e garagem)
        Casa casa = new Casa(
            "Rua das Flores, 123 - Centro",  // endereco
            2,                              // numeroBanheiros
            120.0,                          // areaConstruida
            3,                              // numeroMaximoInquilinos
            2500.0,                         // valorAluguel
            3,                              // numeroQuartos
            1                               // vagasGaragem
        );
        
        // 2. CRIAR UMA KITNET (sem quartos, com cozinha)
        Kitnet kitnet = new Kitnet(
            "Av. Principal, 456 - Jardim",  // endereco
            35.0,                           // areaConstruida
            1200.0,                         // valorAluguel
            true,                           // temArmario
            true,                           // temCozinha
            false                           // temSacada
        );
        
        // 3. CRIAR UM QUARTO (sem banheiro próprio)
        Quarto quarto = new Quarto(
            "Rua da Universidade, 789 - Campus", // endereco
            15.0,                                // areaConstruida
            800.0,                               // valorAluguel
            false,                               // temArmario
            false,                               // temBanheiroPrivativo
            true,                                // temVentilador
            false                                // temArCondicionado
        );
        
        // 4. CRIAR UMA REPÚBLICA (compartilhada, com banheiros)
        Republica republica = new Republica(
            "Rua dos Estudantes, 321 - Vila Nova", // endereco
            2,                                     // numeroBanheiros
            100.0,                                 // areaConstruida
            5,                                     // numeroMaximoInquilinos
            2500.0,                                // valorAluguel
            5,                                     // numeroQuartos
            true,                                  // temCozinhaCompartilhada
            true,                                  // temSalaComum
            false,                                 // temLavanderia
            true,                                  // temInternet
            false                                  // temLimpezaInclusa
        );
        
        // 5. CRIAR UMA EDÍCULA (com banheiro e cozinha)
        Edicula edicula = new Edicula(
            "Rua do Comércio, 654 - Centro", // endereco
            1,                               // numeroBanheiros
            45.0,                            // areaConstruida
            2,                               // numeroMaximoInquilinos
            1500.0,                          // valorAluguel
            true,                            // temCozinha
            false,                           // temQuintal
            true,                            // temEntradaIndependente
            false                            // temGaragem
        );
        
        // 6. CRIAR UM PRÉDIO DE KITNETS (com banheiros e estrutura)
        PredioKit predioKit = new PredioKit(
            "Av. das Indústrias, 987 - Industrial", // endereco
            1,                                      // numeroBanheiros
            40.0,                                   // areaConstruida
            1,                                      // numeroMaximoInquilinos
            1100.0,                                 // valorAluguel
            3,                                      // numeroAndares
            12,                                     // numeroKitnets
            true,                                   // temElevador
            true,                                   // temPortaria
            false,                                  // temAreaLazer
            true                                    // temVagasGaragem
        );
        
        // Adicionar proprietários e responsáveis
        casa.adicionarProprietario(proprietario1);
        casa.setResponsavel(responsavel1);
        
        kitnet.adicionarProprietario(proprietario2);
        kitnet.setResponsavel(responsavel1);
        
        quarto.adicionarProprietario(proprietario1);
        
        republica.adicionarProprietario(proprietario1);
        republica.adicionarMorador(morador1);
        
        edicula.adicionarProprietario(proprietario2);
        
        predioKit.adicionarProprietario(proprietario1);
        
        // Adicionar locais ao repositório
        repositorio.adicionarLocal(casa);
        repositorio.adicionarLocal(kitnet);
        repositorio.adicionarLocal(quarto);
        repositorio.adicionarLocal(republica);
        repositorio.adicionarLocal(edicula);
        repositorio.adicionarLocal(predioKit);
        
        // Demonstrar funcionalidades específicas de cada tipo
        System.out.println("=== SISTEMA DE LOCAIS COM ATRIBUTOS ESPECÍFICOS ===");
        System.out.println("Total de locais: " + repositorio.getQuantidadeLocais());
        
        // Listar todos os locais com seus atributos específicos
        System.out.println("\n--- Todos os locais com atributos específicos ---");
        List<Local> todosLocais = repositorio.listarTodos();
        for (Local local : todosLocais) {
            System.out.println(local);
        }
        
        // Demonstrar funcionalidades específicas da Casa
        System.out.println("\n--- Funcionalidades específicas da Casa ---");
        if (casa instanceof Casa) {
            Casa casaCast = (Casa) casa;
            System.out.println("Casa: " + casaCast.getEndereco());
            System.out.println("  Quartos: " + casaCast.getNumeroQuartos());
            System.out.println("  Vagas de garagem: " + casaCast.getVagasGaragem());
            System.out.println("  Valor por quarto: R$" + String.format("%.2f", casaCast.getValorPorQuarto()));
        }
        
        // Demonstrar funcionalidades específicas da Kitnet
        System.out.println("\n--- Funcionalidades específicas da Kitnet ---");
        if (kitnet instanceof Kitnet) {
            Kitnet kitnetCast = (Kitnet) kitnet;
            System.out.println("Kitnet: " + kitnetCast.getEndereco());
            System.out.println("  Tem cozinha: " + kitnetCast.isTemCozinha());
            System.out.println("  Tem armário: " + kitnetCast.isTemArmario());
            System.out.println("  Tem sacada: " + kitnetCast.isTemSacada());
            System.out.println("  Valor por m²: R$" + String.format("%.2f", kitnetCast.getValorPorMetroQuadrado()));
        }
        
        // Demonstrar funcionalidades específicas do Quarto
        System.out.println("\n--- Funcionalidades específicas do Quarto ---");
        if (quarto instanceof Quarto) {
            Quarto quartoCast = (Quarto) quarto;
            System.out.println("Quarto: " + quartoCast.getEndereco());
            System.out.println("  Tem banheiro privativo: " + quartoCast.isTemBanheiroPrivativo());
            System.out.println("  Tem armário: " + quartoCast.isTemArmario());
            System.out.println("  Tem ventilador: " + quartoCast.isTemVentilador());
            System.out.println("  Tem ar condicionado: " + quartoCast.isTemArCondicionado());
        }
        
        // Demonstrar funcionalidades específicas da República
        System.out.println("\n--- Funcionalidades específicas da República ---");
        if (republica instanceof Republica) {
            Republica republicaCast = (Republica) republica;
            System.out.println("República: " + republicaCast.getEndereco());
            System.out.println("  Quartos: " + republicaCast.getNumeroQuartos());
            System.out.println("  Tem cozinha compartilhada: " + republicaCast.isTemCozinhaCompartilhada());
            System.out.println("  Tem sala comum: " + republicaCast.isTemSalaComum());
            System.out.println("  Tem internet: " + republicaCast.isTemInternet());
            System.out.println("  Valor por quarto: R$" + String.format("%.2f", republicaCast.getValorPorQuarto()));
        }
        
        // Demonstrar funcionalidades específicas da Edícula
        System.out.println("\n--- Funcionalidades específicas da Edícula ---");
        if (edicula instanceof Edicula) {
            Edicula ediculaCast = (Edicula) edicula;
            System.out.println("Edícula: " + ediculaCast.getEndereco());
            System.out.println("  Tem cozinha: " + ediculaCast.isTemCozinha());
            System.out.println("  Tem quintal: " + ediculaCast.isTemQuintal());
            System.out.println("  Tem entrada independente: " + ediculaCast.isTemEntradaIndependente());
            System.out.println("  Tem garagem: " + ediculaCast.isTemGaragem());
        }
        
        // Demonstrar funcionalidades específicas do Prédio de Kitnets
        System.out.println("\n--- Funcionalidades específicas do Prédio de Kitnets ---");
        if (predioKit instanceof PredioKit) {
            PredioKit predioCast = (PredioKit) predioKit;
            System.out.println("Prédio de Kitnets: " + predioCast.getEndereco());
            System.out.println("  Andares: " + predioCast.getNumeroAndares());
            System.out.println("  Kitnets: " + predioCast.getNumeroKitnets());
            System.out.println("  Tem elevador: " + predioCast.isTemElevador());
            System.out.println("  Tem portaria: " + predioCast.isTemPortaria());
            System.out.println("  Tem vagas de garagem: " + predioCast.isTemVagasGaragem());
            System.out.println("  Valor por kitnet: R$" + String.format("%.2f", predioCast.getValorPorKitnet()));
        }
        
        // Buscar locais por características específicas
        System.out.println("\n--- Locais com cozinha ---");
        for (Local local : todosLocais) {
            if (local instanceof Kitnet && ((Kitnet) local).isTemCozinha()) {
                System.out.println("  Kitnet: " + local.getEndereco());
            } else if (local instanceof Edicula && ((Edicula) local).isTemCozinha()) {
                System.out.println("  Edícula: " + local.getEndereco());
            } else if (local instanceof Republica && ((Republica) local).isTemCozinhaCompartilhada()) {
                System.out.println("  República: " + local.getEndereco());
            }
        }
        
        // Buscar locais com ar condicionado
        System.out.println("\n--- Locais com ar condicionado ---");
        for (Local local : todosLocais) {
            if (local instanceof Quarto && ((Quarto) local).isTemArCondicionado()) {
                System.out.println("  Quarto: " + local.getEndereco());
            }
        }
        
        // Estatísticas por tipo
        System.out.println("\n--- Estatísticas por tipo ---");
        Map<String, Long> estatisticas = repositorio.getEstatisticasPorTipo();
        for (Map.Entry<String, Long> entry : estatisticas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " locais");
        }
    }
} 