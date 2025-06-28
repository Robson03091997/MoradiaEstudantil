package locais;

import java.util.ArrayList;
import java.util.List;
import perfil.Perfil;

public abstract class Local {
    private String endereco;
    private List<Perfil> proprietarios;
    private List<Perfil> moradores;
    private String id;
    private static int contadorId = 1;
    
    // Atributos comuns para todos os tipos de locais
    private int numeroBanheiros;
    private double areaConstruida; // em metros quadrados
    private int numeroMaximoInquilinos;
    private boolean disponivel;
    private double valorAluguel;
    
    // Atributos de compartilhamento
    private boolean compartilhado;
    private int numeroInquilinos;
    private TipoCompartilhamento tipoCompartilhamento;
    private String descricao;
    
    // Atributos de pagamento e responsável
    private List<FormaCaucao> formasCaucao;
    private boolean aceitaSeguro;
    private double valorSeguro;
    private Perfil responsavel;
    
    // Enum para tipo de compartilhamento
    public enum TipoCompartilhamento {
        MASCULINO("Masculino"),
        FEMININO("Feminino"),
        MISTO("Misto");
        
        private final String descricao;
        
        TipoCompartilhamento(String descricao) {
            this.descricao = descricao;
        }
        
        @Override
        public String toString() {
            return descricao;
        }
    }
    
    // Enum para formas de caução //não deveria ser um arquivo separado???
    public enum FormaCaucao {
        FIADOR("Fiador"),
        DEPOSITO("Depósito"),
        SEGURO("Seguro"),
        CALCAO("Caução"),
        SEM_CAUCAO("Sem caução");
        
        private final String descricao;
        
        FormaCaucao(String descricao) {
            this.descricao = descricao;
        }
        
        @Override
        public String toString() {
            return descricao;
        }
    }
    
    public Local(String endereco, int numeroBanheiros, double areaConstruida, 
                int numeroMaximoInquilinos, double valorAluguel, boolean compartilhado, 
                int numeroInquilinos, TipoCompartilhamento tipoCompartilhamento, String descricao,
                List<FormaCaucao> formasCaucao, boolean aceitaSeguro, double valorSeguro, Perfil responsavel) {
        this.endereco = endereco;
        this.numeroBanheiros = numeroBanheiros;
        this.areaConstruida = areaConstruida;
        this.numeroMaximoInquilinos = numeroMaximoInquilinos;
        this.valorAluguel = valorAluguel;
        this.compartilhado = compartilhado;
        this.numeroInquilinos = numeroInquilinos;
        this.tipoCompartilhamento = tipoCompartilhamento;
        this.descricao = descricao;
        this.formasCaucao = formasCaucao != null ? new ArrayList<>(formasCaucao) : new ArrayList<>();
        this.aceitaSeguro = aceitaSeguro;
        this.valorSeguro = valorSeguro;
        this.responsavel = responsavel;
        this.disponivel = true;
        this.proprietarios = new ArrayList<>();
        this.moradores = new ArrayList<>();
        this.id = "LOCAL_" + contadorId++;
    }
    
    // Getters e Setters básicos
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public int getNumeroBanheiros() {
        return numeroBanheiros;
    }
    
    public void setNumeroBanheiros(int numeroBanheiros) {
        this.numeroBanheiros = numeroBanheiros;
    }
    
    public double getAreaConstruida() {
        return areaConstruida;
    }
    
    public void setAreaConstruida(double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }
    
    public int getNumeroMaximoInquilinos() {
        return numeroMaximoInquilinos;
    }
    
    public void setNumeroMaximoInquilinos(int numeroMaximoInquilinos) {
        this.numeroMaximoInquilinos = numeroMaximoInquilinos;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    public double getValorAluguel() {
        return valorAluguel;
    }
    
    public void setValorAluguel(double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }
    
    // Getters e Setters para compartilhamento
    public boolean isCompartilhado() {
        return compartilhado;
    }
    
    public void setCompartilhado(boolean compartilhado) {
        this.compartilhado = compartilhado;
    }
    
    public int getNumeroInquilinos() {
        return numeroInquilinos;
    }
    
    public void setNumeroInquilinos(int numeroInquilinos) {
        this.numeroInquilinos = numeroInquilinos;
    }
    
    public TipoCompartilhamento getTipoCompartilhamento() {
        return tipoCompartilhamento;
    }
    
    public void setTipoCompartilhamento(TipoCompartilhamento tipoCompartilhamento) {
        this.tipoCompartilhamento = tipoCompartilhamento;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    // Getters e Setters para formas de caução e responsável
    public List<FormaCaucao> getFormasCaucao() {
        return new ArrayList<>(formasCaucao);
    }
    
    public void setFormasCaucao(List<FormaCaucao> formasCaucao) {
        this.formasCaucao = new ArrayList<>(formasCaucao);
    }
    
    public void adicionarFormaCaucao(FormaCaucao formaCaucao) {
        if (formaCaucao != null && !formasCaucao.contains(formaCaucao)) {
            formasCaucao.add(formaCaucao);
        }
    }
    
    public void removerFormaCaucao(FormaCaucao formaCaucao) {
        formasCaucao.remove(formaCaucao);
    }
    
    public boolean isAceitaSeguro() {
        return aceitaSeguro;
    }
    
    public void setAceitaSeguro(boolean aceitaSeguro) {
        this.aceitaSeguro = aceitaSeguro;
    }
    
    public double getValorSeguro() {
        return valorSeguro;
    }
    
    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
    
    public Perfil getResponsavel() {
        return responsavel;
    }
    
    public void setResponsavel(Perfil responsavel) {
        this.responsavel = responsavel;
    }
    
    public List<Perfil> getProprietarios() {
        return new ArrayList<>(proprietarios);
    }
    
    public void adicionarProprietario(Perfil proprietario) {
        if (proprietario != null && !proprietarios.contains(proprietario)) {
            proprietarios.add(proprietario);
        }
    }
    
    public void removerProprietario(Perfil proprietario) {
        proprietarios.remove(proprietario);
    }
    
    public List<Perfil> getMoradores() {
        return new ArrayList<>(moradores);
    }
    
    public void adicionarMorador(Perfil morador) {
        if (morador != null && !moradores.contains(morador) && moradores.size() < numeroMaximoInquilinos) {
            moradores.add(morador);
            if (moradores.size() >= numeroMaximoInquilinos) {
                disponivel = false;
            }
        }
    }
    
    public void removerMorador(Perfil morador) {
        moradores.remove(morador);
        if (moradores.size() < numeroMaximoInquilinos) {
            disponivel = true;
        }
    }
    
    public String getId() {
        return id;
    }
    
    // Método para verificar se ainda há vagas disponíveis
    public boolean temVagasDisponiveis() {
        return moradores.size() < numeroMaximoInquilinos;
    }
    
    // Método para obter número de vagas disponíveis
    public int getVagasDisponiveis() {
        return Math.max(0, numeroMaximoInquilinos - moradores.size());
    }
    
    // Método para obter valor por pessoa (em caso de compartilhamento)
    public double getValorPorPessoa() {
        if (compartilhado && numeroInquilinos > 0) {
            return valorAluguel / numeroInquilinos;
        }
        return valorAluguel;
    }
    
    // Método para verificar se aceita uma forma específica de caução
    public boolean aceitaFormaCaucao(FormaCaucao formaCaucao) {
        return formasCaucao.contains(formaCaucao);
    }
    
    // Método para obter valor total inicial (aluguel + seguro + caução)
    public double getValorTotalInicial() {
        double total = valorAluguel;
        if (aceitaSeguro) {
            total += valorSeguro;
        }
        // Adicionar valor de caução se houver
        if (formasCaucao.contains(FormaCaucao.DEPOSITO) || formasCaucao.contains(FormaCaucao.CALCAO)) {
            total += valorAluguel; // Geralmente a caução é equivalente a um mês de aluguel
        }
        return total;
    }
    
    // Método abstrato para obter o tipo do local
    public abstract String getTipoLocal();
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Local{");
        sb.append("id='").append(id).append('\'');
        sb.append(", tipo='").append(getTipoLocal()).append('\'');
        sb.append(", endereco='").append(endereco).append('\'');
        sb.append(", banheiros=").append(numeroBanheiros);
        sb.append(", area=").append(areaConstruida).append("m²");
        sb.append(", maxInquilinos=").append(numeroMaximoInquilinos);
        sb.append(", vagasDisponiveis=").append(getVagasDisponiveis());
        sb.append(", valorAluguel=R$").append(String.format("%.2f", valorAluguel));
        
        if (compartilhado) {
            sb.append(", compartilhado=").append(compartilhado);
            sb.append(", numeroInquilinos=").append(numeroInquilinos);
            sb.append(", tipo=").append(tipoCompartilhamento);
            sb.append(", valorPorPessoa=R$").append(String.format("%.2f", getValorPorPessoa()));
        }
        
        if (descricao != null && !descricao.isEmpty()) {
            sb.append(", descricao='").append(descricao).append('\'');
        }
        
        if (!formasCaucao.isEmpty()) {
            sb.append(", formasCaucao=").append(formasCaucao);
        }
        
        if (aceitaSeguro) {
            sb.append(", seguro=R$").append(String.format("%.2f", valorSeguro));
        }
        
        if (responsavel != null) {
            sb.append(", responsavel='").append(responsavel.getNome()).append('\'');
        }
        
        sb.append(", proprietarios=").append(proprietarios.size());
        sb.append(", moradores=").append(moradores.size());
        sb.append('}');
        
        return sb.toString();
    }
}
