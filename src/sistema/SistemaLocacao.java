package sistema;

import locais.*;
import perfil.Perfil;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

public class SistemaLocacao {
    private static Scanner scanner = new Scanner(System.in);
    private static RepositorioLocais repositorio = RepositorioLocais.getInstancia();
    private static List<Perfil> usuarios = new ArrayList<>();
    private static Perfil usuarioLogado = null;
    
    public static void main(String[] args) {
        inicializarDadosExemplo();
        
        while (true) {
            mostrarMenuPrincipal();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    criarPerfil();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE LOCAÇÃO DE IMÓVEIS ===");
        System.out.println("1. Fazer Login");
        System.out.println("2. Criar Perfil");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void mostrarMenuUsuario() {
        System.out.println("\n=== MENU DO USUÁRIO ===");
        System.out.println("Bem-vindo, " + usuarioLogado.getNome() + "!");
        System.out.println("1. Ver Imóveis Disponíveis");
        System.out.println("2. Disponibilizar Imóvel");
        System.out.println("3. Meus Imóveis");
        System.out.println("4. Buscar por Tipo");
        System.out.println("5. Estatísticas");
        System.out.println("6. Fazer Logout");
        System.out.print("Escolha uma opção: ");
    }
    
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void fazerLogin() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        // Simulação de login (em um sistema real seria com hash de senha)
        for (Perfil usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getTelefone().equals(senha)) {
                usuarioLogado = usuario;
                System.out.println("Login realizado com sucesso!");
                menuUsuario();
                return;
            }
        }
        System.out.println("Email ou senha incorretos!");
    }
    
    private static void criarPerfil() {
        System.out.println("\n=== CRIAR PERFIL ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        // Verificar se email já existe
        for (Perfil usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                System.out.println("Email já cadastrado!");
                return;
            }
        }
        
        Perfil novoUsuario = new Perfil(nome, email, telefone);
        usuarios.add(novoUsuario);
        System.out.println("Perfil criado com sucesso!");
        System.out.println("Use seu telefone como senha para fazer login.");
    }
    
    private static void menuUsuario() {
        while (usuarioLogado != null) {
            mostrarMenuUsuario();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    verImoveisDisponiveis();
                    break;
                case 2:
                    disponibilizarImovel();
                    break;
                case 3:
                    meusImoveis();
                    break;
                case 4:
                    buscarPorTipo();
                    break;
                case 5:
                    mostrarEstatisticas();
                    break;
                case 6:
                    usuarioLogado = null;
                    System.out.println("Logout realizado!");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void verImoveisDisponiveis() {
        System.out.println("\n=== IMÓVEIS DISPONÍVEIS ===");
        List<Local> locais = repositorio.listarTodos();
        
        if (locais.isEmpty()) {
            System.out.println("Nenhum imóvel disponível no momento.");
            return;
        }
        
        for (int i = 0; i < locais.size(); i++) {
            Local local = locais.get(i);
            System.out.println((i + 1) + ". " + local.getTipoLocal() + " - " + local.getEndereco());
            System.out.println("   Valor: R$" + String.format("%.2f", local.getValorAluguel()));
            System.out.println("   Área: " + local.getAreaConstruida() + "m²");
            if (local.isCompartilhado()) {
                System.out.println("   Compartilhado: " + local.getTipoCompartilhamento());
            }
            System.out.println();
        }
        
        System.out.print("Digite o número do imóvel para ver detalhes (0 para voltar): ");
        int escolha = lerOpcao();
        
        if (escolha > 0 && escolha <= locais.size()) {
            mostrarDetalhesImovel(locais.get(escolha - 1));
        }
    }
    
    private static void mostrarDetalhesImovel(Local local) {
        System.out.println("\n=== DETALHES DO IMÓVEL ===");
        System.out.println("Tipo: " + local.getTipoLocal());
        System.out.println("Endereço: " + local.getEndereco());
        System.out.println("Valor: R$" + String.format("%.2f", local.getValorAluguel()));
        System.out.println("Área: " + local.getAreaConstruida() + "m²");
        System.out.println("Banheiros: " + local.getNumeroBanheiros());
        System.out.println("Capacidade: " + local.getNumeroMaximoInquilinos() + " pessoas");
        
        if (local.isCompartilhado()) {
            System.out.println("Compartilhado: " + local.getTipoCompartilhamento());
            System.out.println("Inquilinos atuais: " + local.getNumeroInquilinos());
        }
        
        // Detalhes específicos por tipo
        if (local instanceof Casa) {
            Casa casa = (Casa) local;
            System.out.println("Quartos: " + casa.getNumeroQuartos());
            System.out.println("Vagas de garagem: " + casa.getVagasGaragem());
            System.out.println("Valor por quarto: R$" + String.format("%.2f", casa.getValorPorQuarto()));
        } else if (local instanceof Republica) {
            Republica republica = (Republica) local;
            System.out.println("Quartos: " + republica.getNumeroQuartos());
            System.out.println("Cozinha compartilhada: " + republica.isTemCozinhaCompartilhada());
            System.out.println("Internet: " + republica.isTemInternet());
        } else if (local instanceof Pensionato) {
            Pensionato pensionato = (Pensionato) local;
            System.out.println("Quartos: " + pensionato.getNumeroQuartos());
            System.out.println("Refeições inclusas: " + pensionato.isTemRefeicoesInclusas());
            System.out.println("Limpeza inclusa: " + pensionato.isTemLimpezaInclusa());
            System.out.println("Horário de fechamento: " + pensionato.isTemHorarioFechamento());
            System.out.println("Regras rigorosas: " + pensionato.isTemRegrasRigorosas());
        } else if (local instanceof Kitnet) {
            Kitnet kitnet = (Kitnet) local;
            System.out.println("Cozinha: " + kitnet.isTemCozinha());
            System.out.println("Sacada: " + kitnet.isTemSacada());
            System.out.println("Valor por m²: R$" + String.format("%.2f", kitnet.getValorPorMetroQuadrado()));
        } else if (local instanceof Quarto) {
            Quarto quarto = (Quarto) local;
            System.out.println("Banheiro privativo: " + quarto.isTemBanheiroPrivativo());
            System.out.println("Ar condicionado: " + quarto.isTemArCondicionado());
        }
        
        if (local.getResponsavel() != null) {
            System.out.println("Responsável: " + local.getResponsavel().getNome());
            System.out.println("Contato: " + local.getResponsavel().getTelefone());
        }
        
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
    
    private static void disponibilizarImovel() {
        System.out.println("\n=== DISPONIBILIZAR IMÓVEL ===");
        System.out.println("Tipos disponíveis:");
        System.out.println("1. Casa");
        System.out.println("2. República");
        System.out.println("3. Pensionato");
        System.out.println("4. Kitnet");
        System.out.println("5. Quarto");
        System.out.println("6. Edícula");
        System.out.println("7. Prédio de Kitnets");
        System.out.print("Escolha o tipo: ");
        
        int tipo = lerOpcao();
        if (tipo < 1 || tipo > 7) {
            System.out.println("Opção inválida!");
            return;
        }
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Número de banheiros: ");
        int banheiros = Integer.parseInt(scanner.nextLine());
        System.out.print("Área construída (m²): ");
        double area = Double.parseDouble(scanner.nextLine());
        System.out.print("Número máximo de inquilinos: ");
        int maxInquilinos = Integer.parseInt(scanner.nextLine());
        System.out.print("Valor do aluguel: ");
        double valor = Double.parseDouble(scanner.nextLine());
        
        Local novoLocal = null;
        
        switch (tipo) {
            case 1: // Casa
                System.out.print("Número de quartos: ");
                int quartos = Integer.parseInt(scanner.nextLine());
                System.out.print("Vagas de garagem: ");
                int vagas = Integer.parseInt(scanner.nextLine());
                novoLocal = new Casa(endereco, banheiros, area, maxInquilinos, valor, quartos, vagas);
                break;
            case 2: // República
                System.out.print("Número de quartos: ");
                quartos = Integer.parseInt(scanner.nextLine());
                novoLocal = new Republica(endereco, banheiros, area, maxInquilinos, valor, quartos);
                break;
            case 3: // Pensionato
                System.out.print("Número de quartos: ");
                quartos = Integer.parseInt(scanner.nextLine());
                novoLocal = new Pensionato(endereco, banheiros, area, maxInquilinos, valor, quartos);
                break;
            case 4: // Kitnet
                novoLocal = new Kitnet(endereco, area, valor);
                break;
            case 5: // Quarto
                novoLocal = new Quarto(endereco, area, valor);
                break;
            case 6: // Edícula
                novoLocal = new Edicula(endereco, banheiros, area, maxInquilinos, valor);
                break;
            case 7: // Prédio de Kitnets
                System.out.print("Número de andares: ");
                int andares = Integer.parseInt(scanner.nextLine());
                System.out.print("Número de kitnets: ");
                int kitnets = Integer.parseInt(scanner.nextLine());
                novoLocal = new PredioKit(endereco, banheiros, area, maxInquilinos, valor, andares, kitnets);
                break;
        }
        
        if (novoLocal != null) {
            novoLocal.adicionarProprietario(usuarioLogado);
            repositorio.adicionarLocal(novoLocal);
            System.out.println("Imóvel cadastrado com sucesso!");
        }
    }
    
    private static void meusImoveis() {
        System.out.println("\n=== MEUS IMÓVEIS ===");
        List<Local> meusLocais = new ArrayList<>();
        
        for (Local local : repositorio.listarTodos()) {
            if (local.getProprietarios().contains(usuarioLogado)) {
                meusLocais.add(local);
            }
        }
        
        if (meusLocais.isEmpty()) {
            System.out.println("Você não possui imóveis cadastrados.");
            return;
        }
        
        for (int i = 0; i < meusLocais.size(); i++) {
            Local local = meusLocais.get(i);
            System.out.println((i + 1) + ". " + local.getTipoLocal() + " - " + local.getEndereco());
            System.out.println("   Valor: R$" + String.format("%.2f", local.getValorAluguel()));
        }
    }
    
    private static void buscarPorTipo() {
        System.out.println("\n=== BUSCAR POR TIPO ===");
        System.out.println("1. Casa");
        System.out.println("2. República");
        System.out.println("3. Pensionato");
        System.out.println("4. Kitnet");
        System.out.println("5. Quarto");
        System.out.println("6. Edícula");
        System.out.println("7. Prédio de Kitnets");
        System.out.print("Escolha o tipo: ");
        
        int tipo = lerOpcao();
        if (tipo < 1 || tipo > 7) {
            System.out.println("Opção inválida!");
            return;
        }
        
        String tipoBusca = "";
        switch (tipo) {
            case 1: tipoBusca = "Casa"; break;
            case 2: tipoBusca = "República"; break;
            case 3: tipoBusca = "Pensionato"; break;
            case 4: tipoBusca = "Kitnet"; break;
            case 5: tipoBusca = "Quarto"; break;
            case 6: tipoBusca = "Edícula"; break;
            case 7: tipoBusca = "Prédio de Kitnets"; break;
        }
        
        List<Local> locaisFiltrados = new ArrayList<>();
        for (Local local : repositorio.listarTodos()) {
            if (local.getTipoLocal().equals(tipoBusca)) {
                locaisFiltrados.add(local);
            }
        }
        
        if (locaisFiltrados.isEmpty()) {
            System.out.println("Nenhum imóvel do tipo " + tipoBusca + " encontrado.");
            return;
        }
        
        System.out.println("\nImóveis do tipo " + tipoBusca + ":");
        for (Local local : locaisFiltrados) {
            System.out.println("- " + local.getEndereco() + " - R$" + String.format("%.2f", local.getValorAluguel()));
        }
    }
    
    private static void mostrarEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        Map<String, Long> estatisticas = repositorio.getEstatisticasPorTipo();
        
        System.out.println("Total de imóveis: " + repositorio.getQuantidadeLocais());
        System.out.println("\nPor tipo:");
        for (Map.Entry<String, Long> entry : estatisticas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " imóveis");
        }
        
        // Valor médio por tipo
        System.out.println("\nValor médio por tipo:");
        for (String tipo : estatisticas.keySet()) {
            double valorTotal = 0;
            int count = 0;
            for (Local local : repositorio.listarTodos()) {
                if (local.getTipoLocal().equals(tipo)) {
                    valorTotal += local.getValorAluguel();
                    count++;
                }
            }
            if (count > 0) {
                System.out.println(tipo + ": R$" + String.format("%.2f", valorTotal / count));
            }
        }
    }
    
    private static void inicializarDadosExemplo() {
        // Criar usuários de exemplo
        Perfil joao = new Perfil("João Silva", "joao@email.com", "123");
        Perfil maria = new Perfil("Maria Santos", "maria@email.com", "456");
        Perfil carlos = new Perfil("Carlos Oliveira", "carlos@email.com", "789");
        
        usuarios.add(joao);
        usuarios.add(maria);
        usuarios.add(carlos);
        
        // Criar imóveis de exemplo
        Casa casa = new Casa("Rua das Flores, 123 - Centro", 2, 120.0, 3, 2500.0, 3, 1);
        casa.adicionarProprietario(joao);
        
        Kitnet kitnet = new Kitnet("Av. Principal, 456 - Jardim", 35.0, 1200.0);
        kitnet.adicionarProprietario(maria);
        
        Quarto quarto = new Quarto("Rua da Universidade, 789 - Campus", 15.0, 800.0);
        quarto.adicionarProprietario(carlos);
        
        Republica republica = new Republica("Rua dos Estudantes, 321 - Vila Nova", 2, 100.0, 5, 2500, 5);
        republica.adicionarProprietario(joao);
        
        Pensionato pensionato = new Pensionato("Rua dos Estudantes, 654 - Vila Nova", 2, 120.0, 6, 3000, 6);
        pensionato.adicionarProprietario(maria);
        
        Edicula edicula = new Edicula("Rua do Comércio, 654 - Centro", 1, 45.0, 2, 1500.0);
        edicula.adicionarProprietario(carlos);
        
        PredioKit predioKit = new PredioKit("Av. das Indústrias, 987 - Industrial", 1, 40.0, 1, 1100.0, 3, 12);
        predioKit.adicionarProprietario(joao);
        
        // Adicionar ao repositório
        repositorio.adicionarLocal(casa);
        repositorio.adicionarLocal(kitnet);
        repositorio.adicionarLocal(quarto);
        repositorio.adicionarLocal(republica);
        repositorio.adicionarLocal(pensionato);
        repositorio.adicionarLocal(edicula);
        repositorio.adicionarLocal(predioKit);
        
        System.out.println("Sistema inicializado com dados de exemplo!");
        System.out.println("Usuários disponíveis para teste:");
        System.out.println("- joao@email.com / 123");
        System.out.println("- maria@email.com / 456");
        System.out.println("- carlos@email.com / 789");
    }
} 