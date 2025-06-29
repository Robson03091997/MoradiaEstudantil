package sistema;

import perfil.Perfil;
import perfil.Genero;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositório de usuários com persistência em banco de dados
 * Integra com sistema de segurança para criptografia de senhas
 */
public class UserRepository {
    private final DatabaseManager dbManager;
    private final SecurityManager securityManager;
    
    public UserRepository() {
        this.dbManager = DatabaseManager.getInstance();
        this.securityManager = SecurityManager.getInstance();
    }
    
    /**
     * Cadastra um novo usuário
     */
    public boolean cadastrarUsuario(String nome, String email, String senha, String telefone, Genero genero) {
        try {
            // Verificar se email já existe
            if (emailExiste(email)) {
                System.err.println("❌ Email já cadastrado: " + email);
                return false;
            }
            
            // Validar força da senha
            SecurityManager.PasswordStrength strength = securityManager.validatePasswordStrength(senha);
            if (strength == SecurityManager.PasswordStrength.WEAK) {
                System.err.println("❌ Senha muito fraca. Use pelo menos 8 caracteres com maiúsculas, minúsculas, números e símbolos.");
                return false;
            }
            
            // Gerar salt e criptografar senha
            String salt = securityManager.generateSalt();
            String senhaHash = securityManager.hashPassword(senha, salt);
            
            // Inserir no banco
            String sql = """
                INSERT INTO usuarios (nome, email, senha_hash, telefone, genero, salt)
                VALUES (?, ?, ?, ?, ?, ?)
            """;
            
            int userId = dbManager.executeInsert(sql, nome, email, senhaHash, telefone, genero.toString(), salt);
            
            if (userId > 0) {
                System.out.println("✅ Usuário cadastrado com sucesso! ID: " + userId);
                return true;
            } else {
                System.err.println("❌ Erro ao cadastrar usuário");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro no banco de dados: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Autentica um usuário
     */
    public Optional<Perfil> autenticarUsuario(String email, String senha) {
        try {
            String sql = "SELECT id, nome, email, senha_hash, telefone, genero, salt FROM usuarios WHERE email = ? AND ativo = TRUE";
            ResultSet rs = dbManager.executeQuery(sql, email);
            
            if (rs.next()) {
                String storedHash = rs.getString("senha_hash");
                String salt = rs.getString("salt");
                
                // Verificar senha
                if (securityManager.verifyPassword(senha, salt, storedHash)) {
                    Perfil perfil = new Perfil(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        Genero.valueOf(rs.getString("genero"))
                    );
                    
                    // Criar sessão
                    String sessionToken = securityManager.createUserSession(rs.getInt("id"), email);
                    System.out.println("✅ Login realizado com sucesso! Token: " + sessionToken);
                    
                    return Optional.of(perfil);
                } else {
                    System.err.println("❌ Senha incorreta para: " + email);
                }
            } else {
                System.err.println("❌ Usuário não encontrado: " + email);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro no banco de dados: " + e.getMessage());
        }
        
        return Optional.empty();
    }
    
    /**
     * Busca usuário por email
     */
    public Optional<Perfil> buscarPorEmail(String email) {
        try {
            String sql = "SELECT id, nome, email, telefone, genero FROM usuarios WHERE email = ? AND ativo = TRUE";
            ResultSet rs = dbManager.executeQuery(sql, email);
            
            if (rs.next()) {
                Perfil perfil = new Perfil(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    Genero.valueOf(rs.getString("genero"))
                );
                return Optional.of(perfil);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar usuário: " + e.getMessage());
        }
        
        return Optional.empty();
    }
    
    /**
     * Busca usuário por ID
     */
    public Optional<Perfil> buscarPorId(int id) {
        try {
            String sql = "SELECT id, nome, email, telefone, genero FROM usuarios WHERE id = ? AND ativo = TRUE";
            ResultSet rs = dbManager.executeQuery(sql, id);
            
            if (rs.next()) {
                Perfil perfil = new Perfil(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    Genero.valueOf(rs.getString("genero"))
                );
                return Optional.of(perfil);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar usuário: " + e.getMessage());
        }
        
        return Optional.empty();
    }
    
    /**
     * Lista todos os usuários ativos
     */
    public List<Perfil> listarUsuarios() {
        List<Perfil> usuarios = new ArrayList<>();
        
        try {
            String sql = "SELECT id, nome, email, telefone, genero FROM usuarios WHERE ativo = TRUE ORDER BY nome";
            ResultSet rs = dbManager.executeQuery(sql);
            
            while (rs.next()) {
                Perfil perfil = new Perfil(
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    Genero.valueOf(rs.getString("genero"))
                );
                usuarios.add(perfil);
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar usuários: " + e.getMessage());
        }
        
        return usuarios;
    }
    
    /**
     * Atualiza dados do usuário
     */
    public boolean atualizarUsuario(int id, String nome, String telefone, Genero genero) {
        try {
            String sql = "UPDATE usuarios SET nome = ?, telefone = ?, genero = ? WHERE id = ? AND ativo = TRUE";
            int rowsAffected = dbManager.executeUpdate(sql, nome, telefone, genero.toString(), id);
            
            if (rowsAffected > 0) {
                System.out.println("✅ Usuário atualizado com sucesso!");
                return true;
            } else {
                System.err.println("❌ Usuário não encontrado ou inativo");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Altera senha do usuário
     */
    public boolean alterarSenha(int id, String senhaAtual, String novaSenha) {
        try {
            // Verificar senha atual
            String sql = "SELECT senha_hash, salt FROM usuarios WHERE id = ? AND ativo = TRUE";
            ResultSet rs = dbManager.executeQuery(sql, id);
            
            if (rs.next()) {
                String storedHash = rs.getString("senha_hash");
                String salt = rs.getString("salt");
                
                if (securityManager.verifyPassword(senhaAtual, salt, storedHash)) {
                    // Validar nova senha
                    SecurityManager.PasswordStrength strength = securityManager.validatePasswordStrength(novaSenha);
                    if (strength == SecurityManager.PasswordStrength.WEAK) {
                        System.err.println("❌ Nova senha muito fraca");
                        return false;
                    }
                    
                    // Gerar novo salt e hash
                    String newSalt = securityManager.generateSalt();
                    String newHash = securityManager.hashPassword(novaSenha, newSalt);
                    
                    // Atualizar no banco
                    String updateSql = "UPDATE usuarios SET senha_hash = ?, salt = ? WHERE id = ?";
                    int rowsAffected = dbManager.executeUpdate(updateSql, newHash, newSalt, id);
                    
                    if (rowsAffected > 0) {
                        System.out.println("✅ Senha alterada com sucesso!");
                        return true;
                    }
                } else {
                    System.err.println("❌ Senha atual incorreta");
                }
            } else {
                System.err.println("❌ Usuário não encontrado");
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao alterar senha: " + e.getMessage());
        }
        
        return false;
    }
    
    /**
     * Desativa um usuário (soft delete)
     */
    public boolean desativarUsuario(int id) {
        try {
            String sql = "UPDATE usuarios SET ativo = FALSE WHERE id = ?";
            int rowsAffected = dbManager.executeUpdate(sql, id);
            
            if (rowsAffected > 0) {
                System.out.println("✅ Usuário desativado com sucesso!");
                return true;
            } else {
                System.err.println("❌ Usuário não encontrado");
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao desativar usuário: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Verifica se email já existe
     */
    private boolean emailExiste(String email) {
        try {
            String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ? AND ativo = TRUE";
            ResultSet rs = dbManager.executeQuery(sql, email);
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao verificar email: " + e.getMessage());
        }
        
        return false;
    }
    
    /**
     * Obtém estatísticas de usuários
     */
    public void printUserStats() {
        try {
            System.out.println("\n👥 ESTATÍSTICAS DE USUÁRIOS:");
            
            // Total de usuários
            ResultSet rs = dbManager.executeQuery("SELECT COUNT(*) FROM usuarios WHERE ativo = TRUE");
            if (rs.next()) {
                System.out.println("Total de usuários ativos: " + rs.getInt(1));
            }
            
            // Usuários por gênero
            rs = dbManager.executeQuery("SELECT genero, COUNT(*) FROM usuarios WHERE ativo = TRUE GROUP BY genero");
            while (rs.next()) {
                System.out.println("Gênero " + rs.getString("genero") + ": " + rs.getInt(2));
            }
            
            // Últimos cadastros
            rs = dbManager.executeQuery("SELECT nome, email, data_cadastro FROM usuarios WHERE ativo = TRUE ORDER BY data_cadastro DESC LIMIT 5");
            System.out.println("\nÚltimos cadastros:");
            while (rs.next()) {
                System.out.println("- " + rs.getString("nome") + " (" + rs.getString("email") + ") - " + rs.getTimestamp("data_cadastro"));
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao obter estatísticas: " + e.getMessage());
        }
    }
} 