package locais;

import java.util.ArrayList;
import java.util.List;
import perfil.Perfil;

public class PredioKit extends Local {
    
    // Atributos específicos do Prédio de Kitnets
    private int numeroAndares;
    private int numeroKitnets;
    private boolean temElevador;
    private boolean temPortaria;
    
    public PredioKit(String endereco, int numeroBanheiros, double areaConstruida, 
                     int numeroMaximoInquilinos, double valorAluguel, boolean compartilhado, 
                     int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                     String descricao, List<Local.FormaCaucao> formasCaucao, boolean aceitaSeguro, 
                     double valorSeguro, Perfil responsavel, int numeroAndares, int numeroKitnets, 
                     boolean temElevador, boolean temPortaria) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, compartilhado, 
              numeroInquilinos, tipoCompartilhamento, descricao, formasCaucao, aceitaSeguro, valorSeguro, responsavel);
        this.numeroAndares = numeroAndares;
        this.numeroKitnets = numeroKitnets;
        this.temElevador = temElevador;
        this.temPortaria = temPortaria;
    }
    
    // Construtor simplificado para prédio de kitnets
    public PredioKit(String endereco, int numeroBanheiros, double areaConstruida, 
                     int numeroMaximoInquilinos, double valorAluguel, int numeroAndares, int numeroKitnets) {
        this(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
             false, 1, null, "", new ArrayList<>(), false, 0.0, null, numeroAndares, numeroKitnets, 
             false, false);
    }
    
    // Construtor para prédio compartilhado
    public PredioKit(String endereco, int numeroBanheiros, double areaConstruida, 
                     double valorAluguel, int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                     String descricao, int numeroAndares, int numeroKitnets) {
        this(endereco, numeroBanheiros, areaConstruida, numeroInquilinos, valorAluguel, 
             true, numeroInquilinos, tipoCompartilhamento, descricao, new ArrayList<>(), false, 0.0, null,
             numeroAndares, numeroKitnets, false, false);
    }
    
    // Construtor com formas de caução
    public PredioKit(String endereco, int numeroBanheiros, double areaConstruida, 
                     int numeroMaximoInquilinos, double valorAluguel, List<Local.FormaCaucao> formasCaucao,
                     boolean aceitaSeguro, double valorSeguro, Perfil responsavel, int numeroAndares, int numeroKitnets) {
        this(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
             false, 1, null, "", formasCaucao, aceitaSeguro, valorSeguro, responsavel, numeroAndares, numeroKitnets,
             false, false);
    }
    
    // Getters e Setters específicos do Prédio de Kitnets
    public int getNumeroAndares() {
        return numeroAndares;
    }
    
    public void setNumeroAndares(int numeroAndares) {
        this.numeroAndares = numeroAndares;
    }
    
    public int getNumeroKitnets() {
        return numeroKitnets;
    }
    
    public void setNumeroKitnets(int numeroKitnets) {
        this.numeroKitnets = numeroKitnets;
    }
    
    public boolean isTemElevador() {
        return temElevador;
    }
    
    public void setTemElevador(boolean temElevador) {
        this.temElevador = temElevador;
    }
    
    public boolean isTemPortaria() {
        return temPortaria;
    }
    
    public void setTemPortaria(boolean temPortaria) {
        this.temPortaria = temPortaria;
    }
    
    // Método para calcular valor por kitnet
    public double getValorPorKitnet() {
        return numeroKitnets > 0 ? getValorAluguel() / numeroKitnets : 0;
    }
    
    @Override
    public String getTipoLocal() {
        return "Prédio de Kitnets";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", andares=").append(numeroAndares);
        sb.append(", kitnets=").append(numeroKitnets);
        
        if (temElevador) sb.append(", elevador");
        if (temPortaria) sb.append(", portaria");
        
        return sb.toString();
    }
}
