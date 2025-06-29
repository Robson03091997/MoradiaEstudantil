package locais;

import perfil.Perfil;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ExemploCaucaoResponsavel {
    public static void main(String[] args) {
        // Obter instância do repositório
        RepositorioLocais repositorio = RepositorioLocais.getInstancia();
        
        // Criar alguns perfis de exemplo
        Perfil proprietario1 = new Perfil("João", "Silva", "joao@email.com", "(11) 99999-9999");
        Perfil proprietario2 = new Perfil("Maria Santos", "maria@email.com", "(11) 88888-8888");
        Perfil responsavel1 = new Perfil("Carlos Oliveira", "carlos@email.com", "(11) 77777-7777");
        Perfil responsavel2 = new Perfil("Ana Costa", "ana@email.com", "(11) 66666-6666");
        Perfil morador1 = new Perfil("Pedro Costa", "pedro@email.com", "(11) 55555-5555");
        
        // Criar formas de caução
        List<Local.FormaCaucao> formasCaucao1 = new ArrayList<>();
        formasCaucao1.add(Local.FormaCaucao.FIADOR);
        formasCaucao1.add(Local.FormaCaucao.DEPOSITO);
        
        List<Local.FormaCaucao> formasCaucao2 = new ArrayList<>();
        formasCaucao2.add(Local.FormaCaucao.SEGURO);
        formasCaucao2.add(Local.FormaCaucao.CALCAO);
        
        List<Local.FormaCaucao> formasCaucao3 = new ArrayList<>();
        formasCaucao3.add(Local.FormaCaucao.SEM_CAUCAO);
        
        // Criar uma casa com fiador e depósito
        Casa casaComFiador = new Casa(
            "Rua das Flores, 123 - Centro",  // endereco
            2,                              // numeroBanheiros
            120.0,                          // areaConstruida
            3,                              // numeroMaximoInquilinos
            2500.0,                         // valorAluguel
            formasCaucao1,                  // formasCaucao
            false,                          // aceitaSeguro
            0.0,                            // valorSeguro
            responsavel1,                   // responsavel
            3,                              // numeroQuartos
            1                               // vagasGaragem
        );
        
        // Criar uma casa com seguro
        Casa casaComSeguro = new Casa(
            "Av. Principal, 456 - Jardim",  // endereco
            3,                              // numeroBanheiros
            150.0,                          // areaConstruida
            4,                              // numeroMaximoInquilinos
            3500.0,                         // valorAluguel
            formasCaucao2,                  // formasCaucao
            true,                           // aceitaSeguro
            500.0,                          // valorSeguro
            responsavel2,                   // responsavel
            4,                              // numeroQuartos
            2                               // vagasGaragem
        );
        
        // Criar uma casa sem caução (para estudantes)
        Casa casaSemCaucao = new Casa(
            "Rua dos Estudantes, 789 - Campus", // endereco
            2,                                   // numeroBanheiros
            100.0,                               // areaConstruida
            3,                                   // numeroMaximoInquilinos
            1800.0,                              // valorAluguel
            formasCaucao3,                       // formasCaucao
            false,                               // aceitaSeguro
            0.0,                                 // valorSeguro
            responsavel1,                        // responsavel
            3,                                   // numeroQuartos
            1                                    // vagasGaragem
        );
        
        // Criar uma república com caução
        Republica republicaComCaucao = new Republica(
            "Rua das Meninas, 321 - Vila Nova",  // endereco
            2,                                   // numeroBanheiros
            120.0,                               // areaConstruida
            6,                                   // numeroMaximoInquilinos
            3000.0,                              // valorAluguel
            true,                                // compartilhado
            6,                                   // numeroInquilinos
            Local.TipoCompartilhamento.FEMININO, // tipoCompartilhamento
            "República feminina para estudantes. Ambiente organizado e limpo.", // descricao
            formasCaucao1,                       // formasCaucao
            true,                                // aceitaSeguro
            300.0,                               // valorSeguro
            responsavel2                         // responsavel
        );
        
        // Adicionar proprietários e moradores
        casaComFiador.adicionarProprietario(proprietario1);
        casaComFiador.adicionarMorador(morador1);
        
        casaComSeguro.adicionarProprietario(proprietario2);
        
        casaSemCaucao.adicionarProprietario(proprietario1);
        
        republicaComCaucao.adicionarProprietario(proprietario1);
        
        // Adicionar locais ao repositório
        repositorio.adicionarLocal(casaComFiador);
        repositorio.adicionarLocal(casaComSeguro);
        repositorio.adicionarLocal(casaSemCaucao);
        repositorio.adicionarLocal(republicaComCaucao);
        
        // Demonstrar funcionalidades do repositório
        System.out.println("=== SISTEMA DE LOCAIS COM CAUÇÃO E RESPONSÁVEL ===");
        System.out.println("Total de locais: " + repositorio.getQuantidadeLocais());
        
        // Listar todos os locais
        System.out.println("\n--- Todos os locais ---");
        List<Local> todosLocais = repositorio.listarTodos();
        for (Local local : todosLocais) {
            System.out.println(local);
        }
        
        // Buscar locais por forma de caução
        System.out.println("\n--- Locais que aceitam fiador ---");
        for (Local local : todosLocais) {
            if (local.aceitaFormaCaucao(Local.FormaCaucao.FIADOR)) {
                System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco());
                System.out.println("    Responsável: " + local.getResponsavel().getNome());
                System.out.println("    Valor total inicial: R$" + String.format("%.2f", local.getValorTotalInicial()));
            }
        }
        
        // Buscar locais com seguro
        System.out.println("\n--- Locais com seguro ---");
        for (Local local : todosLocais) {
            if (local.isAceitaSeguro()) {
                System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco());
                System.out.println("    Valor do seguro: R$" + String.format("%.2f", local.getValorSeguro()));
                System.out.println("    Valor total inicial: R$" + String.format("%.2f", local.getValorTotalInicial()));
            }
        }
        
        // Buscar locais sem caução (para estudantes)
        System.out.println("\n--- Locais sem caução (para estudantes) ---");
        for (Local local : todosLocais) {
            if (local.aceitaFormaCaucao(Local.FormaCaucao.SEM_CAUCAO)) {
                System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco());
                System.out.println("    Valor do aluguel: R$" + String.format("%.2f", local.getValorAluguel()));
                if (local.isCompartilhado()) {
                    System.out.println("    Valor por pessoa: R$" + String.format("%.2f", local.getValorPorPessoa()));
                }
            }
        }
        
        // Buscar por responsável
        System.out.println("\n--- Locais por responsável ---");
        for (Local local : todosLocais) {
            if (local.getResponsavel() != null) {
                System.out.println("  Responsável: " + local.getResponsavel().getNome());
                System.out.println("    " + local.getTipoLocal() + " - " + local.getEndereco());
                System.out.println("    Telefone: " + local.getResponsavel().getTelefone());
            }
        }
        
        // Locais disponíveis com valor total inicial
        System.out.println("\n--- Locais disponíveis com custos ---");
        List<Local> disponiveis = repositorio.getLocaisDisponiveis();
        System.out.println("Locais disponíveis: " + disponiveis.size());
        for (Local local : disponiveis) {
            System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco());
            System.out.println("    Aluguel: R$" + String.format("%.2f", local.getValorAluguel()));
            if (local.isAceitaSeguro()) {
                System.out.println("    Seguro: R$" + String.format("%.2f", local.getValorSeguro()));
            }
            System.out.println("    Formas de caução: " + local.getFormasCaucao());
            System.out.println("    Valor total inicial: R$" + String.format("%.2f", local.getValorTotalInicial()));
            if (local.isCompartilhado()) {
                System.out.println("    Valor por pessoa: R$" + String.format("%.2f", local.getValorPorPessoa()));
            }
            System.out.println();
        }
        
        // Estatísticas
        System.out.println("\n--- Estatísticas por tipo ---");
        Map<String, Long> estatisticas = repositorio.getEstatisticasPorTipo();
        for (Map.Entry<String, Long> entry : estatisticas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " locais");
        }
        
        // Buscar por faixa de preço total inicial
        System.out.println("\n--- Locais com valor total inicial até R$ 3000 ---");
        for (Local local : todosLocais) {
            double valorTotal = local.getValorTotalInicial();
            if (valorTotal <= 3000.0) {
                System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco() + 
                                 " (R$" + String.format("%.2f", valorTotal) + " total inicial)");
            }
        }
    }
} 