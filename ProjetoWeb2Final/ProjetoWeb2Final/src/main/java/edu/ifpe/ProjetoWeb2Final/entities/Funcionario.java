package edu.ifpe.ProjetoWeb2Final.entities;

public class Funcionario {
    private int id;
    private String nome;
    private String registro;
    private int idSetor;

    public Funcionario() {}

    public Funcionario(int id, String nome, String registro, int idSetor) {
        this.id = id;
        this.nome = nome;
        this.registro = registro;
        this.idSetor = idSetor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }
    

}
