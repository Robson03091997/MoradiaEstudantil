package locais;

import java.util.ArrayList;
import java.util.List;
import perfil.Perfil;

public class Edicula extends Local {
    
    // Atributos específicos da Edícula
    private boolean temCozinha;
    private boolean temEntradaIndependente;
    
    public Edicula(String endereco, int numeroBanheiros, double areaConstruida, 
                   int numeroMaximoInquilinos, double valorAluguel, boolean compartilhado, 
                   int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                   String descricao, List<Local.FormaCaucao> formasCaucao, boolean aceitaSeguro, 
                   double valorSeguro, Perfil responsavel, boolean temCozinha, boolean temEntradaIndependente) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, compartilhado, 
              numeroInquilinos, tipoCompartilhamento, descricao, formasCaucao, aceitaSeguro, valorSeguro, responsavel);
        this.temCozinha = temCozinha;
        this.temEntradaIndependente = temEntradaIndependente;
    }
    
    // Construtor simplificado para edícula individual
    public Edicula(String endereco, int numeroBanheiros, double areaConstruida, 
                   int numeroMaximoInquilinos, double valorAluguel) {
        this(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
             false, 1, null, "", new ArrayList<>(), false, 0.0, null, true, true);
    }
    
    // Construtor para edícula compartilhada
    public Edicula(String endereco, int numeroBanheiros, double areaConstruida, 
                   double valorAluguel, int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                   String descricao) {
        this(endereco, numeroBanheiros, areaConstruida, numeroInquilinos, valorAluguel, 
             true, numeroInquilinos, tipoCompartilhamento, descricao, new ArrayList<>(), false, 0.0, null,
             true, true);
    }
    
    // Construtor com formas de caução
    public Edicula(String endereco, int numeroBanheiros, double areaConstruida, 
                   int numeroMaximoInquilinos, double valorAluguel, List<Local.FormaCaucao> formasCaucao,
                   boolean aceitaSeguro, double valorSeguro, Perfil responsavel) {
        this(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
             false, 1, null, "", formasCaucao, aceitaSeguro, valorSeguro, responsavel, true, true);
    }
    
    // Getters e Setters específicos da Edícula
    public boolean isTemCozinha() {
        return temCozinha;
    }
    
    public void setTemCozinha(boolean temCozinha) {
        this.temCozinha = temCozinha;
    }
    
    public boolean isTemEntradaIndependente() {
        return temEntradaIndependente;
    }
    
    public void setTemEntradaIndependente(boolean temEntradaIndependente) {
        this.temEntradaIndependente = temEntradaIndependente;
    }
    
    // Método para calcular valor por metro quadrado
    public double getValorPorMetroQuadrado() {
        return getAreaConstruida() > 0 ? getValorAluguel() / getAreaConstruida() : 0;
    }
    
    @Override
    public String getTipoLocal() {
        return "Edícula";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        
        if (temCozinha) sb.append(", cozinha");
        if (temEntradaIndependente) sb.append(", entrada independente");
        
        return sb.toString();
    }
}
