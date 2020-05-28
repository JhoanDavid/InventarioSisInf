
//package Controladores;

//import Clases.Prueba;
//import Conexion.ConexionMySQL;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;

//public class PruebaController {
  //  private ConexionMySQL conn;
    //private PreparedStatement PS;
    //private String query;
    
    //public PruebaController(){
//        PS=null;
  //      conn= new ConexionMySQL();
   // }
    
    //public boolean Login(String user, String pass){
      //  try {
        //    query="select * from prueba where email=? And password=?"; //los ? se reemplazan dandoles indices 1 y 2 depende de la cantidad de ? que haya
          //  PS=conn.getConexion().prepareStatement(query);
           // PS.setString(1, user);//reemplaza el primer ? con el usuario
           // PS.setString(2, pass);//reemplaza el segundo ? con el password
            //ResultSet resultado=PS.executeQuery(); //El result set es una espcie de lista de objetos con los resultados de la consulta
            //if(resultado.next()){ //si encontro registro en la lista de resultado
             //   PS.close();
               // return true;
            //}
        //} catch (SQLException ex) {
          //  Logger.getLogger(PruebaController.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //return false;
    //}
    
    
    
     //public boolean CrearPrueba(String nombre, String email, String password, String Estado){
        
      //      query="INSERT INTO prueba(`nombre`, `email`, `password`, `Estado`) VALUES (?,?,?,?)"; //los ? se reemplazan dandoles indices 1 y 2 depende de la cantidad de ? que haya
       // try {
        //    PS=conn.getConexion().prepareStatement(query);
        //    PS.setString(1, nombre);
        //    PS.setString(2, email);
        //    PS.setString(3, password);
        //    PS.setString(4, Estado);
        //    int resultado=PS.executeUpdate(); //el executeupdate devuelve el numero de filas creadas o editadas
        //    if(resultado>0){
        //        PS.close();
        //        return true;
        //    }else{
        //        PS.close();
        //        return false;
        //    }
        //} catch (SQLException ex) {
        //    Logger.getLogger(PruebaController.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //return false;
    //}
     
     
     
     
    //  public boolean ActualizarPrueba(String nombre, String email, String password, String Estado, int id){
       
     //   query="UPDATE prueba SET nombre=?,email=?,password=?,Estado=? WHERE  id=?";
      //  try {
       //     PS=conn.getConexion().prepareStatement(query);
        //    PS.setString(1, nombre);
         //   PS.setString(2, email);
          //  PS.setString(3, password);
           // PS.setString(4, Estado);
            //PS.setInt(5, id);
         //   int resultado=PS.executeUpdate();
          //  if(resultado>0){
           //     PS.close();
            //    return true;
        //    }else{
//                PS.close();
  //              return false;
    //        }
      //  } catch (SQLException ex) {
       //     Logger.getLogger(PruebaController.class.getName()).log(Level.SEVERE, null, ex);
        //}
//        return false;
  //  }
     
      
      
      
    //   public boolean InactivarPrueba(int id){    //la idea de este metodo es editar el estado y no eliminar el registro
      //  query="UPDATE prueba SET Estado='Inactivo' WHERE  id=?";
        //try {
          //  PS=conn.getConexion().prepareStatement(query);
            //PS.setInt(1, id);
//            int resultado=PS.executeUpdate();
  //          if(resultado>0){
    //            PS.close();
      //          return true;
        //    }else{
          //      PS.close();
            //    return false;
            //}
//        } catch (SQLException ex) {
  //          Logger.getLogger(PruebaController.class.getName()).log(Level.SEVERE, null, ex);
    //    }
      //  return false;
    //}
       
//        public ArrayList<Prueba> VerListaDePruebas(){
  //      try {
    //        ArrayList<Prueba> listaPruebas = new ArrayList<Prueba>();
      //      query="select * from prueba"; 
        //    PS=conn.getConexion().prepareStatement(query);
          //  ResultSet resultado=PS.executeQuery(); //el execute ejecuta el query pero solo devuelve verdadero o falso
            //while(resultado.next()){
              //  Prueba p=new Prueba();
                //p.setId(resultado.getInt("id"));
//                p.setNombre(resultado.getString("nombre"));
  //              p.setEmail(resultado.getString("email"));
    //            p.setPassword(resultado.getString("password"));
      //          p.setEstado(resultado.getString("estado"));
        //        listaPruebas.add(p);
          //  }
            //PS.close();
//            return listaPruebas;
  //      } catch (SQLException ex) {
    //        Logger.getLogger(PruebaController.class.getName()).log(Level.SEVERE, null, ex);
      //  }
        //return null;
//        }
        
        
        
//        public Prueba VerPrueba(int id){
  //          try {
//
  //          query="SELECT * From prueba WHERE id=?"; 
    //        PS=conn.getConexion().prepareStatement(query);
      //      PS.setInt(1, id);
        //    ResultSet resultado=PS.executeQuery(); //el execute ejecuta el query pero solo devuelve verdadero o falso
          //  if(resultado.next()){
            //    PS.close();
              //  Prueba p=new Prueba();
                //p.setId(resultado.getInt("id"));
//                p.setNombre(resultado.getString("nombre"));
  //              p.setEmail(resultado.getString("email"));
    //            p.setPassword(resultado.getString("password"));
      //          p.setEstado(resultado.getString("estado"));
        //        return p;
          //  }else{
            //    return null;
//            }
            
//        } catch (SQLException ex) {
  //          Logger.getLogger(PruebaController.class.getName()).log(Level.SEVERE, null, ex);
    //    }
      //  return null;
        //}
//}
