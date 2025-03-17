package programaad52425;

import java.io.IOException;
import org.basex.core.*;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        try {
            String directorioSalida = "AD52425";

            // Crear el directorio si no existe
            Files.createDirectories(Paths.get(directorioSalida));

            Context context = BaseDeDatos.conectar();

            // EJERCICIO 1: Procesar y guardar colecciones
            TallerController.guardarVehiculos(context, directorioSalida);
            TallerController.guardarReparaciones(context, directorioSalida);
            TallerController.guardarMarcas(context, directorioSalida);

            BaseDeDatos.desconectar();

        } catch (IOException ex) {
            System.out.println("Error de Entrada/Salida " + ex.getMessage());
        }
    }

}
