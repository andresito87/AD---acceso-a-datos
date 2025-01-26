/* 
 * SOLUCION TAREA 1 AD 
 * Proyecto que resuelve los ejercicios 2 y 3 con los siguientes enunciados:
 * 
 * EJERCICIO 2.
 * Esta tarea vamos a crear un programa en Java, que permite introducir el nombre, apellidos, y e-mail de un usuario 
 * mediante un menú que pida estos datos y los grabe de forma secuencial en un fichero llamado "datosContacto.dat" 
 * del directorio local del proyecto. Sería necesario introducir un carácter como por ejemplo $ para separar un campo 
 * de otro. Deberás documentar bien tu código así como realizar el adecuado manejo de las posibles excepciones.
 * EJERCICIO 3.
 * Crea un programa en Java, que permita leer el fichero llamado "datosContacto.dat" del ejercicio anterior del 
 * directorio datos y permita mostrarlos por pantalla de forma secuencial, también habría que contar el número de 
 * registros que existen en el fichero. Se entiende por un registro cada línea creada por cada uno de los campos. 
 * Deberás documentar bien tu código así como realizar el adecuado manejo de las posibles excepciones.
 */
package soltarea1ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */


public class SolTarea1AD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Mostrar datos de todos los usuarios");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    mostrarFichero();
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void registrarUsuario() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nombre del usuario: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellidos del usuario: ");
            String apellidos = scanner.nextLine();

            System.out.print("Email del usuario: ");
            String email = scanner.next();
            
            String datosUsuario = nombre + "$" + apellidos + "$" + email;
          
            //Creamos un objeto FileWriter que referencia al archivo "datosContacto.dat" y que lo crea
            //si no existe, y si existe permite que se añadan datos al final del mismo como
            //archivo secuencial.
            FileWriter fileWriter = new FileWriter("datosContacto.dat", true);
            
            //BufferedWriter es una clase en Java que proporciona un búfer para escribir texto en un flujo de 
            //salida de caracteres. BuffererdWriter se asociará con el FileWriter y aprovechará sus capacidades 
            //de escritura, pero con el beneficio adicional de un búfer, que puede mejorar el rendimiento al 
            //agrupar escrituras en bloques más grandes antes de enviarlas al sistema de archivos.  
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(datosUsuario);
                bufferedWriter.newLine();
            }
            fileWriter.close();
            scanner.close();
            
            System.out.println("Usuario registrado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
                
    }
    
    private static void mostrarFichero() {
        String archivo = "datosContacto.dat";

        try {
            //Creamos un objeto FileReader que se conecta a un archivo específico en el sistema
            //de archivos. El constructor de FileReader toma un objeto File o una cadena que representa 
            //la ruta del archivo como parámetro.
            //FileReader se utiliza para leer caracteres de un archivo.
            FileReader fileReader = new FileReader(archivo);
            int contadorRegistros;
            
            //BufferedReader se utiliza para mejorar la eficiencia y proporcionar la capacidad 
            //de leer líneas completas en lugar de caracteres individuales. 
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String linea;
                contadorRegistros = 0;
                System.out.println("Datos de usuarios:");
                while ((linea = bufferedReader.readLine()) != null) {
                    String[] campos = linea.split("\\$");
                    
                    if (campos.length == 3) {
                        String nombre = campos[0];
                        String apellidos = campos[1];
                        String email = campos[2];
                        
                        System.out.println("Nombre: " + nombre);
                        System.out.println("Apellidos: " + apellidos);
                        System.out.println("Email: " + email);
                        System.out.println();
                        
                        contadorRegistros++;
                    }
                }
            }
            fileReader.close();

            System.out.println("Número total de registros: " + contadorRegistros);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }   
    
}
