package sistema;

import locais.*;
import perfil.Perfil;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ExemploSistemaCompleto {
    public static void main(String[] args) {
        // Obter instância do repositório
        RepositorioLocais repositorio = RepositorioLocais.getInstancia();
        
        // Criar perfis de usuários
        Perfil proprietario1 = new Perfil("João Silva", "joao@email.com", "(11) 99999-9999");
        Perfil proprietario2 = new Perfil("Maria Santos", "maria@email.com", "(11) 88888-8888");
        Perfil proprietario3 = new Perfil("Carlos Oliveira", "carlos@email.com", "(11) 77777-7777");
        Perfil responsavel1 = new Perfil("Ana Costa", "ana@email.com", "(11) 66666-6666");
        Perfil morador1 = new Perfil("Pedro Lima", "pedro@email.com", "(11) 55555-5555");
        
        // Criar formas de caução
        List<Local.FormaCaucao> formasCaucao1 = new ArrayList<>();
        formasCaucao1.add(Local.FormaCaucao.FIADOR);
        formasCaucao1.add(Local.FormaCaucao.DEPOSITO);
        
        List<Local.FormaCaucao> formasCaucao2 = new ArrayList<>();
        formasCaucao2.add(Local.FormaCaucao.SEGURO);
        
        List<Local.FormaCaucao> formasCaucao3 = new ArrayList<>();
        formasCaucao3.add(Local.FormaCaucao.SEM_CAUCAO);
        
        // 1. CASA - Individual
        Casa casa = new Casa(
            "Rua das Flores, 123 - Centro",  // endereco
            2,                              // numeroBanheiros
            120.0,                          // areaConstruida
            3,                              // numeroMaximoInquilinos
            2500.0,                         // valorAluguel
            3,                              // numeroQuartos
            1                               // vagasGaragem
        );
        
        // 2. CASA - Compartilhada
        Casa casaCompartilhada = new Casa(
            "Rua dos Estudantes, 456 - Campus", // endereco
            3,                                   // numeroBanheiros
            150.0,                               // areaConstruida
            4000.0,                              // valorAluguel
            4,                                   // numeroInquilinos
            Local.TipoCompartilhamento.MISTO,    // tipoCompartilhamento
            "Casa para estudantes de graduação. Ambiente tranquilo, próximo à universidade.", // descricao
            4,                                   // numeroQuartos
            2                                    // vagasGaragem
        );
        
        // 3. KITNET - Individual
        Kitnet kitnet = new Kitnet(
            "Av. Principal, 456 - Jardim",  // endereco
            35.0,                           // areaConstruida
            1200.0,                         // valorAluguel
            true,                           // temCozinha
            false                           // temSacada
        );
        
        // 4. KITNET - Compartilhada
        Kitnet kitnetCompartilhada = new Kitnet(
            "Av. das Indústrias, 789 - Industrial", // endereco
            40.0,                                   // areaConstruida
            1500.0,                                 // valorAluguel
            2,                                      // numeroInquilinos
            Local.TipoCompartilhamento.FEMININO,    // tipoCompartilhamento
            "Kitnet para mulheres, ambiente seguro e tranquilo" // descricao
        );
        
        // 5. QUARTO - Individual
        Quarto quarto = new Quarto(
            "Rua da Universidade, 789 - Campus", // endereco
            15.0,                                // areaConstruida
            800.0,                               // valorAluguel
            false,                               // temBanheiroPrivativo
            true                                 // temArCondicionado
        );
        
        // 6. QUARTO - Compartilhado
        Quarto quartoCompartilhado = new Quarto(
            "Rua dos Estudantes, 321 - Vila Nova", // endereco
            18.0,                                  // areaConstruida
            1000.0,                                // valorAluguel
            2,                                     // numeroInquilinos
            Local.TipoCompartilhamento.MASCULINO,  // tipoCompartilhamento
            "Quarto para homens, próximo ao campus" // descricao
        );
        
        // 7. REPÚBLICA - herda de Casa
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
        
        // 8. PENSIONATO - herda de Casa
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
        
        // 9. EDÍCULA - Individual
        Edicula edicula = new Edicula(
            "Rua do Comércio, 654 - Centro", // endereco
            1,                               // numeroBanheiros
            45.0,                            // areaConstruida
            2,                               // numeroMaximoInquilinos
            1500.0,                          // valorAluguel
            true,                            // temCozinha
            true                             // temEntradaIndependente
        );
        
        // 10. PRÉDIO DE KITNETS - com estrutura
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
        
        // Adicionar proprietários e responsáveis
        casa.adicionarProprietario(proprietario1);
        casa.setResponsavel(responsavel1);
        
        casaCompartilhada.adicionarProprietario(proprietario2);
        casaCompartilhada.adicionarMorador(morador1);
        
        kitnet.adicionarProprietario(proprietario2);
        kitnet.setResponsavel(responsavel1);
        
        kitnetCompartilhada.adicionarProprietario(proprietario3);
        
        quarto.adicionarProprietario(proprietario1);
        
        quartoCompartilhado.adicionarProprietario(proprietario2);
        
        republica.adicionarProprietario(proprietario1);
        republica.setResponsavel(responsavel1);
        
        pensionato.adicionarProprietario(proprietario2);
        pensionato.setResponsavel(responsavel1);
        
        edicula.adicionarProprietario(proprietario3);
        
        predioKit.adicionarProprietario(proprietario1);
        
        // Adicionar locais ao repositório
        repositorio.adicionarLocal(casa);
        repositorio.adicionarLocal(casaCompartilhada);
        repositorio.adicionarLocal(kitnet);
        repositorio.adicionarLocal(kitnetCompartilhada);
        repositorio.adicionarLocal(quarto);
        repositorio.adicionarLocal(quartoCompartilhado);
        repositorio.adicionarLocal(republica);
        repositorio.adicionarLocal(pensionato);
        repositorio.adicionarLocal(edicula);
        repositorio.adicionarLocal(predioKit);
        
        // Demonstrar funcionalidades do sistema
        System.out.println("=== SISTEMA COMPLETO DE LOCAÇÃO ===");
        System.out.println("Total de locais: " + repositorio.getQuantidadeLocais());
        
        // Listar todos os locais
        System.out.println("\n--- Todos os locais cadastrados ---");
        List<Local> todosLocais = repositorio.listarTodos();
        for (Local local : todosLocais) {
            System.out.println(local);
        }
        
        // Demonstrar herança - República e Pensionato são Casas
        System.out.println("\n--- Demonstração de Herança ---");
        System.out.println("República é uma Casa? " + (republica instanceof Casa));
        System.out.println("Pensionato é uma Casa? " + (pensionato instanceof Casa));
        System.out.println("República é um Local? " + (republica instanceof Local));
        System.out.println("Pensionato é um Local? " + (pensionato instanceof Local));
        
        // Demonstrar funcionalidades específicas
        System.out.println("\n--- Funcionalidades Específicas ---");
        
        // Casa
        System.out.println("Casa: " + casa.getEndereco());
        System.out.println("  Quartos: " + casa.getNumeroQuartos());
        System.out.println("  Vagas de garagem: " + casa.getVagasGaragem());
        System.out.println("  Valor por quarto: R$" + String.format("%.2f", casa.getValorPorQuarto()));
        
        // República
        System.out.println("\nRepública: " + republica.getEndereco());
        System.out.println("  Quartos: " + republica.getNumeroQuartos());
        System.out.println("  Vagas de garagem: " + republica.getVagasGaragem());
        System.out.println("  Tem cozinha compartilhada: " + republica.isTemCozinhaCompartilhada());
        System.out.println("  Tem internet: " + republica.isTemInternet());
        System.out.println("  Valor por quarto: R$" + String.format("%.2f", republica.getValorPorQuarto()));
        
        // Pensionato
        System.out.println("\nPensionato: " + pensionato.getEndereco());
        System.out.println("  Quartos: " + pensionato.getNumeroQuartos());
        System.out.println("  Vagas de garagem: " + pensionato.getVagasGaragem());
        System.out.println("  Tem refeições inclusas: " + pensionato.isTemRefeicoesInclusas());
        System.out.println("  Tem limpeza inclusa: " + pensionato.isTemLimpezaInclusa());
        System.out.println("  Tem horário de fechamento: " + pensionato.isTemHorarioFechamento());
        System.out.println("  Tem regras rigorosas: " + pensionato.isTemRegrasRigorosas());
        System.out.println("  Valor por quarto: R$" + String.format("%.2f", pensionato.getValorPorQuarto()));
        System.out.println("  Valor por quarto com serviços: R$" + String.format("%.2f", pensionato.getValorPorQuartoComServicos()));
        
        // Kitnet
        System.out.println("\nKitnet: " + kitnet.getEndereco());
        System.out.println("  Tem cozinha: " + kitnet.isTemCozinha());
        System.out.println("  Tem sacada: " + kitnet.isTemSacada());
        System.out.println("  Valor por m²: R$" + String.format("%.2f", kitnet.getValorPorMetroQuadrado()));
        
        // Quarto
        System.out.println("\nQuarto: " + quarto.getEndereco());
        System.out.println("  Tem banheiro privativo: " + quarto.isTemBanheiroPrivativo());
        System.out.println("  Tem ar condicionado: " + quarto.isTemArCondicionado());
        System.out.println("  Valor por m²: R$" + String.format("%.2f", quarto.getValorPorMetroQuadrado()));
        
        // Edícula
        System.out.println("\nEdícula: " + edicula.getEndereco());
        System.out.println("  Tem cozinha: " + edicula.isTemCozinha());
        System.out.println("  Tem entrada independente: " + edicula.isTemEntradaIndependente());
        System.out.println("  Valor por m²: R$" + String.format("%.2f", edicula.getValorPorMetroQuadrado()));
        
        // Prédio de Kitnets
        System.out.println("\nPrédio de Kitnets: " + predioKit.getEndereco());
        System.out.println("  Andares: " + predioKit.getNumeroAndares());
        System.out.println("  Kitnets: " + predioKit.getNumeroKitnets());
        System.out.println("  Tem elevador: " + predioKit.isTemElevador());
        System.out.println("  Tem portaria: " + predioKit.isTemPortaria());
        System.out.println("  Valor por kitnet: R$" + String.format("%.2f", predioKit.getValorPorKitnet()));
        
        // Buscar locais por características
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
        
        // Buscar locais compartilhados
        System.out.println("\n--- Locais compartilhados ---");
        for (Local local : todosLocais) {
            if (local.isCompartilhado()) {
                System.out.println("  " + local.getTipoLocal() + ": " + local.getEndereco());
                System.out.println("    Tipo: " + local.getTipoCompartilhamento());
                System.out.println("    Inquilinos: " + local.getNumeroInquilinos());
                System.out.println("    Valor por pessoa: R$" + String.format("%.2f", local.getValorPorPessoa()));
            }
        }
        
        // Buscar locais por proprietário
        System.out.println("\n--- Imóveis do João Silva ---");
        for (Local local : todosLocais) {
            if (local.getProprietarios().contains(proprietario1)) {
                System.out.println("  " + local.getTipoLocal() + ": " + local.getEndereco());
            }
        }
        
        // Estatísticas
        System.out.println("\n--- Estatísticas por tipo ---");
        Map<String, Long> estatisticas = repositorio.getEstatisticasPorTipo();
        for (Map.Entry<String, Long> entry : estatisticas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " locais");
        }
        
        // Valor total dos aluguéis
        double valorTotal = todosLocais.stream().mapToDouble(Local::getValorAluguel).sum();
        System.out.println("\nValor total dos aluguéis: R$" + String.format("%.2f", valorTotal));
        
        // Valor médio por tipo
        System.out.println("\n--- Valor médio por tipo ---");
        for (String tipo : estatisticas.keySet()) {
            double valorTotalTipo = 0;
            int count = 0;
            for (Local local : todosLocais) {
                if (local.getTipoLocal().equals(tipo)) {
                    valorTotalTipo += local.getValorAluguel();
                    count++;
                }
            }
            if (count > 0) {
                System.out.println(tipo + ": R$" + String.format("%.2f", valorTotalTipo / count));
            }
        }
    }
} 