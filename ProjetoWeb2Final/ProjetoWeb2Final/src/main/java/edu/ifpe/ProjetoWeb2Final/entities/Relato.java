package edu.ifpe.ProjetoWeb2Final.entities;

import java.util.Date;

public class Relato {
    private int id;                 
    private Date dataRelato;        
    private int idFuncionario;       
    private int idProblema;       
    private String problemaTipo;    
    private String funcionarioNome;  

   
    public Relato(int id, Date dataRelato, int idFuncionario, int idProblema, String problemaTipo, String funcionarioNome) {
        this.id = id;
        this.dataRelato = dataRelato;
        this.idFuncionario = idFuncionario;
        this.idProblema = idProblema;
        this.problemaTipo = problemaTipo;
        this.funcionarioNome = funcionarioNome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataRelato() {
        return dataRelato;
    }

    public void setDataRelato(Date dataRelato) {
        this.dataRelato = dataRelato;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public String getProblemaTipo() {
        return problemaTipo;
    }

    public void setProblemaTipo(String problemaTipo) {
        this.problemaTipo = problemaTipo;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String funcionarioNome) {
        this.funcionarioNome = funcionarioNome;
    }

}
