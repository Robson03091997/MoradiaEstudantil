package sistema;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Gerenciador de segurança para o sistema de locação
 * Responsável por criptografar senhas e gerenciar autenticação
 */
public class SecurityManager {
    private static SecurityManager instance;
    private final SecureRandom random = new SecureRandom();
    
    // Simulação de sessões ativas (em produção, usar Redis ou similar)
    private final Map<String, UserSession> activeSessions = new HashMap<>();
    
    private SecurityManager() {}
    
    public static SecurityManager getInstance() {
        if (instance == null) {
            instance = new SecurityManager();
        }
        return instance;
    }
    
    /**
     * Gera um salt aleatório para criptografia
     */
    public String generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    
    /**
     * Criptografa uma senha usando SHA-256 + Salt
     */
    public String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String saltedPassword = password + salt;
            byte[] hashedBytes = md.digest(saltedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criptografar senha", e);
        }
    }
    
    /**
     * Verifica se uma senha está correta
     */
    public boolean verifyPassword(String password, String salt, String storedHash) {
        String inputHash = hashPassword(password, salt);
        return inputHash.equals(storedHash);
    }
    
    /**
     * Gera um token de sessão
     */
    public String generateSessionToken() {
        byte[] tokenBytes = new byte[32];
        random.nextBytes(tokenBytes);
        return Base64.getEncoder().encodeToString(tokenBytes);
    }
    
    /**
     * Cria uma nova sessão de usuário
     */
    public String createUserSession(int userId, String email) {
        String token = generateSessionToken();
        UserSession session = new UserSession(userId, email, System.currentTimeMillis());
        activeSessions.put(token, session);
        return token;
    }
    
    /**
     * Verifica se um token de sessão é válido
     */
    public boolean isValidSession(String token) {
        UserSession session = activeSessions.get(token);
        if (session == null) {
            return false;
        }
        
        // Sessão expira após 24 horas
        long sessionTimeout = 24 * 60 * 60 * 1000; // 24 horas em millisegundos
        if (System.currentTimeMillis() - session.getCreatedAt() > sessionTimeout) {
            activeSessions.remove(token);
            return false;
        }
        
        return true;
    }
    
    /**
     * Obtém informações da sessão
     */
    public UserSession getSession(String token) {
        return activeSessions.get(token);
    }
    
    /**
     * Remove uma sessão
     */
    public void removeSession(String token) {
        activeSessions.remove(token);
    }
    
    /**
     * Limpa sessões expiradas
     */
    public void cleanupExpiredSessions() {
        long sessionTimeout = 24 * 60 * 60 * 1000;
        long currentTime = System.currentTimeMillis();
        
        activeSessions.entrySet().removeIf(entry -> 
            currentTime - entry.getValue().getCreatedAt() > sessionTimeout
        );
    }
    
    /**
     * Gera uma senha forte aleatória
     */
    public String generateStrongPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        StringBuilder password = new StringBuilder();
        
        // Garantir pelo menos um de cada tipo
        password.append(chars.charAt(random.nextInt(26))); // Maiúscula
        password.append(chars.charAt(26 + random.nextInt(26))); // Minúscula
        password.append(chars.charAt(52 + random.nextInt(10))); // Número
        password.append(chars.charAt(62 + random.nextInt(8))); // Símbolo
        
        // Adicionar caracteres aleatórios
        for (int i = 4; i < 12; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        
        // Embaralhar a senha
        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }
        
        return new String(passwordArray);
    }
    
    /**
     * Valida força de uma senha
     */
    public PasswordStrength validatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return PasswordStrength.WEAK;
        }
        
        boolean hasUpperCase = password.matches(".*[A-Z].*");
        boolean hasLowerCase = password.matches(".*[a-z].*");
        boolean hasNumbers = password.matches(".*\\d.*");
        boolean hasSpecialChars = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        
        int score = 0;
        if (hasUpperCase) score++;
        if (hasLowerCase) score++;
        if (hasNumbers) score++;
        if (hasSpecialChars) score++;
        if (password.length() >= 12) score++;
        
        if (score >= 4) return PasswordStrength.STRONG;
        if (score >= 3) return PasswordStrength.MEDIUM;
        return PasswordStrength.WEAK;
    }
    
    /**
     * Classe interna para representar uma sessão de usuário
     */
    public static class UserSession {
        private final int userId;
        private final String email;
        private final long createdAt;
        
        public UserSession(int userId, String email, long createdAt) {
            this.userId = userId;
            this.email = email;
            this.createdAt = createdAt;
        }
        
        public int getUserId() { return userId; }
        public String getEmail() { return email; }
        public long getCreatedAt() { return createdAt; }
    }
    
    /**
     * Enum para força da senha
     */
    public enum PasswordStrength {
        WEAK("Fraca"),
        MEDIUM("Média"),
        STRONG("Forte");
        
        private final String description;
        
        PasswordStrength(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
} 