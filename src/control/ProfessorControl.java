/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DisciplinaModel;
import model.ProfessorModel;
import model.TurmaModel;

/**
 *
 * @author aluno
 */
public class ProfessorControl {

    Conexao con=new Conexao();
    public void CadastrarProfessor(ProfessorModel prof, String senha){
        try{
            PreparedStatement prst=con.getCon().prepareStatement("INSERT INTO professores(nome, disciplina, turma) VALUES(?,?,?)");
            prst.setString(1, prof.nome);
            prst.setString(2, prof.disc.getNome());
            prst.setString(3, prof.turma.getNome());
            prst.execute();
            prst.close();
            PreparedStatement cod=con.getCon().prepareStatement("SELECT * FROM professores WHERE nome=? AND disciplina=?; ");
            cod.setString(1, prof.nome);
            cod.setString(2, prof.disc.getNome());
            ResultSet res = cod.executeQuery();
            res.next();
           if(senha.equals("NULL")){
               
           }else{
                PreparedStatement adm=con.getCon().prepareStatement("INSERT INTO adm(cod_prof,senha) VALUES(?,?)");
                adm.setInt(1, res.getInt("cod"));
                adm.setString(2, senha);
                adm.execute();
                adm.close();
           }
        }catch(Exception e){
            e.getMessage();
            System.out.println("Error/ProfessoresControl/CadastrarProfessor: "+e);
        }
    }
    public ProfessorModel ConsultarPorNomeProfessor(String nome){
        ProfessorModel prof=null;
        try{
            PreparedStatement prst=con.getCon().prepareStatement("SELECT * FROM professores WHERE nome=?");
            prst.setString(1, nome);
            ResultSet res=prst.executeQuery();
            if(res!=null){
                while(res.next()){
                    ProfessorModel pr=new ProfessorModel();
                    pr.setCod(res.getInt("cod"));
                    pr.setNome(res.getString("nome"));
                    pr.disc.setNome(res.getString("disciplinas"));
                    pr.turma.setNome(res.getString("turma"));
                }
            }
        }catch(Exception e){
            System.out.println("Erro/ProfessoresControl/ConsultarPorNomeProfessor "+e.getMessage());
        }
        return prof;
    }
    public void EditarProfessor(ProfessorModel prof){
        try{
            PreparedStatement prst=con.getCon().prepareStatement("UPDATE professores SET nome=?, disciplina=?, turma=? WHERE cod=?");
            prst.setString(1, prof.nome);
            prst.setString(2, prof.disc.getNome());
            prst.setString(3, prof.turma.getNome());
            prst.setInt(4, prof.getCod());
            prst.execute();
            prst.close();
        }catch(Exception e){
            System.out.println("Erro/ProfessoresControl/EditarProfessor: "+e.getMessage());
        }
    }
    public void ApagarProfessor(ProfessorModel prof){
        try{
            PreparedStatement adm=con.getCon().prepareStatement("DELETE FROM adm WHERE cod_prof=?");
            adm.setInt(1, prof.getCod());
            adm.execute();
            adm.close();
            PreparedStatement prst=con.getCon().prepareStatement("DELETE FROM professores WHERE cod=?");
            prst.setInt(1, prof.getCod());
            prst.execute();
            prst.close();
        }catch(Exception e){
            System.out.println("Error/ProfessoresControl/ApagarProfessor: "+e.getMessage());
        }
    }
    public ArrayList<ProfessorModel> ListarProfessor(){
            ArrayList<ProfessorModel> lista=new ArrayList<>();
       try{
           PreparedStatement prst=con.getCon().prepareStatement("SELECT * FROM professores");
           ResultSet res=prst.executeQuery();
           if(res!=null){
                while(res.next()){
                    ProfessorModel prof=new ProfessorModel();
                    prof.setCod(res.getInt("cod"));
                    prof.setNome(res.getString("nome"));
                    prof.disc.setNome(res.getString("disciplina"));
                    prof.turma.setNome(res.getString("turma"));
                    lista.add(prof);
                }
           }else{
               lista=null;
           }
       }catch(Exception e){
           System.out.println("Error/ProfessoresControl/ListaProfessor: "+e.getMessage());
       }
        return lista;
    }
}
