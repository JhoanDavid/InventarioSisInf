package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexionMySQL {
    
private static Connection conn;
private static String driver="com.mysql.jdbc.driver";
private static String user="root";
private static String password="";
private static String url="jdbc:mysql://localhost:3306/inventariosisinf";

public ConexionMySQL(){     
    conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,password);
            System.out.println("Se conectó a la base de datos éxitosamente");
        } catch (Exception e) {
            System.out.println("error al conectar la base de datos: " + e);
        }
    }

public Connection getConexion(){
      return conn;
        };

}
