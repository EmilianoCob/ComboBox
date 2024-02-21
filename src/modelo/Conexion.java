
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    //Necesitamos hacer la conexion con la BD, utilizamos el codigo para hacer la conexion
    public static final String URL = "jdbc:mysql://localhost:3306/datosPaises"; 
    public static final String usuario = "root";
    public static final String contraseña = "directe089";
    
    public Connection getConnection(){
        Connection conexion=null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL,usuario,contraseña);
            //JOptionPane.showMessageDialog(null, "Conexion exitosa");
            
        }catch(Exception ex){
            System.err.println("Error, "+ex);
        }
        
        return conexion;
    }
    
    
}
