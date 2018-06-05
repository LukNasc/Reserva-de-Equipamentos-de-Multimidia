/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.AdmModel;

/**
 *
 * @author aluno
 */

public class AdmControl{
    AdmModel admDepend=new AdmModel();
    Conexao con= new Conexao();
    public Boolean FazerLogin(String nome, String senha){
              Boolean ret = null;
        try{
           PreparedStatement prst = con.getCon().prepareStatement("SELECT professores.*, adm.* FROM adm INNER JOIN professores ON cod_prof=professores.cod WHERE adm.senha=? AND professores.nome=?");
           prst.setString(1,senha);
           prst.setString(2,nome);
           prst.execute();
           ResultSet res=prst.executeQuery();
           if(res.next()){
             ret=true; 
           }else{
             ret=false; 
           }
           res.close();
      }catch(Exception e){
          System.out.println("Erro/AdmControl/FazerLogin: "+e);
      }
      return ret;  
    }
}
