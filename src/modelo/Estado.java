package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Estado {
    //Atributos que tiene nuestra tabla Pais en la BD
    private int idEstado;
    private String nombreEstado;

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String toString() {
        return this.nombreEstado;
    }

    //Hacemos un vector de Estado 
    public Vector<Estado> mostrarEstados(Integer idPais) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        Vector<Estado> vectorEstados = new Vector<Estado>(); 
        Estado estado = null; 

        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();

            ps = conexion.prepareStatement("select * from estados where idpais="+idPais); 
            rs = ps.executeQuery();

            estado = new Estado();
            estado.setIdEstado(0);
            estado.setNombreEstado("Seleccione estado");
            vectorEstados.add(estado);

            while (rs.next()) { 
                estado = new Estado();
                estado.setIdEstado(rs.getInt("idestado"));  
                estado.setNombreEstado(rs.getString("nombreEstado")); 
                vectorEstados.add(estado); //Se agregan al vector
            }

            rs.close();

        } catch (Exception e) {
            System.err.println("Error, " + e);
        }
        return vectorEstados; 
    }
}
