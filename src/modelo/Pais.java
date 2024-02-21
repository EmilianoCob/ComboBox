package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Pais {
    //Atributos que tiene nuestra tabla Pais en la BD
    private int idPais;
    private String nombrePais;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    //Agregamos o modificamos el toString para que solo nos aparezca el nombre de los paises en el comboBox y no el Id, ese tiene que estar por "dentro" pero no debe verse
    public String toString() {
        return this.nombrePais;
    }

    //Hacemos un vector de Pais 
    public Vector<Pais> mostrarPaises() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        Vector<Pais> vectorPaises = new Vector<Pais>(); //Creamos el objeto de tipo vector
        Pais pais = null; //Creamos un objeto de Pais

        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();

            ps = conexion.prepareStatement("select * from paises"); 
            rs = ps.executeQuery();

            //Para que nos aparezca al inicio del comboBox el: seleccionar pais 
            pais = new Pais();
            pais.setIdPais(0);
            pais.setNombrePais("Seleccione pais");
            vectorPaises.add(pais);

            while (rs.next()) { //Agregamos todos los paises de la BD que vaya encontrando (los agregamos a nuestro vectorPaises)
                pais = new Pais();
                pais.setIdPais(rs.getInt("idPais")); //Obtenemos el idPais 
                pais.setNombrePais(rs.getString("nombrePais")); //Obtenemos el nombrePais
                vectorPaises.add(pais); //Se agregan al vector
            }

            rs.close();

        } catch (Exception e) {
            System.err.println("Error, " + e);
        }
        return vectorPaises; //Retornamos el vectorPaises donde estan todos mis paises de la BD
    }
}
