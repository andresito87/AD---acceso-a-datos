/*
 * SOLUCION Ejercicio 2:
 *
 * Crea un proyecto en Netbeans con nombre AD02_Ej02_ApellidosNombre que establezca conexión con la BD y muestre la 
 * información obtenida, resultado de implementar a través de sentencias preparadas y parametrizadas las siguientes 
 * consultas, por la salida estándar:
 * Consulta 1: Educadores que trabajan en una guardería indicada por su nombre.
 * Consulta 2: Para cada guardería, obtener el número de educadores que trabajan en dicha guardería.
 * Consulta 3: Calcular la cantidad de presupuesto (cantidad de dinero) gastado en el salario de los educadores de una 
 * determinada guardería que se pasará como parámetro (se pasará su código).
 * Debes gestionar las posibles excepciones y errores que puedan presentarse, así como el cierre de recursos utilizados
 * de forma adecuada si la aplicación dejara de funcionar (usa la sentencia try-catch-finally o equivalente).
 *
 * Para la correcta ejecución de este proyecto, se deberá haber añadido en las bibliotecas del proyecto el conectror (.jar) 
 * de MySQL que posibilita la conexión con la base de datos.
 */
package sol_ad02_ej02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Sol_AD02_Ej02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Datos para la conexion //
        String url = "jdbc:mysql://localhost:3306/centroseducacioninfantil";
        String user = "root";
        String password = "root";
        Connection con = null;
        try {
            // Establecer conexión con la base de datos //
            con = DriverManager.getConnection(url, user, password);
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
                //Mostrar menú con las diferentes opciones
                System.out.println("_____________________________________________");
                System.out.println("                    MENU");
                System.out.println("_____________________________________________");
                System.out.println("1.- Educadores que trabajan en una guardería.");
                System.out.println("2.- Guarderías y su número de trabajadores.");
                System.out.println("3.- Cantidad gastada en salarios de una determinada guardería.");
                System.out.println("4.- Salir.");
                System.out.println(">>Introduzca el número de la opción que desea ejecutar:");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        Consulta1(con);
                        break;
                    case 2:
                        Consulta2(con);
                        break;
                    case 3:
                        Consulta3(con);
                        break;
                    case 4:
                        System.out.println(">>Saliendo del programa...");
                        scanner.close();
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                        System.exit(0);
                    default:
                        System.out.println(">>Opción no válida.");
                }
            }            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
    /*
     * Método que implementa la Consulta 1: Educadores que trabajan en una guardería indicada por su nombre.
     * 
     * @param con Conexión con la Base de datos
     */
    public static void Consulta1(Connection con) {

        System.out.println("Las guarderías disponibles son:");
        
        //Consulta para listar los nombres de las guarderías
        try (Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nombre FROM guarderia");){
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                System.out.println(" * " + nombre);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // solicitamos el nombre de una guardería
        System.out.println(">>Introduzca el nombre de la guardería que quiere consultar:");
        Scanner sc = new Scanner(System.in); 
        String nombreGuarderia = sc.nextLine();
   
        try {
            // Preparación de consulta parametrizada
            String consulta = "SELECT * FROM Educador_infantil e, guarderia g "
                    + "WHERE codigo_guarderia=codigo "
                    + "AND g.nombre =?";
            PreparedStatement pstmt;
            pstmt = con.prepareStatement(consulta);

            // Se le asigna valor al parámetro con el dato introducido por teclado y se ejecuta la consulta
            pstmt.setString(1, nombreGuarderia);
            ResultSet rs = pstmt.executeQuery();

            // Se imprimen los resultados recorriendo ResultSet
            System.out.println("\nLos educadores de la guardería " + nombreGuarderia + " son:");   
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                System.out.println(" "+ nombre + " " + apellidos);
            }     
            // liberamos recursos
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *
     * Método que implementa la Consulta 2: Para cada guardería, obtener el número de educadores que trabajan en 
     * dicha guardería.
     *
     * @param con Conexión con la Base de datos
     */
    private static void Consulta2(Connection con) {
        try {
            // preparación de consulta
            String consulta = "SELECT g.nombre, COUNT(e.nombre) total "
                    + "FROM guarderia g, educador_infantil e "
                    + "WHERE e.codigo_guarderia=g.codigo "
                    + "GROUP BY e.codigo_guarderia;";
            PreparedStatement pstmt;
            pstmt = con.prepareStatement(consulta);

            // Se ejecuta la consulta
            ResultSet rs = pstmt.executeQuery();

            // Se imprimen resultados recorriendo el objeto ResultSet
            System.out.println("\nGUARDERÍA              NÚMERO DE EDUCADORES"); 
            while (rs.next()) {
                String nombreGuarderia = rs.getString("nombre");
                int cantidad = rs.getInt("total");
                System.out.printf("%-25s", nombreGuarderia);
                System.out.printf("%-25s", cantidad);
                System.out.println("");
            }
            // liberamos recursos
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *
     * Método que implementa la Consulta 3: Calcular la cantidad de presupuesto (cantidad de dinero) gastado en 
     * el salario de los educadores de una determinada guardería que se pasará como parámetro (se pasará su código).
     *
     * @param con Conexión con la Base de datos
     */
    private static void Consulta3(Connection con) {

        try {
            // preparación de la consulta
            String consulta = "SELECT SUM(salario) total "
                    + "FROM educador_infantil "
                    + "WHERE codigo_guarderia=?";
            PreparedStatement pstmt = con.prepareStatement(consulta);
            
            System.out.println("Las guarderías disponibles son:");
            //Consulta para listar los códigos y nombres de las guarderías
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT codigo, nombre FROM guarderia");){
                while (rs.next()) {
                    System.out.println(" * " + rs.getString("codigo")+" "+rs.getString("nombre"));
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            // Se solicita el código de una guardería
            System.out.println("Introduce código de guardería:");
            Scanner sc = new Scanner(System.in);
            String codigoG = sc.nextLine();

            // Se le asigna valor al parámetro con el dato introducido por teclado y se ejecuta la consulta
            pstmt.setString(1, codigoG);
            ResultSet rs = pstmt.executeQuery();

            // Se imprimen resultados recorriendo el objeto ResultSet
            System.out.print("El gasto en salarios de los educadores de la guarderia " + codigoG + " es: ");
            while (rs.next()) {
                int totalSalarios = rs.getInt("total");
                System.out.println(totalSalarios + " €\n");
            }

            // liberamos recursos
            rs.close();
            pstmt.close();  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
