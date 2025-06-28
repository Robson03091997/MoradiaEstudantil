package perfil;

import java.util.ArrayList;
import locais.Local;

public class Perfil {
    ArrayList<Local> propriedades = new ArrayList<Local>();
    Local casa;
    String nome;
    Genero genero; //acredito que não precisa/pode instanciar como método
    String sobrenome;
    String login;
    String senha;
    String descricao_pessoal = "";
    ArrayList<String> contato = new ArrayList<String>();

    public Perfil(String login, String senha, String nome, String sobrenome){
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.genero = Genero.PREFIRONAORESPONDER; //adicionar o "Genero." para selecionar a enumeração (feito em todas as vezes q aconteceu a msm coisa)
    }

    public void alteraPerfil(String nome, String sobrenome, Local casa, int valGenero, String descricao){
        setNome(nome);
        setSobrenome(sobrenome);
        setCasa(casa);
        setGenero(valGenero);
        setDescricao(descricao);
    }

    public void alteraConfiguracao(String senha, String login){
        this.senha = senha;
        this.login = login;
    }

    public void adicionaContato(String contato){
        contato.add(contato);
    }

    public void removerContato(int posicao){
        contato.remove(posicao);
    }

//------------------Get and Set Methods ---------------------------------------

    public String getNome(){
        return this.nome;
    }

    public String getSobrenome(){
        return this.sobrenome;
    }

    public Genero getGenero(){
        return this.genero;
    }

    public locais.Local getCasa(){
        return this.casa;
    }

    public String getDescricao(){
        return this.descricao_pessoal;
    }

    public String getSenha(){
        return this.senha;
    }

    public String getLogin(){
        return this.login;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome){
        this.sobrenome = sobrenome;
    }

    public void setGenero(int valGenero){
        switch (valGenero){
            case 0:
            genero = Genero.HOMEMCISGENERO;
            break;
            case 1:
            genero = Genero.MULHERCISGENERO;
            break;
            case 2:
            genero = Genero.HOMEMTRANSGENERO;
            break;
            case 3:
            genero = Genero.MULHERTRANSGENERO;
            break;
            case 4:
            genero = Genero.NAOBINARIO;
            break;
            case 5:
            genero = Genero.PREFIRONAORESPONDER;
            break;

        }
    }

    public void setCasa(Local casa){
        this.casa = casa;
    }

    public void setDescricao(String descricao){
        this.descricao_pessoal = descricao;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public void setLogin(String login){
        this.login = login;
    }
    
}