package locais;

import java.util.ArrayList;
import java.util.List;
import perfil.Perfil;

public class Kitnet extends Local {
    
    // Atributos específicos da Kitnet
    private boolean temCozinha;
    private boolean temSacada;
    
    public Kitnet(String endereco, double areaConstruida, 
                  double valorAluguel, boolean compartilhado, 
                  int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                  String descricao, List<Local.FormaCaucao> formasCaucao, boolean aceitaSeguro, 
                  double valorSeguro, Perfil responsavel, boolean temCozinha, boolean temSacada) {
        super(endereco, 1, areaConstruida, numeroInquilinos, valorAluguel, compartilhado, 
              numeroInquilinos, tipoCompartilhamento, descricao, formasCaucao, aceitaSeguro, valorSeguro, responsavel);
        this.temCozinha = temCozinha;
        this.temSacada = temSacada;
    }
    
    // Construtor simplificado para kitnet individual
    public Kitnet(String endereco, double areaConstruida, double valorAluguel) {
        this(endereco, areaConstruida, valorAluguel, false, 1, null, "", 
             new ArrayList<>(), false, 0.0, null, true, false);
    }
    
    // Construtor para kitnet compartilhada
    public Kitnet(String endereco, double areaConstruida, double valorAluguel, 
                  int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                  String descricao) {
        this(endereco, areaConstruida, valorAluguel, true, numeroInquilinos, tipoCompartilhamento, 
             descricao, new ArrayList<>(), false, 0.0, null, true, false);
    }
    
    // Construtor com formas de caução
    public Kitnet(String endereco, double areaConstruida, double valorAluguel, 
                  List<Local.FormaCaucao> formasCaucao, boolean aceitaSeguro, 
                  double valorSeguro, Perfil responsavel) {
        this(endereco, areaConstruida, valorAluguel, false, 1, null, "", 
             formasCaucao, aceitaSeguro, valorSeguro, responsavel, true, false);
    }
    
    // Getters e Setters específicos da Kitnet
    public boolean isTemCozinha() {
        return temCozinha;
    }
    
    public void setTemCozinha(boolean temCozinha) {
        this.temCozinha = temCozinha;
    }
    
    public boolean isTemSacada() {
        return temSacada;
    }
    
    public void setTemSacada(boolean temSacada) {
        this.temSacada = temSacada;
    }
    
    // Método para calcular valor por metro quadrado
    public double getValorPorMetroQuadrado() {
        return getAreaConstruida() > 0 ? getValorAluguel() / getAreaConstruida() : 0;
    }
    
    @Override
    public String getTipoLocal() {
        return "Kitnet";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", area=").append(getAreaConstruida()).append("m²");
        
        if (temCozinha) sb.append(", cozinha");
        if (temSacada) sb.append(", sacada");
        
        return sb.toString();
    }
}
