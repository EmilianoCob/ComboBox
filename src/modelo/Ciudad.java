package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Ciudad {

    private int idCiudad;
    private String nombreCiudad;

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }


    public String toString() {
        return this.nombreCiudad;
    }

     
    public Vector<Ciudad> mostrarCiudades(Integer idEstado) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        Vector<Ciudad> vectorCiudades = new Vector<Ciudad>(); 
        Ciudad ciudad = null; 

        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();

            ps = conexion.prepareStatement("select * from ciudades where idEstado="+idEstado); 
            rs = ps.executeQuery();

             
            ciudad = new Ciudad();
            ciudad.setIdCiudad(0);
            ciudad.setNombreCiudad("Seleccione ciudad");
            vectorCiudades.add(ciudad);

            while (rs.next()) { 
                ciudad = new Ciudad();
                ciudad.setIdCiudad(rs.getInt("idciudad")); 
                ciudad.setNombreCiudad(rs.getString("nombreCiudad")); 
                vectorCiudades.add(ciudad); 
            }

            rs.close();

        } catch (Exception e) {
            System.err.println("Error, " + e);
        }
        return vectorCiudades; 
    }
}
