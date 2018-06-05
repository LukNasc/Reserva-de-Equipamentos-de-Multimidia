/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author aluno
 */
public class ProfessorModel {

    /**
     * @return the cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
     public String getNome() {
        return nome;
    }

 
   
 
    private int cod;
    public String nome;
    public DisciplinaModel disc=new DisciplinaModel();
    public TurmaModel turma=new TurmaModel();
}
