/*
 * SOLUCIOON Ejercicio 3:
 *
 * Desarrolla un proyecto en NetBeans con nombre AD02_Ej03_ApellidoNombre que establezca conexión con la BD y muestre
 * la información obtenida, resultado de ejecutar los siguientes procedimientos almacenados, por la salida estándar:
 *
 * P.A. 1: Un procedimiento almacenado que obtenga en un parámetro de salida, el número total de guarderías que superan
 * una cierta capacidad (numero máximo de alumnos que puede acoger). Se deben pasar como parámetro al procedimiento la 
 * capacidad que se tiene que superar.
 * P.A. 2: Un procedimiento almacenado que devuelva en un parámetro de salida, el número total de educadores infantiles,
 * cuyo salario pertenece a un determinado intervalo económico. Se deben pasar como parámetros al procedimiento, tanto 
 * el valor inicial como el valor final de dicho intervalo.
 * P.A. 3: Un procedimiento almacenado que aumente el salario de los educadores infantiles de cierta guardería en un 
 * porcentaje. La guardería y valor entero del porcentaje a aplicar se han de pasar como parámetros (de entrada).
 * Debes gestionar las posibles excepciones y errores que puedan presentarse, así como el cierre de recursos utilizados 
 * de forma adecuada si la aplicación dejara de funcionar (usa la sentencia try-catch-finally o equivalente).
 *
 * Para la correcta ejecución de este proyecto, se deberá haber añadido en las bibliotecas del proyecto el conectror (.jar) 
 * de MySQL que posibilita la conexión con la base de datos.
 */
package sol_ad02_ej03;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Sol_AD02_Ej03 {

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
                System.out.println("1.- Obtener el número de guarderías que superan una cierta capacidad.");
                System.out.println("2.- Obtener educadores con salario en un determinado rango.");
                System.out.println("3.- Aumentar salario de educadores de una guardería en un porcentaje.");
                System.out.println("4.- Salir.");
                System.out.println(">>Introduzca el número de la opción que desea ejecutar:");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        Procedimiento1(con);
                        break;
                    case 2:
                        Procedimiento2(con);
                        break;
                    case 3:
                        Procedimiento3(con);
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
     * Método que llama al P.A. 1: Un procedimiento almacenado que obtenga en un parámetro de salida, el número
     * total de guarderías que superan una cierta capacidad (numero máximo de alumnos que puede acoger). Se deben 
     * pasar como parámetro al procedimiento la capacidad que se tiene que superar.
     * 
     * @param con Conexión con la Base de datos
     *
     * El procedimiento almacenado previamente en la base de datos es:
    
       DELIMITER $$
       CREATE PROCEDURE Proc1 (IN v_capacidad INT, OUT total INT)
       BEGIN
         SELECT COUNT(*) INTO total
         FROM guarderia
         WHERE capacidad> v_capacidad;
       END 
       $$
      */
    private static void Procedimiento1(Connection con) {
        try {
            // Se solicita la capacidad mímina que se requiere
            Scanner sc = new Scanner(System.in);
            System.out.println(">>Introduzca la capacidad mínima:");
            int numero = Integer.parseInt(sc.nextLine());

            try ( // Se prepara la llamada al procedimiento
                  CallableStatement prc = con.prepareCall("{ call Proc1(?, ?) }")) {
                // Se le da valor al parámetro de entrada "v_capacidad" con el valor introducido por el usuario
                prc.setInt("v_capacidad", numero);
                // Se ejecuta el procedimiento
                prc.executeQuery();
                // Se imprimen el resultado que está disponible en el parámetro de salida "total"
                System.out.println("El número de guarderías con capacidad superior a " + numero + " es de: " + prc.getInt("total") + "\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
     * Método que llama al P.A. 2: Un procedimiento almacenado que devuelva en un parámetro de salida, el número total
     * de educadores infantiles, cuyo salario pertenece a un determinado intervalo económico. Se deben pasar como 
     * parámetros al procedimiento, tanto el valor inicial como el valor final de dicho intervalo.
     * 
     * @param con Conexión con la Base de datos
     *
     * El procedimiento almacenado previamente en la base de datos es:
    
       DELIMITER $$
       CREATE PROCEDURE Proc2 (IN v_minimo INT, v_maximo INT, OUT numEducadores INT)
       BEGIN
         SELECT COUNT(*) INTO numEducadores
	 FROM educador_infantil
	 WHERE salario BETWEEN v_minimo AND v_maximo;
       END 
       $$
      */
    private static void Procedimiento2(Connection con) {
        try {
            // Se solicitan los valores del intervalo de los salarios
            Scanner sc = new Scanner(System.in);
            System.out.println(">>Introduzca el valor mínimo de salario:");
            int salMin = Integer.parseInt(sc.nextLine());
            System.out.println(">>Introduzca el valor máximo de salario:");
            int salMax = Integer.parseInt(sc.nextLine());

            try ( // Se prepara la llamada al procedimiento
                  CallableStatement prc = con.prepareCall("{ call Proc2(?, ?, ?) }")) {
                // Se le da valor a los parámetros de entrada "v_minimo" y "v_maximo" con los valores introducidos por el usuario
                prc.setInt("v_minimo", salMin);
                prc.setInt("v_maximo", salMax);
                // Se ejecuta el procedimiento
                prc.executeQuery();
                // Se imprimen el resultado que está disponible en el parámetro de salida "numEducadores"
                System.out.println("El número de educadores con salario comprendido entre " + salMin + "€ y "+salMax +"€ es de: " + prc.getInt("numEducadores") + "\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
     * Método que llama al P.A. 3: Un procedimiento almacenado que aumente el salario de los educadores infantiles 
     * de cierta guardería en un porcentaje. La guardería y valor entero del porcentaje a aplicar se han de pasar 
     * como parámetros (de entrada).Debes gestionar las posibles excepciones y errores que puedan presentarse, así 
     * como el cierre de recursos utilizados de forma adecuada si la aplicación dejara de funcionar (usa la sentencia try-catch-finally o equivalente).
     * 
     * @param con Conexión con la Base de datos
     *
     * El procedimiento almacenado previamente en la base de datos es:
    
       DELIMITER $$
       CREATE PROCEDURE Proc3 (IN v_guarderia VARCHAR(5), v_porcentaje INT)
       BEGIN
        UPDATE educador_infantil
        SET salario=salario+(salario*v_porcentaje/100)
        WHERE codigo_guarderia = v_guarderia;
       END 
       $$
      */
    private static void Procedimiento3(Connection con) {
        try {
            // Se solicitan el código de guardería y el porcentaje a incrementar
            Scanner sc = new Scanner(System.in);
            System.out.println(">>Introduzca el código de la guarderia:");
            String codG = sc.nextLine();
            System.out.println(">>Introduzca el porcentaje de incremento:");
            int inc = Integer.parseInt(sc.nextLine());

            try ( // Se prepara la llamada al procedimiento
                  CallableStatement prc = con.prepareCall("{ call Proc3(?, ?) }")) {
                // Se le da valor a los parámetros de entrada "v_guarderia" y "v_porcentaje" con los valores introducidos por el usuario
                prc.setString("v_guarderia", codG);
                prc.setInt("v_porcentaje", inc);
                // Se ejecuta el procedimiento
                prc.executeQuery();
                // Se informa de la actualización
                System.out.println("Los salarios de los educadores de la guarderia: " + codG + " se han incrementado en un: " +inc+ "%\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
