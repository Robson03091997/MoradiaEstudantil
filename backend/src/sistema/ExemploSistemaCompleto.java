package sistema;

import perfil.Perfil;
import perfil.Genero;
import locais.*;
import java.util.List;
import java.util.Optional;

/**
 * Exemplo completo do sistema de locação com persistência em banco de dados
 * Demonstra cadastro de usuários, autenticação e gerenciamento de imóveis
 */
public class ExemploSistemaCompleto {
    
    public static void main(String[] args) {
        System.out.println("🏠 SISTEMA DE LOCAÇÃO DE IMÓVEIS - MC322");
        System.out.println("==========================================\n");
        
        // Inicializar componentes do sistema
        DatabaseManager dbManager = DatabaseManager.getInstance();
        SecurityManager securityManager = SecurityManager.getInstance();
        UserRepository userRepo = new UserRepository();
        SistemaLocacao sistema = new SistemaLocacao();
        
        // Demonstrar funcionalidades
        demonstrarCadastroUsuarios(userRepo, securityManager);
        demonstrarAutenticacao(userRepo);
        demonstrarImoveis(sistema);
        demonstrarEstatisticas(dbManager, userRepo);
        demonstrarSeguranca(securityManager);
        
        // Fechar conexão com banco
        dbManager.closeConnection();
        
        System.out.println("\n✅ Demonstração concluída com sucesso!");
    }
    
    /**
     * Demonstra o cadastro de usuários
     */
    private static void demonstrarCadastroUsuarios(UserRepository userRepo, SecurityManager securityManager) {
        System.out.println("👥 CADASTRO DE USUÁRIOS");
        System.out.println("------------------------");
        
        // Cadastrar usuários de exemplo
        boolean sucesso1 = userRepo.cadastrarUsuario(
            "João Silva", 
            "joao@email.com", 
            "Senha123!", 
            "(11) 99999-9999", 
            Genero.MASCULINO
        );
        
        boolean sucesso2 = userRepo.cadastrarUsuario(
            "Maria Santos", 
            "maria@email.com", 
            "MinhaSenha456@", 
            "(11) 88888-8888", 
            Genero.FEMININO
        );
        
        boolean sucesso3 = userRepo.cadastrarUsuario(
            "Carlos Oliveira", 
            "carlos@email.com", 
            "Carlos789#", 
            "(11) 77777-7777", 
            Genero.OUTRO
        );
        
        // Tentar cadastrar email duplicado
        boolean sucesso4 = userRepo.cadastrarUsuario(
            "João Silva", 
            "joao@email.com", 
            "OutraSenha123!", 
            "(11) 66666-6666", 
            Genero.MASCULINO
        );
        
        // Tentar cadastrar com senha fraca
        boolean sucesso5 = userRepo.cadastrarUsuario(
            "Ana Teste", 
            "ana@email.com", 
            "123", 
            "(11) 55555-5555", 
            Genero.FEMININO
        );
        
        System.out.println();
    }
    
    /**
     * Demonstra autenticação de usuários
     */
    private static void demonstrarAutenticacao(UserRepository userRepo) {
        System.out.println("🔐 AUTENTICAÇÃO DE USUÁRIOS");
        System.out.println("----------------------------");
        
        // Login com credenciais corretas
        Optional<Perfil> perfil1 = userRepo.autenticarUsuario("joao@email.com", "Senha123!");
        if (perfil1.isPresent()) {
            System.out.println("✅ Login bem-sucedido: " + perfil1.get().getNome());
        }
        
        // Login com senha incorreta
        Optional<Perfil> perfil2 = userRepo.autenticarUsuario("joao@email.com", "SenhaErrada");
        if (perfil2.isEmpty()) {
            System.out.println("❌ Login falhou: senha incorreta");
        }
        
        // Login com email inexistente
        Optional<Perfil> perfil3 = userRepo.autenticarUsuario("inexistente@email.com", "Senha123!");
        if (perfil3.isEmpty()) {
            System.out.println("❌ Login falhou: usuário não encontrado");
        }
        
        System.out.println();
    }
    
    /**
     * Demonstra gerenciamento de imóveis
     */
    private static void demonstrarImoveis(SistemaLocacao sistema) {
        System.out.println("🏠 GERENCIAMENTO DE IMÓVEIS");
        System.out.println("----------------------------");
        
        // Criar diferentes tipos de imóveis
        Casa casa = new Casa(
            "Casa com 3 quartos",
            "Rua das Flores, 123 - São Paulo",
            2500.0,
            "Casa espaçosa com jardim e garagem",
            "João Silva",
            "joao@email.com",
            "(11) 99999-9999",
            Local.FormaCaucao.DINHEIRO,
            Local.TipoCompartilhamento.INDIVIDUAL
        );
        
        Kitnet kitnet = new Kitnet(
            "Kitnet mobiliado",
            "Av. Paulista, 1000 - São Paulo",
            1200.0,
            "Kitnet completo com móveis e eletrodomésticos",
            "Maria Santos",
            "maria@email.com",
            "(11) 88888-8888",
            Local.FormaCaucao.FIANCA,
            Local.TipoCompartilhamento.INDIVIDUAL
        );
        
        Quarto quarto = new Quarto(
            "Quarto em república",
            "Rua Augusta, 500 - São Paulo",
            800.0,
            "Quarto individual em república estudantil",
            "Carlos Oliveira",
            "carlos@email.com",
            "(11) 77777-7777",
            Local.FormaCaucao.DINHEIRO,
            Local.TipoCompartilhamento.COMPARTILHADO
        );
        
        // Cadastrar imóveis no sistema
        sistema.cadastrarLocal(casa);
        sistema.cadastrarLocal(kitnet);
        sistema.cadastrarLocal(quarto);
        
        System.out.println("✅ Imóveis cadastrados no sistema");
        
        // Buscar imóveis por tipo
        List<Local> casas = sistema.buscarPorTipo("Casa");
        System.out.println("🏠 Casas encontradas: " + casas.size());
        
        List<Local> kitnets = sistema.buscarPorTipo("Kitnet");
        System.out.println("🏢 Kitnets encontradas: " + kitnets.size());
        
        // Buscar por faixa de preço
        List<Local> imoveisAte1500 = sistema.buscarPorPreco(0.0, 1500.0);
        System.out.println("💰 Imóveis até R$ 1.500: " + imoveisAte1500.size());
        
        System.out.println();
    }
    
    /**
     * Demonstra estatísticas do sistema
     */
    private static void demonstrarEstatisticas(DatabaseManager dbManager, UserRepository userRepo) {
        System.out.println("📊 ESTATÍSTICAS DO SISTEMA");
        System.out.println("---------------------------");
        
        // Estatísticas do banco de dados
        dbManager.printDatabaseStats();
        
        // Estatísticas de usuários
        userRepo.printUserStats();
        
        System.out.println();
    }
    
    /**
     * Demonstra funcionalidades de segurança
     */
    private static void demonstrarSeguranca(SecurityManager securityManager) {
        System.out.println("🔒 FUNCIONALIDADES DE SEGURANÇA");
        System.out.println("-------------------------------");
        
        // Gerar senhas fortes
        String senhaForte1 = securityManager.generateStrongPassword();
        String senhaForte2 = securityManager.generateStrongPassword();
        
        System.out.println("🔑 Senha forte gerada 1: " + senhaForte1);
        System.out.println("🔑 Senha forte gerada 2: " + senhaForte2);
        
        // Validar força de senhas
        String senhaFraca = "123";
        String senhaMedia = "Senha123";
        String senhaForte = "MinhaSenha123!@#";
        
        System.out.println("\n📋 Validação de força de senhas:");
        System.out.println("Senha '123': " + securityManager.validatePasswordStrength(senhaFraca).getDescription());
        System.out.println("Senha 'Senha123': " + securityManager.validatePasswordStrength(senhaMedia).getDescription());
        System.out.println("Senha 'MinhaSenha123!@#': " + securityManager.validatePasswordStrength(senhaForte).getDescription());
        
        // Demonstrar criptografia
        String senhaOriginal = "MinhaSenha123!";
        String salt = securityManager.generateSalt();
        String hash = securityManager.hashPassword(senhaOriginal, salt);
        
        System.out.println("\n🔐 Demonstração de criptografia:");
        System.out.println("Senha original: " + senhaOriginal);
        System.out.println("Salt gerado: " + salt);
        System.out.println("Hash da senha: " + hash);
        
        // Verificar senha
        boolean senhaCorreta = securityManager.verifyPassword(senhaOriginal, salt, hash);
        boolean senhaIncorreta = securityManager.verifyPassword("SenhaErrada", salt, hash);
        
        System.out.println("Verificação com senha correta: " + (senhaCorreta ? "✅" : "❌"));
        System.out.println("Verificação com senha incorreta: " + (senhaIncorreta ? "✅" : "❌"));
        
        System.out.println();
    }
} 