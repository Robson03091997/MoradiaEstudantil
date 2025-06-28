package locais;

import perfil.Perfil;
import java.util.List;
import java.util.Map;

public class ExemploUsoAtualizado {
    public static void main(String[] args) {
        // Obter instância do repositório
        RepositorioLocais repositorio = RepositorioLocais.getInstancia();
        
        // Criar alguns perfis de exemplo
        Perfil proprietario1 = new Perfil("João Silva", "joao@email.com", "(11) 99999-9999");
        Perfil proprietario2 = new Perfil("Maria Santos", "maria@email.com", "(11) 88888-8888");
        Perfil morador1 = new Perfil("Pedro Costa", "pedro@email.com", "(11) 77777-7777");
        
        // Criar uma casa com todos os atributos
        Casa casaLuxo = new Casa(
            "Rua das Flores, 123 - Centro",  // endereco
            3,                              // numeroBanheiros
            200.0,                          // areaConstruida
            4,                              // numeroMaximoInquilinos
            5000.0,                         // valorAluguel
            4,                              // numeroQuartos
            2,                              // vagasGaragem
            true,                           // temQuintal
            true,                           // temAreaServico
            true,                           // temSalaJantar
            2,                              // numeroAndares
            true,                           // temChurrasqueira
            true                            // temPiscina
        );
        
        // Criar uma casa mais simples
        Casa casaSimples = new Casa(
            "Av. Principal, 456 - Jardim",  // endereco
            2,                              // numeroBanheiros
            120.0,                          // areaConstruida
            3,                              // numeroMaximoInquilinos
            2500.0,                         // valorAluguel
            3,                              // numeroQuartos
            1                               // vagasGaragem
        );
        
        // Criar outros tipos de locais
        Kitnet kitnet1 = new Kitnet("Rua da Universidade, 789 - Vila Nova", 1, 35.0, 1, 1200.0);
        Quarto quarto1 = new Quarto("Rua dos Estudantes, 321 - Campus", 1, 15.0, 1, 800.0);
        Republica republica1 = new Republica("Rua dos Estudantes, 654 - Campus", 2, 80.0, 6, 3000.0);
        Edicula edicula1 = new Edicula("Rua do Comércio, 987 - Centro", 1, 45.0, 2, 1500.0);
        PredioKit predio1 = new PredioKit("Av. das Indústrias, 555 - Industrial", 1, 40.0, 1, 1100.0);
        
        // Adicionar proprietários e moradores
        casaLuxo.adicionarProprietario(proprietario1);
        casaLuxo.adicionarMorador(morador1);
        
        casaSimples.adicionarProprietario(proprietario2);
        
        // Adicionar locais ao repositório
        repositorio.adicionarLocal(casaLuxo);
        repositorio.adicionarLocal(casaSimples);
        repositorio.adicionarLocal(kitnet1);
        repositorio.adicionarLocal(quarto1);
        repositorio.adicionarLocal(republica1);
        repositorio.adicionarLocal(edicula1);
        repositorio.adicionarLocal(predio1);
        
        // Demonstrar funcionalidades do repositório
        System.out.println("=== REPOSITÓRIO DE LOCAIS ATUALIZADO ===");
        System.out.println("Total de locais: " + repositorio.getQuantidadeLocais());
        
        // Listar todos os locais
        System.out.println("\n--- Todos os locais ---");
        List<Local> todosLocais = repositorio.listarTodos();
        for (Local local : todosLocais) {
            System.out.println(local);
        }
        
        // Buscar casas de luxo
        System.out.println("\n--- Casas de luxo ---");
        List<Local> casas = repositorio.buscarPorTipo("Casa");
        for (Local local : casas) {
            if (local instanceof Casa) {
                Casa casa = (Casa) local;
                if (casa.isCasaLuxo()) {
                    System.out.println("Casa de luxo: " + casa);
                    System.out.println("  Valor por quarto: R$" + String.format("%.2f", casa.getValorPorQuarto()));
                }
            }
        }
        
        // Locais disponíveis
        System.out.println("\n--- Locais disponíveis ---");
        List<Local> disponiveis = repositorio.getLocaisDisponiveis();
        System.out.println("Locais disponíveis: " + disponiveis.size());
        for (Local local : disponiveis) {
            System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco() + 
                             " (R$" + String.format("%.2f", local.getValorAluguel()) + ")");
        }
        
        // Estatísticas
        System.out.println("\n--- Estatísticas por tipo ---");
        Map<String, Long> estatisticas = repositorio.getEstatisticasPorTipo();
        for (Map.Entry<String, Long> entry : estatisticas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " locais");
        }
        
        // Buscar por faixa de preço
        System.out.println("\n--- Locais com aluguel até R$ 2000 ---");
        for (Local local : todosLocais) {
            if (local.getValorAluguel() <= 2000.0) {
                System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco() + 
                                 " (R$" + String.format("%.2f", local.getValorAluguel()) + ")");
            }
        }
    }
} 