/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.ReservaModel;
import java.util.ArrayList;
/**
 *
 * @author aluno
 */
public class ReservaControl {
    AdmControl comp=new AdmControl();
    Conexao con=new Conexao();
    public void efetuarReserva(ReservaModel res){
        try{
            PreparedStatement prst=con.getCon().prepareStatement("INSERT INTO reserva(hora, equip, professor) VALUES(?,?,?);");
            prst.setInt(1, res.hora.getNum());
            prst.setInt(2, res.equip.getCod());
            prst.setInt(3, res.prof.getCod());
            prst.execute();
            prst.close();
        }catch(Exception e){
            System.out.println("Error/ReservaControl/eferuarReserva: "+e.getMessage());
        }
    }
    public ArrayList<ReservaModel> exibirReserva(){
         ArrayList<ReservaModel> lista = new ArrayList<>();
         try{
             PreparedStatement prst=con.getCon().prepareStatement("SELECT reserva.*, professores.nome, equipamentos.nome, horario.nome FROM reserva INNER JOIN professores ON professor=professores.cod INNER JOIN equipamentos ON reserva.equip=equipamentos.cod INNER JOIN horario ON reserva.hora=horario.num;");
             ResultSet res=prst.executeQuery();
             if(res!=null){
                 while(res.next()){
                     ReservaModel reserva=new ReservaModel();
                     reserva.setCod(res.getInt("cod"));
                     reserva.prof.setNome(res.getString("professores.nome"));
                     reserva.equip.setNome(res.getString("equipamentos.nome"));
                     reserva.hora.setDesc(res.getString("horario.nome"));
                     lista.add(reserva);
                 }
             }else{
                 lista=null;
             }
         }catch(Exception e){
             System.out.println("Error/ReservaControl/exibirReserva: "+e.getMessage());
         }
        return lista;
    }
    public void excluirReserva(int cod){
        try{
            PreparedStatement prst=con.getCon().prepareStatement("DELETE FROM reserva WHERE cod=?;");
            prst.setInt(1, cod);
            prst.execute();
            prst.close();
        }catch(Exception e){
            System.out.println("Error/ReservaControl/excluirReserva: "+e.getMessage());
        }
    }
}
