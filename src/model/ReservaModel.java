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
public class ReservaModel {

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
     * @return the hora
     */

    /**
     * @return the turma
     */
    public String getTurma() {
        return turma;
    }

    /**
     * @param turma the turma to set
     */
    public void setTurma(String turma) {
        this.turma = turma;
    }

    /**
     * @return the equip
     */
 

    /**
     * @return the prof
     */

    private int cod;
    public HorarioModel hora=new HorarioModel();
    private String turma;
    public EquipamentosModel equip=new EquipamentosModel();
    public ProfessorModel prof=new ProfessorModel();
}
