package perfil;

import java.util.ArrayList;

public class Perfil {
    ArrayList<Local> propriedades = new ArrayList<Local>();
    Local casa;
    String nome;
    Genero genero = new Genero();
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
        this.genero = PREFIRONAORESPONDER;
    }

    public alteraPerfil(String nome, String sobrenome, Local casa, int valGenero, String descricao){
        setNome(nome);
        setSobrenome(sobrenome);
        setCasa(casa);
        setGenero(valGenero);
        setDescricao(descricao);
    }

    public alteraConfiguracao(String senha, String login){
        this.senha = senha;
        this.login = login;
    }

    public adicionaContato(String contato){
        contato.add(contato);
    }

    public removerContato(int posicao){
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

    public Local getCasa(){
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

    public setNome(String nome){
        this.nome = nome;
    }

    public setSobrenome(String sobrenome){
        this.sobrenome = sobrenome;
    }

    public setGenero(int valGenero){
        switch (valGenero){
            case 0:
            genero = HOMEMCISGENERO;
            break;
            case 1:
            genero = MULHERCISGENERO;
            break;
            case 2:
            genero = HOMEMTRANSGENERO;
            break;
            case 3:
            genero = MULHERTRANSGENERO;
            break;
            case 4:
            genero = NAOBINARIO;
            break;
            case 5:
            genero = PREFIRONAORESPONDER;
            break;

        }
    }

    public setCasa(Local casa){
        this.casa = casa;
    }

    public setDescricao(String descricao){
        this.descricao_pessoal = descricao;
    }

    public setSenha(String senha){
        this.senha = senha;
    }

    public setLogin(String login){
        this.login = login;
    }
    
}