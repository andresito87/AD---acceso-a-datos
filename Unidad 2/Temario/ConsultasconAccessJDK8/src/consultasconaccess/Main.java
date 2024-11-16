/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package consultasconaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JMMN
 * Acceso a Access con el JDBC puro Multiplataforma UCANACCESS
 * http://ucanaccess.sourceforge.net/site.html
 * https://stackoverflow.com/questions/21955256/manipulating-an-access-database-from-java-without-odbc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Obtener la conexión
            Connection conn=DriverManager.getConnection("jdbc:ucanaccess://farmacia.mdb");
            
            // La clase Statement contiene los métodos executeQuery y
            //executeUpdate para realizar consultas y actualizaciones
            Statement stmt = conn.createStatement();
            
            //El método executeQuery devuelve un objeto ResultSet para poder
            // recorrer el resultado de la consulta utilizando un cursor.
            // Esta consulta obtiene todos los datos, todos los campos, )debido
            // al *), almacenados en la tabla medicamentos.
            ResultSet rs = stmt.executeQuery("SELECT * from medicamentos");
            
            // Mientras queden datos
            while (rs.next()) {
                    // Imprimir en la consola
                    String codigo = rs.getString("codigo");
                    String nombre = rs.getString("nombre");
                    String precio = rs.getString("precio");
                    String pvp = rs.getString("pvp");
                    String unidades = rs.getString("unidades");
                    System.out.println(codigo + "---" + nombre + "--" +precio+ "--" +pvp+ "--" +unidades);
            }

       } catch (SQLException e) {
            System.out.println("SQL Exception: "+ e.toString());
       } 
    }

}
