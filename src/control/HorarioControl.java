package control;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.HorarioModel;
public class HorarioControl{
     
      Conexao con=new Conexao();
    public void CadastrarHorario(HorarioModel val){
        try{
            PreparedStatement prst=con.getCon().prepareStatement("INSERT INTO horario(nome) VALUES(?)");
            prst.setString(1, val.getDesc());
            prst.execute();
            prst.close();
        }catch(Exception e){
            e.getMessage();
            System.out.println("Erro/HorarioControl/CadastrarHorario: "+e);
        }
    }
    public boolean ConsultarHorario(String hora){
      ArrayList<HorarioModel> lista=new ArrayList<>();
        try{
          PreparedStatement prst=con.getCon().prepareStatement("SELECT * FROM horario WHERE nome=?");
          prst.setString(1, hora);
          prst.execute();
          ResultSet res=prst.executeQuery();
          if(res!=null){
              while(res.next()){
                  HorarioModel ho=new HorarioModel();
                  ho.setDesc(res.getString(hora));
                  lista.add(ho);
                  
              }
              return true;
          }else{
             lista=null;
             return false;
          }
      }catch(Exception e){
          e.getMessage();
          System.out.println("Error/HorarioControl/ConsultarHorario: "+e);
          return false;
      }
    }
    public void EditarHorario(HorarioModel hora){
        try{
         PreparedStatement prst=con.getCon().prepareStatement("UPDATE horario SET nome=? WHERE num=?");
         prst.setString(1, hora.getDesc());
         prst.setInt(2, hora.getNum());
         prst.execute();
         prst.close();
     }catch(Exception e){
         e.getMessage();
         System.out.println("Error/HorarioControl/EditarHorario: "+e);
         boolean retorno=false;
     }
        boolean retorno=true;
        
    }
    public void ApagarHorario(HorarioModel hora){
        try{
            PreparedStatement prst=con.getCon().prepareStatement("DELETE FROM horario WHERE num=?");
            prst.setInt(1,hora.getNum());
            prst.execute();
            prst.close();
        }catch(Exception e){
            e.getMessage();
            System.out.println("Erro/HorarioControl/ApagarHorario: "+e);
        }
    }
    public ArrayList<HorarioModel> ListarHoraios(){
            ArrayList<HorarioModel> lista=new ArrayList<>();
       try{
           PreparedStatement prst=con.getCon().prepareStatement("SELECT * FROM horario");
           ResultSet res=prst.executeQuery();
           if(res!=null){
                while(res.next()){
                    HorarioModel hora=new HorarioModel();
                    hora.setNum(res.getInt("num"));
                    hora.setDesc(res.getString("nome"));
                    lista.add(hora);
                }
           }else{
               lista=null;
           }
       }catch(Exception e){
           System.out.println("Error/HorarioControl/ListarHorarios: "+e.getMessage());
       }
        return lista;
    }
  
}
