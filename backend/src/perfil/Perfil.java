package perfil;

public class Perfil {
    private String nome;
    private String email;
    private String telefone;
    private String id;
    private static int contadorId = 1;
    
    public Perfil(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.id = "PERFIL_" + contadorId++;
    }
    
    // Getters e Setters
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Perfil{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Perfil perfil = (Perfil) obj;
        return id.equals(perfil.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
