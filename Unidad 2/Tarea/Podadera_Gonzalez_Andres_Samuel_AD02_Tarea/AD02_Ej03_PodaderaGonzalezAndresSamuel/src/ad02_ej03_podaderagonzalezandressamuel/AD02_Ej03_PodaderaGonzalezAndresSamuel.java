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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * Clase principal del proyecto AD02_Ej03_PodaderaGonzalezAndresSamuel.
 *
 * Esta clase conecta a una base de datos MySQL y ejecuta tres procedimientos
 * almacenados:
 *
 * <ul>
 * <li>Procedimiento 1. Obtener la cantidad de guarderías que superan un límite
 * de capacidad.</li>
 * <li>Procedimiento 2. Obtener la cantidad de educadores con un salario dentro
 * de un intervalo específico.</li>
 * <li>Procedimiento 3. Incrementar el salario de los educadores de una
 * guardería en un porcentaje dado.</li>
 * </ul>
 *
 * Se gestionan posibles excepciones y errores, y se asegura el cierre adecuado
 * de los recursos.
 *
 * @author ANDRES SAMUEL PODADERA GONZALEZ
 * <p>
 * Desarrollo de aplicaciones multiplataforma - Acceso a datos - 2024/2025
 * </p>
 */
public class AD02_Ej03_PodaderaGonzalezAndresSamuel {

    /**
     * Método principal del programa.
     *
     * Conecta a la base de datos y ejecuta los tres procedimientos almacenados
     * con parámetros de prueba. Maneja errores de conexión y asegura el cierre
     * de la conexión.
     *
     * @param args Argumentos de línea de comandos. No utilizados en este
     * programa.
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
            //System.out.println("**Conexion a la base de datos realizada correctamente**");

            // ejecutar el primer procedimiento
            int capacidadMaxima = 50;
            ejecutarPrimerProcedimiento(conexionDB, capacidadMaxima);

            // ejecutar el segundo procedimiento
            int minIntervalo = 36000;
            int maxIntervalo = 42000;
            ejecutarSegundoProcedimiento(conexionDB, minIntervalo, maxIntervalo);

            // ejecutar el tercer procedimiento
            String codigoGuarderia = "CDE03";
            int porcentajeAumento = 15;
            ejecutarTercerProcedimiento(conexionDB, codigoGuarderia, porcentajeAumento);

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

    /**
     * Ejecuta el procedimiento almacenado Guarderias_Con_Maxima_Capacidad.
     *
     * Este procedimiento obtiene la cantidad de guarderías que superan un
     * límite de capacidad dado y muestra el resultado en la consola.
     *
     * @param conexionDB Conexión a la base de datos.
     * @param capacidadMaxima Límite de capacidad a superar.
     */
    public static void ejecutarPrimerProcedimiento(Connection conexionDB, int capacidadMaxima) {

        CallableStatement procedimiento = null;

        try {
            // prepara la llamada al procedimiento almacenado
            procedimiento = conexionDB.prepareCall("{CALL Guarderias_Con_Maxima_Capacidad(?, ?)}");

            // registrar el parámetro de entrada y el de salida
            procedimiento.setInt(1, capacidadMaxima); // parametro de entrada
            procedimiento.registerOutParameter(2, Types.INTEGER); // parametro de salida

            // ejecutar el procedimiento
            procedimiento.execute();

            // recuperar el valor del parámetro de salida, posicion 2
            int cantidadGuarderias = procedimiento.getInt(2);

            // muestro el resultado por consola
            System.out.println("*** PROCEDIMIENTO 1: CANTIDAD DE GUARDERIAS QUE SUPERAN UN MAXIMO DE CAPACIDAD DE " + capacidadMaxima + " ***");
            if (cantidadGuarderias > 0) {
                System.out.println("La cantidad de guarderias que superan el limite de capacidad de "
                        + capacidadMaxima + (cantidadGuarderias == 1 ? " es " : " son ") + cantidadGuarderias + ".");
            } else {
                System.out.println("No hay guarderias que superen el limite de capacidad de " + capacidadMaxima + ".");
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el primer procedimiento con nombre **Guarderias_Con_Maxima_Capacidad** " + ex.getMessage());
        } finally {
            try {
                if (procedimiento != null) {
                    procedimiento.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar los recursos del procedimiento con nombre **Guarderias_Con_Maxima_Capacidad**. " + ex.getMessage());
            }
        }
    }

    /**
     * Ejecuta el procedimiento almacenado Educadores_Infantiles.
     *
     * Este procedimiento obtiene la cantidad de educadores con un salario
     * dentro de un intervalo específico y muestra el resultado en la consola.
     *
     * @param conexionDB Conexión a la base de datos.
     * @param minIntervalo Salario mínimo del intervalo.
     * @param maxIntervalo Salario máximo del intervalo.
     */
    public static void ejecutarSegundoProcedimiento(Connection conexionDB, int minIntervalo, int maxIntervalo) {

        CallableStatement procedimiento = null;

        try {
            // prepara la llamada al procedimiento almacenado
            procedimiento = conexionDB.prepareCall("{CALL Educadores_Infantiles(?, ?, ?)}");

            // registrar el parámetro de entrada y el de salida
            procedimiento.setInt(1, minIntervalo); // primer parametro de entrada
            procedimiento.setInt(2, maxIntervalo); // segundo parametro de entrada
            procedimiento.registerOutParameter(3, Types.INTEGER); // parametro de salida

            // ejecutar el procedimiento
            procedimiento.execute();

            // recuperar el valor parámetro de salida, posicion 3
            int cantidadEducadores = procedimiento.getInt(3);

            // muestro el resultado por consola
            System.out.println("\n*** PROCEDIMIENTO 2: CANTIDAD DE EDUCADORES CON UN RANGO SALARIAL ENTRE "
                    + minIntervalo + " Y " + maxIntervalo + " ***");
            if (cantidadEducadores > 0) {
                System.out.println("La cantidad de educadores con un rango salarial entre "
                        + minIntervalo + " y " + maxIntervalo + (cantidadEducadores == 1 ? " es " : " son ") + cantidadEducadores + ".");
            } else {
                System.out.println("No hay educadores con un rango salarial entre " + minIntervalo + " y " + maxIntervalo + ".");
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el segundo procedimiento con nombre **Educadores_Infantiles** " + ex.getMessage());
        } finally {
            try {
                if (procedimiento != null) {
                    procedimiento.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar los recursos del procedimiento con nombre **Educadores_Infantiles**" + ex.getMessage());
            }
        }
    }

    /**
     * Ejecuta el procedimiento almacenado Incremento_Salarial_Porcentaje.
     *
     * Este procedimiento incrementa el salario de los educadores de una
     * guardería en un porcentaje dado y muestra el resultado en la consola.
     *
     * @param conexionDB Conexión a la base de datos.
     * @param codigoGuarderia Código de la guardería a la que pertenecen los
     * educadores.
     * @param porcentajeAumento Porcentaje de incremento salarial.
     */
    public static void ejecutarTercerProcedimiento(Connection conexionDB, String codigoGuarderia, int porcentajeAumento) {

        CallableStatement procedimiento = null;

        try {
            // prepara la llamada al procedimiento almacenado
            procedimiento = conexionDB.prepareCall("{CALL Incremento_Salarial_Porcentaje(?, ?)}");

            // registrar el parámetro de entrada y el de salida
            procedimiento.setString(1, codigoGuarderia); // primer parametro de entrada
            procedimiento.setInt(2, porcentajeAumento); // segundo parametro de entrada

            // ejecutar el procedimiento y almacenar el numero de educadores afectados
            procedimiento.execute();
            int educadoresAfectados = procedimiento.getUpdateCount();

            // muestro el resultado por consola
            System.out.println("\n*** PROCEDIMIENTO 3: AUMENTAR EL SALARIO EN UN "
                    + porcentajeAumento + " % A LOS EDUCADORES DE LA GUARDERIA CON CODIGO " + codigoGuarderia + " ***");
            if (educadoresAfectados > 0) {
                System.out.println("Aumento salarial de un " + porcentajeAumento
                        + " % realizado correctamente a " + educadoresAfectados + " educadores de la guarderia con codigo " + codigoGuarderia + ".");
            } else {
                System.out.println("No se ha encontrado ninguna guarderia con el codigo " + codigoGuarderia + ".");
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar el tercer procedimiento con nombre **Incremento_Salarial_Porcentaje** " + ex.getMessage());
        } finally {
            try {
                if (procedimiento != null) {
                    procedimiento.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar los recursos del procedimiento con nombre **Incremento_Salarial_Porcentaje**" + ex.getMessage());
            }
        }
    }
}
