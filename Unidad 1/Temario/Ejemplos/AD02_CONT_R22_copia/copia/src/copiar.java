/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author JJBH
 */
public class copiar {

public static void main(String args[]) {
    // Clase para leer los datos de un fichero
    FileInputStream fuente = null;
    // Clase para escribir los datos a un fichero
    FileOutputStream destino = null;
    try {
        // El fichero fuente es el primer parámetro
        fuente = new FileInputStream(args[0]);
        // El fichero destino es el segundo parámetro.
        destino = new FileOutputStream(args[1],true);

        // Leer del fuente hasta llegar la fin de archivo
        int i = fuente.read();
        while (i != -1) { // mientras not EOF
                destino.write(i);
                i = fuente.read();
        }
        // Cerrar los ficheros
        fuente.close();
        destino.close();
    }catch (IOException e) {
		System.out.println("Error en operaciones de ficheros");
     }
   }
}