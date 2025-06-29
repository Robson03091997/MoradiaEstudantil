package locais;

import perfil.Perfil;
import java.util.List;
import java.util.Map;

public class ExemploCompartilhamento {
    public static void main(String[] args) {
        // Obter instância do repositório
        RepositorioLocais repositorio = RepositorioLocais.getInstancia();
        
        // Criar alguns perfis de exemplo
        Perfil proprietario1 = new Perfil("João Silva", "joao@email.com", "(11) 99999-9999");
        Perfil proprietario2 = new Perfil("Maria Santos", "maria@email.com", "(11) 88888-8888");
        Perfil morador1 = new Perfil("Pedro Costa", "pedro@email.com", "(11) 77777-7777");
        Perfil morador2 = new Perfil("Ana Oliveira", "ana@email.com", "(11) 66666-6666");
        
        // Criar uma casa individual (não compartilhada)
        Casa casaIndividual = new Casa(
            "Rua das Flores, 123 - Centro",  // endereco
            2,                              // numeroBanheiros
            120.0,                          // areaConstruida
            3,                              // numeroMaximoInquilinos
            2500.0,                         // valorAluguel
            3,                              // numeroQuartos
            1                               // vagasGaragem
        );
        
        // Criar uma casa compartilhada para estudantes de graduação
        Casa casaCompartilhada = new Casa(
            "Rua dos Estudantes, 456 - Campus",  // endereco
            2,                                   // numeroBanheiros
            150.0,                               // areaConstruida
            3000.0,                              // valorAluguel
            4,                                   // numeroInquilinos
            Local.TipoCompartilhamento.MISTO,    // tipoCompartilhamento
            "Casa para estudantes de graduação. Ambiente tranquilo, próximo à universidade.", // descricao
            4,                                   // numeroQuartos
            2                                    // vagasGaragem
        );
        
        // Criar uma república feminina
        Republica republicaFeminina = new Republica(
            "Rua das Meninas, 789 - Vila Nova",  // endereco
            2,                                   // numeroBanheiros
            100.0,                               // areaConstruida
            5,                                   // numeroMaximoInquilinos
            2500.0,                              // valorAluguel
            true,                                // compartilhado
            5,                                   // numeroInquilinos
            Local.TipoCompartilhamento.FEMININO, // tipoCompartilhamento
            "República feminina para estudantes. Ambiente organizado e limpo." // descricao
        );
        
        // Criar um quarto individual
        Quarto quartoIndividual = new Quarto(
            "Rua da Universidade, 321 - Campus", // endereco
            1,                                   // numeroBanheiros
            15.0,                                // areaConstruida
            1,                                   // numeroMaximoInquilinos
            800.0,                               // valorAluguel
            false,                               // compartilhado
            1,                                   // numeroInquilinos
            null,                                // tipoCompartilhamento
            ""                                   // descricao
        );
        
        // Adicionar proprietários e moradores
        casaIndividual.adicionarProprietario(proprietario1);
        casaIndividual.adicionarMorador(morador1);
        
        casaCompartilhada.adicionarProprietario(proprietario2);
        casaCompartilhada.adicionarMorador(morador1);
        casaCompartilhada.adicionarMorador(morador2);
        
        republicaFeminina.adicionarProprietario(proprietario1);
        
        // Adicionar locais ao repositório
        repositorio.adicionarLocal(casaIndividual);
        repositorio.adicionarLocal(casaCompartilhada);
        repositorio.adicionarLocal(republicaFeminina);
        repositorio.adicionarLocal(quartoIndividual);
        
        // Demonstrar funcionalidades do repositório
        System.out.println("=== SISTEMA DE LOCAIS COM COMPARTILHAMENTO ===");
        System.out.println("Total de locais: " + repositorio.getQuantidadeLocais());
        
        // Listar todos os locais
        System.out.println("\n--- Todos os locais ---");
        List<Local> todosLocais = repositorio.listarTodos();
        for (Local local : todosLocais) {
            System.out.println(local);
        }
        
        // Buscar locais compartilhados
        System.out.println("\n--- Locais compartilhados ---");
        for (Local local : todosLocais) {
            if (local.isCompartilhado()) {
                System.out.println("Local compartilhado: " + local.getTipoLocal());
                System.out.println("  Endereço: " + local.getEndereco());
                System.out.println("  Tipo: " + local.getTipoCompartilhamento());
                System.out.println("  Número de inquilinos: " + local.getNumeroInquilinos());
                System.out.println("  Valor por pessoa: R$" + String.format("%.2f", local.getValorPorPessoa()));
                if (local.getDescricao() != null && !local.getDescricao().isEmpty()) {
                    System.out.println("  Descrição: " + local.getDescricao());
                }
                System.out.println();
            }
        }
        
        // Buscar locais por tipo de compartilhamento
        System.out.println("--- Locais femininos ---");
        for (Local local : todosLocais) {
            if (local.isCompartilhado() && local.getTipoCompartilhamento() == Local.TipoCompartilhamento.FEMININO) {
                System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco());
            }
        }
        
        // Locais disponíveis
        System.out.println("\n--- Locais disponíveis ---");
        List<Local> disponiveis = repositorio.getLocaisDisponiveis();
        System.out.println("Locais disponíveis: " + disponiveis.size());
        for (Local local : disponiveis) {
            System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco() + 
                             " (R$" + String.format("%.2f", local.getValorAluguel()) + ")");
            if (local.isCompartilhado()) {
                System.out.println("    Valor por pessoa: R$" + String.format("%.2f", local.getValorPorPessoa()));
            }
        }
        
        // Estatísticas
        System.out.println("\n--- Estatísticas por tipo ---");
        Map<String, Long> estatisticas = repositorio.getEstatisticasPorTipo();
        for (Map.Entry<String, Long> entry : estatisticas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " locais");
        }
        
        // Buscar por faixa de preço
        System.out.println("\n--- Locais com aluguel até R$ 1000 por pessoa ---");
        for (Local local : todosLocais) {
            double valorPorPessoa = local.getValorPorPessoa();
            if (valorPorPessoa <= 1000.0) {
                System.out.println("  " + local.getTipoLocal() + " - " + local.getEndereco() + 
                                 " (R$" + String.format("%.2f", valorPorPessoa) + " por pessoa)");
            }
        }
    }
} 