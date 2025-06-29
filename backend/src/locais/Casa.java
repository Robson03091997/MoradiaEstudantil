package locais;

import java.util.ArrayList;
import java.util.List;
import perfil.Perfil;

public class Casa extends Local {
    
    // Atributos específicos da Casa
    private int numeroQuartos;
    private int vagasGaragem;
    
    public Casa(String endereco, int numeroBanheiros, double areaConstruida, 
                int numeroMaximoInquilinos, double valorAluguel, boolean compartilhado, 
                int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                String descricao, List<Local.FormaCaucao> formasCaucao, boolean aceitaSeguro, 
                double valorSeguro, Perfil responsavel, int numeroQuartos, int vagasGaragem) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, 
              valorAluguel, compartilhado, numeroInquilinos, tipoCompartilhamento, descricao,
              formasCaucao, aceitaSeguro, valorSeguro, responsavel);
        this.numeroQuartos = numeroQuartos;
        this.vagasGaragem = vagasGaragem;
    }
    
    // Construtor simplificado para casa não compartilhada
    public Casa(String endereco, int numeroBanheiros, double areaConstruida, 
                int numeroMaximoInquilinos, double valorAluguel, int numeroQuartos, 
                int vagasGaragem) {
        this(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, 
             valorAluguel, false, 1, null, "", new ArrayList<>(), false, 0.0, null,
             numeroQuartos, vagasGaragem);
    }
    
    // Construtor para casa compartilhada
    public Casa(String endereco, int numeroBanheiros, double areaConstruida, 
                double valorAluguel, int numeroInquilinos, Local.TipoCompartilhamento tipoCompartilhamento, 
                String descricao, int numeroQuartos, int vagasGaragem) {
        this(endereco, numeroBanheiros, areaConstruida, numeroInquilinos, 
             valorAluguel, true, numeroInquilinos, tipoCompartilhamento, descricao,
             new ArrayList<>(), false, 0.0, null, numeroQuartos, vagasGaragem);
    }
    
    // Construtor com formas de caução
    public Casa(String endereco, int numeroBanheiros, double areaConstruida, 
                int numeroMaximoInquilinos, double valorAluguel, List<Local.FormaCaucao> formasCaucao,
                boolean aceitaSeguro, double valorSeguro, Perfil responsavel, int numeroQuartos, 
                int vagasGaragem) {
        this(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, 
             valorAluguel, false, 1, null, "", formasCaucao, aceitaSeguro, valorSeguro, responsavel,
             numeroQuartos, vagasGaragem);
    }
    
    // Getters e Setters específicos da Casa
    public int getNumeroQuartos() {
        return numeroQuartos;
    }
    
    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }
    
    public int getVagasGaragem() {
        return vagasGaragem;
    }
    
    public void setVagasGaragem(int vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }
    
    // Método para calcular valor por quarto
    public double getValorPorQuarto() {
        return numeroQuartos > 0 ? getValorAluguel() / numeroQuartos : 0;
    }
    
    @Override
    public String getTipoLocal() {
        return "Casa";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", quartos=").append(numeroQuartos);
        sb.append(", garagem=").append(vagasGaragem).append(" vagas");
        return sb.toString();
    }
}
