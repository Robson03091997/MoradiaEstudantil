package locais;

import java.util.List;
import perfil.Perfil;

public class Republica extends Casa {
    
    // Atributos específicos da República
    private boolean temCozinhaCompartilhada;
    private boolean temInternet;
    
    public Republica(String endereco, int numeroBanheiros, double areaConstruida, 
                     int numeroMaximoInquilinos, double valorAluguel, int numeroQuartos, 
                     int vagasGaragem, boolean temCozinhaCompartilhada, boolean temInternet) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
              numeroQuartos, vagasGaragem);
        this.temCozinhaCompartilhada = temCozinhaCompartilhada;
        this.temInternet = temInternet;
    }
    
    // Construtor simplificado para república
    public Republica(String endereco, int numeroBanheiros, double areaConstruida, 
                     int numeroMaximoInquilinos, double valorAluguel, int numeroQuartos) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
             numeroQuartos, 0);
        this.temCozinhaCompartilhada = true;
        this.temInternet = false;
    }
    
    // Construtor com formas de caução
    public Republica(String endereco, int numeroBanheiros, double areaConstruida, 
                     int numeroMaximoInquilinos, double valorAluguel, List<Local.FormaCaucao> formasCaucao,
                     boolean aceitaSeguro, double valorSeguro, Perfil responsavel, int numeroQuartos, 
                     int vagasGaragem) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
              formasCaucao, aceitaSeguro, valorSeguro, responsavel, numeroQuartos, vagasGaragem);
        this.temCozinhaCompartilhada = true;
        this.temInternet = false;
    }
    
    // Getters e Setters específicos da República
    public boolean isTemCozinhaCompartilhada() {
        return temCozinhaCompartilhada;
    }
    
    public void setTemCozinhaCompartilhada(boolean temCozinhaCompartilhada) {
        this.temCozinhaCompartilhada = temCozinhaCompartilhada;
    }
    
    public boolean isTemInternet() {
        return temInternet;
    }
    
    public void setTemInternet(boolean temInternet) {
        this.temInternet = temInternet;
    }
    
    @Override
    public String getTipoLocal() {
        return "República";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        
        if (temCozinhaCompartilhada) sb.append(", cozinha compartilhada");
        if (temInternet) sb.append(", internet");
        
        return sb.toString();
    }
}
