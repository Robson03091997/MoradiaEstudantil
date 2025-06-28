package locais;

import java.util.ArrayList;
import java.util.List;
import perfil.Perfil;

public class Quarto extends Local {
    
    // Atributos específicos do Quarto
    private boolean temBanheiroPrivativo;
    private boolean temArCondicionado;
    
    public Quarto(String endereco, double areaConstruida, 
                  double valorAluguel, boolean compartilhado, 
                  int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                  String descricao, List<Local.FormaCaucao> formasCaucao, boolean aceitaSeguro, 
                  double valorSeguro, Perfil responsavel, boolean temBanheiroPrivativo, 
                  boolean temArCondicionado) {
        super(endereco, 0, areaConstruida, numeroInquilinos, valorAluguel, compartilhado, 
              numeroInquilinos, tipoCompartilhamento, descricao, formasCaucao, aceitaSeguro, valorSeguro, responsavel);
        this.temBanheiroPrivativo = temBanheiroPrivativo;
        this.temArCondicionado = temArCondicionado;
    }
    
    // Construtor simplificado para quarto individual
    public Quarto(String endereco, double areaConstruida, double valorAluguel) {
        this(endereco, areaConstruida, valorAluguel, false, 1, null, "", 
             new ArrayList<>(), false, 0.0, null, false, false);
    }
    
    // Construtor para quarto compartilhado
    public Quarto(String endereco, double areaConstruida, double valorAluguel, 
                  int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                  String descricao) {
        this(endereco, areaConstruida, valorAluguel, true, numeroInquilinos, tipoCompartilhamento, 
             descricao, new ArrayList<>(), false, 0.0, null, false, false);
    }
    
    // Construtor com formas de caução
    public Quarto(String endereco, double areaConstruida, double valorAluguel, 
                  List<Local.FormaCaucao> formasCaucao, boolean aceitaSeguro, 
                  double valorSeguro, Perfil responsavel) {
        this(endereco, areaConstruida, valorAluguel, false, 1, null, "", 
             formasCaucao, aceitaSeguro, valorSeguro, responsavel, false, false);
    }
    
    // Getters e Setters específicos do Quarto
    public boolean isTemBanheiroPrivativo() {
        return temBanheiroPrivativo;
    }
    
    public void setTemBanheiroPrivativo(boolean temBanheiroPrivativo) {
        this.temBanheiroPrivativo = temBanheiroPrivativo;
    }
    
    public boolean isTemArCondicionado() {
        return temArCondicionado;
    }
    
    public void setTemArCondicionado(boolean temArCondicionado) {
        this.temArCondicionado = temArCondicionado;
    }
    
    // Método para calcular valor por metro quadrado
    public double getValorPorMetroQuadrado() {
        return getAreaConstruida() > 0 ? getValorAluguel() / getAreaConstruida() : 0;
    }
    
    @Override
    public String getTipoLocal() {
        return "Quarto";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", area=").append(getAreaConstruida()).append("m²");
        
        if (temBanheiroPrivativo) sb.append(", banheiro privativo");
        if (temArCondicionado) sb.append(", ar condicionado");
        
        return sb.toString();
    }
}
