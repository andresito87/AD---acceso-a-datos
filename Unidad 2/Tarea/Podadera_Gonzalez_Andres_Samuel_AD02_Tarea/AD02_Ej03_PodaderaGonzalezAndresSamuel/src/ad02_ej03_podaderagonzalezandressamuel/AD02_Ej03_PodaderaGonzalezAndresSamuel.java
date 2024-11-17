package ad02_ej03_podaderagonzalezandressamuel;

/**
 * Desarrolla un proyecto en NetBeans con nombre AD02_Ej03_ApellidoNombre que
 * establezca conexión con la BD y muestre la información obtenida, resultado de
 * ejecutar los siguientes procedimientos almacenados, por la salida estándar:
 *
 * P.A. 1: Un procedimiento almacenado que obtenga en un parámetro de salida, el
 * número total de guarderías que superan una cierta capacidad (numero máximo de
 * alumnos que puede acoger). Se deben pasar como parámetro al procedimiento la
 * capacidad que se tiene que superar.
 *
 * P.A. 2: Un procedimiento almacenado que devuelva en un parámetro de salida,
 * el número total de educadores infantiles, cuyo salario pertenece a un
 * determinado intervalo económico. Se deben pasar como parámetros al
 * procedimiento, tanto el valor inicial como el valor final de dicho intervalo.
 *
 * P.A. 3: Un procedimiento almacenado que aumente el salario de los educadores
 * infantiles de cierta guardería en un porcentaje. La guardería y valor entero
 * del porcentaje a aplicar se han de pasar como parámetros (de entrada).
 *
 * Debes gestionar las posibles excepciones y errores que puedan presentarse,
 * así como el cierre de recursos utilizados de forma adecuada si la aplicación
 * dejara de funcionar (usa la sentencia try-catch-finally o equivalente).
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author andres
 */
public class AD02_Ej03_PodaderaGonzalezAndresSamuel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // objeto que guarda la conexion
        Connection conexionDB = null;
        // parametros de la conexion
        String username = "root";
        String password = "admin";
        String nombreDB = "centroseducacioninfantil";

        try {
            // establece la conexion
            conexionDB = DriverManager
                    .getConnection("jdbc:mysql://localhost:3307/" + nombreDB, username, password);
            System.out.println("Conexion a la base de datos realizada correctamente");

            // ejecutar el primer procedimiento
            ejecutarPrimerProcedimiento(conexionDB);

            // ejecutar la segunda sentencia y mostrar el resultado
            ejecutarSegundoProcedimiento(conexionDB);

            // ejecutar la tercera sentencia y mostrar el resultado
            String codigoGuarderia = "ABC01";
            ejecutarTercerProcedimiento(conexionDB);

        } catch (SQLException ex) {
            System.out.println("Error de conexión a la base de datos");
        } finally {
            // si la conexion esta creada, la cerramos
            if (conexionDB != null) {
                try {
                    conexionDB.close();
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar la conexión a la base de datos");
                }
            }
        }
    }

    public static void ejecutarPrimerProcedimiento(Connection conexionDB) {
    }

    public static void ejecutarSegundoProcedimiento(Connection conexionDB) {
    }

    public static void ejecutarTercerProcedimiento(Connection conexionDB) {
    }

}
