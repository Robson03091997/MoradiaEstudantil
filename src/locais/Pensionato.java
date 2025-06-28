package locais;

import java.util.List;
import java.util.ArrayList;
import perfil.Perfil;

public class Pensionato extends Casa {
    
    // Atributos específicos do Pensionato
    private boolean temRefeicoesInclusas;
    private boolean temLimpezaInclusa;
    private boolean temHorarioFechamento;
    private boolean temRegrasRigorosas;
    
    public Pensionato(String endereco, int numeroBanheiros, double areaConstruida, 
                      int numeroMaximoInquilinos, double valorAluguel, int numeroQuartos, 
                      int vagasGaragem, boolean temRefeicoesInclusas, boolean temLimpezaInclusa, 
                      boolean temHorarioFechamento, boolean temRegrasRigorosas) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
              numeroQuartos, vagasGaragem);
        this.temRefeicoesInclusas = temRefeicoesInclusas;
        this.temLimpezaInclusa = temLimpezaInclusa;
        this.temHorarioFechamento = temHorarioFechamento;
        this.temRegrasRigorosas = temRegrasRigorosas;
    }
    
    // Construtor simplificado para pensionato
    public Pensionato(String endereco, int numeroBanheiros, double areaConstruida, 
                      int numeroMaximoInquilinos, double valorAluguel, int numeroQuartos) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
             numeroQuartos, 0);
        this.temRefeicoesInclusas = true;
        this.temLimpezaInclusa = true;
        this.temHorarioFechamento = true;
        this.temRegrasRigorosas = true;
    }
    
    // Construtor com formas de caução
    public Pensionato(String endereco, int numeroBanheiros, double areaConstruida, 
                      int numeroMaximoInquilinos, double valorAluguel, List<Local.FormaCaucao> formasCaucao,
                      boolean aceitaSeguro, double valorSeguro, Perfil responsavel, int numeroQuartos, 
                      int vagasGaragem) {
        super(endereco, numeroBanheiros, areaConstruida, numeroMaximoInquilinos, valorAluguel, 
              formasCaucao, aceitaSeguro, valorSeguro, responsavel, numeroQuartos, vagasGaragem);
        this.temRefeicoesInclusas = true;
        this.temLimpezaInclusa = true;
        this.temHorarioFechamento = true;
        this.temRegrasRigorosas = true;
    }
    
    // Getters e Setters específicos do Pensionato
    public boolean isTemRefeicoesInclusas() {
        return temRefeicoesInclusas;
    }
    
    public void setTemRefeicoesInclusas(boolean temRefeicoesInclusas) {
        this.temRefeicoesInclusas = temRefeicoesInclusas;
    }
    
    public boolean isTemLimpezaInclusa() {
        return temLimpezaInclusa;
    }
    
    public void setTemLimpezaInclusa(boolean temLimpezaInclusa) {
        this.temLimpezaInclusa = temLimpezaInclusa;
    }
    
    public boolean isTemHorarioFechamento() {
        return temHorarioFechamento;
    }
    
    public void setTemHorarioFechamento(boolean temHorarioFechamento) {
        this.temHorarioFechamento = temHorarioFechamento;
    }
    
    public boolean isTemRegrasRigorosas() {
        return temRegrasRigorosas;
    }
    
    public void setTemRegrasRigorosas(boolean temRegrasRigorosas) {
        this.temRegrasRigorosas = temRegrasRigorosas;
    }
    
    // Método para calcular valor por quarto (incluindo serviços)
    public double getValorPorQuartoComServicos() {
        double valorBase = getValorPorQuarto();
        if (temRefeicoesInclusas) valorBase += 200.0; // Valor estimado das refeições
        if (temLimpezaInclusa) valorBase += 100.0;    // Valor estimado da limpeza
        return valorBase;
    }
    
    @Override
    public String getTipoLocal() {
        return "Pensionato";
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        
        if (temRefeicoesInclusas) sb.append(", refeições inclusas");
        if (temLimpezaInclusa) sb.append(", limpeza inclusa");
        if (temHorarioFechamento) sb.append(", horário de fechamento");
        if (temRegrasRigorosas) sb.append(", regras rigorosas");
        
        return sb.toString();
    }
} 