package ad02_ej02_podaderagonzalezandressamuel;

/**
 * Crea un proyecto en Netbeans con nombre AD02_Ej02_ApellidosNombre que
 * establezca conexión con la BD y muestre la información obtenida, resultado de
 * implementar a través de sentencias preparadas y parametrizadas las siguientes
 * consultas, por la salida estándar:
 *
 * Consulta 1: Educadores que trabajan en una guardería indicada por su nombre.
 *
 * Consulta 2: Para cada guardería, obtener el número de educadores que trabajan
 * en dicha guardería.
 *
 * Consulta 3: Calcular la cantidad de presupuesto (cantidad de dinero) gastado
 * en el salario de los educadores de una determinada guardería que se pasará
 * como parámetro (se pasará su código).
 *
 * Debes gestionar las posibles excepciones y errores que puedan presentarse,
 * así como el cierre de recursos utilizados de forma adecuada si la aplicación
 * dejara de funcionar (usa la sentencia try-catch-finally o equivalente).
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Este programa establece conexión con una base de datos MySQL llamada
 * centroseducacioninfantil y realiza consultas parametrizadas para obtener
 * información sobre guarderías y sus educadores.
 *
 * <ul>
 * <li>Consulta 1: Lista de educadores que trabajan en una guardería específica
 * por nombre.</li>
 * <li>Consulta 2: Número de educadores que trabajan en cada guardería.</li>
 * <li>Consulta 3: Presupuesto gastado en salarios en una guardería específica
 * por código.</li>
 * </ul>
 *
 * La aplicación utiliza sentencias preparadas para evitar inyecciones SQL.
 * También gestiona posibles errores y garantiza el cierre adecuado de recursos.
 *
 * @author ANDRES SAMUEL PODADERA GONZALEZ
 * <p>
 * Desarrollo de aplicaciones multiplataforma - Acceso a datos - 2024/2025
 * </P>
 */
public class AD02_Ej02_PodaderaGonzalezAndresSamuel {

    /**
     * Punto de entrada principal del programa.
     * <p>
     * Establece la conexión con la base de datos, ejecuta las consultas y
     * muestra los resultados en la salida estándar.
     * </p>
     *
     * @param args argumentos de la línea de comandos (no utilizados)
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
            //System.out.println("Conexion a la base de datos realizada correctamente");

            // ejecutar la primera sentencia y mostrar resultado
            String nombreGuarderia = "Centro Aventura";
            ejecutarPrimeraConsulta(conexionDB, nombreGuarderia);

            // ejecutar la segunda sentencia y mostrar el resultado
            ejecutarSegundaConsulta(conexionDB);

            // ejecutar la tercera sentencia y mostrar el resultado
            String codigoGuarderia = "ABC01";
            ejecutarTerceraConsulta(conexionDB, codigoGuarderia);

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
     * Consulta y muestra los educadores que trabajan en una guardería
     * específica.
     *
     * @param conexionDB la conexión a la base de datos
     * @param nombreGuarderia el nombre de la guardería
     */
    public static void ejecutarPrimeraConsulta(Connection conexionDB, String nombreGuarderia) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // crea la consulta con un parámetro para el nombre de la guardería
            String sql = "SELECT educador.dni, educador.nombre, educador.apellidos "
                    + "FROM Educador_Infantil educador, Guarderia guarderia "
                    + "WHERE educador.codigo_guarderia = guarderia.codigo "
                    + "AND guarderia.nombre LIKE ?";

            // prepara la sentencia
            pstmt = conexionDB.prepareStatement(sql);

            // sustituye el parámetro por el valor de 'nombreguarderia'
            pstmt.setString(1, "%" + nombreGuarderia + "%");

            // ejecuta la consulta
            rs = pstmt.executeQuery();

            // procesa los resultados
            System.out.println("*** CONSULTA 1: LISTADO DE TRABAJADORES DE GUARDERIA - " + nombreGuarderia + " ***");
            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");

                System.out.printf("DNI: %-10s | Nombre: %-10s | Apellidos: %-20s\n",
                        dni,
                        nombre,
                        apellidos);
            }
        } catch (SQLException ex) {
            System.out.println("Error al realizar la primera consulta a la base de datos");
            System.out.println(ex.getMessage());
        } finally {
            // cierra el ResultSet y el Statement
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar los recursos de la primera consulta: " + ex.getMessage());
            }
        }
    }

    /**
     * Consulta y muestra el número de educadores que trabajan en cada
     * guardería.
     *
     * @param conexionDB la conexión a la base de datos
     */
    public static void ejecutarSegundaConsulta(Connection conexionDB) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // crea la consulta
            String sql = "SELECT guarderia.nombre,count(*) AS numeroTrabajadores "
                    + "FROM Educador_Infantil educador,Guarderia guarderia "
                    + "WHERE educador.codigo_guarderia=guarderia.codigo "
                    + "GROUP BY guarderia.codigo";

            // prepara la sentencia
            pstmt = conexionDB.prepareStatement(sql);

            // ejecuta la consulta
            rs = pstmt.executeQuery();

            // procesa los resultados
            System.out.println("\n*** CONSULTA 2: NUMERO DE TRABAJADORES POR GUARDERIA ***");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int numeroTrabajadores = rs.getInt("numeroTrabajadores");

                System.out.printf("Guarderia: %-20s - Trabajadores: %d\n",
                        nombre,
                        numeroTrabajadores);
            }
        } catch (SQLException ex) {
            System.out.println("Error al realizar la segunda consulta a la base de datos");
            System.out.println(ex.getMessage());
        } finally {
            // cierra el ResultSet y el Statement
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar los recursos de la segunda consulta: " + ex.getMessage());
            }
        }
    }

    /**
     * Consulta y muestra el presupuesto gastado en salarios para una guardería
     * específica.
     *
     * @param conexionDB la conexión a la base de datos
     * @param codigoGuarderia el código de la guardería
     */
    public static void ejecutarTerceraConsulta(Connection conexionDB, String codigoGuarderia) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // crea la consulta parametrizada
            String sql = "SELECT SUM(educador.salario) AS cantidadGastada "
                    + "FROM Educador_Infantil educador, Guarderia guarderia "
                    + "WHERE educador.codigo_guarderia = guarderia.codigo "
                    + "AND educador.codigo_guarderia LIKE ?";

            // prepara la sentencia
            pstmt = conexionDB.prepareStatement(sql);

            // sustituye el parámetro
            pstmt.setString(1, "%" + codigoGuarderia + "%");

            // ejecuta la consulta
            rs = pstmt.executeQuery();

            // procesa los resultados
            System.out.println("\n*** CONSULTA 3: CANTIDAD DE DINERO GASTADA EN LOS TRABAJADORES DE LA GUARDERIA CON CODIGO: " + codigoGuarderia + " ***");
            if (rs.next()) {
                int cantidadGastada = rs.getInt("cantidadGastada");
                System.out.printf("Cantidad de presupuesto gastada en profesores: %d\n", cantidadGastada);
            } else {
                System.out.println("No se encontró información para el código de guardería especificado.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al realizar la tercera consulta a la base de datos");
            System.out.println(ex.getMessage());
        } finally {
            // cierra el ResultSet y el Statement
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar los recursos de la tercera consulta: " + ex.getMessage());
            }
        }
    }
}
