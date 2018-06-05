/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
/**
 *
 * @author aluno
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.EquipamentosModel;
public class EquipControl {
    AdmControl comp=new AdmControl();
    Conexao con=new Conexao();
    public void cadastrarEquipamento(EquipamentosModel equip){
         try{
             PreparedStatement prst = con.getCon().prepareStatement("INSERT INTO equipamentos(nome,marca,modelo,cor) VALUES(?,?,?,?)");
             prst.setString(1, equip.getNome());
             prst.setString(2, equip.getMarca());
             prst.setString(3, equip.getModelo());
             prst.setString(4, equip.getCor());
             prst.execute();
             prst.close();
         }catch(Exception e){
             System.out.println("Erro/EquipControl/cadastrarEquipmentos: "+e.getMessage());
         }
    }
    public EquipamentosModel ConsultarPorNomeEquip(String nome){
         EquipamentosModel equipamentos=null;
        try{
               PreparedStatement prst= con.getCon().prepareStatement("SELECT * FROM equipamentos WHERE nome=?;");
               prst.setString(1, nome);
               ResultSet res= prst.executeQuery();
              if(res!=null){
                  while(res.next()){
                   EquipamentosModel equip=new EquipamentosModel();
                   equip.setCod(res.getInt("cod"));
                   equip.setNome(res.getString("nome"));
                   equip.setMarca(res.getString("marca"));
                   equip.setModelo(res.getString("modelo"));
                    equipamentos=equip;
               }
              }else{
                  equipamentos=null;
              }
        }catch(Exception e){
        System.out.println("Erro/EquipControl/ConsultarPorNomeEquip: "+e);
    } 
        return equipamentos;
    }
    public void EditarEquipamento(EquipamentosModel equip){
        try{
            PreparedStatement prst=con.getCon().prepareStatement("UPDATE equipamentos SET nome=?, marca=?, modelo=?, cor=? WHERE cod=?");
            prst.setString(1, equip.getNome());
            prst.setString(2,equip.getMarca());
            prst.setString(3,equip.getModelo());
            prst.setString(4, equip.getCor());
            prst.setInt(5, equip.getCod());
            prst.execute();
            prst.close();
        }catch(Exception e){
            System.out.println("Erro/EquipControl/EditarEquipamento: "+e);
        }
    }
    public void ApagarEquipamento(EquipamentosModel equip){
        try{
            PreparedStatement prst=con.getCon().prepareStatement("DELETE FROM equipamentos WHERE cod=?");
            prst.setInt(1, equip.getCod());
            prst.execute();
            prst.close();
        }catch(Exception e){
            System.out.println("Erro/EquipControl/ApagarEquipamento:  "+e);
        }
    }
    public ArrayList<EquipamentosModel> ListarEquip(){
            ArrayList<EquipamentosModel> lista=new ArrayList<>();
        try{
            PreparedStatement prst=con.getCon().prepareStatement("SELECT * FROM equipamentos");
            ResultSet res = prst.executeQuery();
            if(res!= null){
                while(res.next()){
                    EquipamentosModel equip=new EquipamentosModel();
                     equip.setCod(res.getInt("cod"));
                    equip.setNome(res.getString("nome"));
                    equip.setMarca(res.getString("marca"));
                    equip.setModelo(res.getString("modelo"));
                    equip.setCor(res.getString("cor"));
                    lista.add(equip);
                }
            }else{
                lista = null;
            }
        }catch(Exception e){
            System.out.println("Erro/EquipControl/ListarEquip: "+e);
        }
        return lista;
    }
}
