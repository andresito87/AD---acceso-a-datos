package controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;

/**
 * Clase que gestiona la conexión a una base de datos XML mediante BaseX.
 * Permite conectar y desconectar de la base de datos.
 *
 * @author Andres
 */
public class ConexionBD {

    // Nombre del directorio donde guardar las colecciones de vehículos, reparaciones y marcas
    private static final String DIRECTORIO_SALIDA = "AD52425";

    // Crear contexto de BaseX
    private static Context contexto = null;

    /**
     * Establece la conexión con la base de datos BaseX. Si el directorio de
     * salida no existe, lo crea. Si la base de datos no ha sido creada
     * previamente, la crea a partir de un archivo XML.
     *
     * @return el contexto de la base de datos si la conexión es exitosa, o
     * {@code null} en caso de error.
     */
    public static Context conectar() {
        try {
            // Crear el directorio si no existe
            Files.createDirectories(Paths.get(DIRECTORIO_SALIDA));

            String databaseName = "tallerDB";
            String filePath = "BD/taller.xml";

            if (contexto == null) {
                contexto = new Context();
                // Crear la base de datos a partir del archivo XML
                new CreateDB(databaseName, filePath).execute(contexto);
                System.out.println("Creación y conexión a la base de datos realizada correctamente.");
            }

            return contexto;
        } catch (BaseXException ex) {
            System.out.println("Error en la conexión a la base de datos XML");
        } catch (IOException ex) {
            System.out.println("Error en la entrada/salida de datos.");
        }

        return null;
    }

    /**
     * Cierra la conexión con la base de datos BaseX.
     */
    public static void desconectar() {
        // Cerrar la conexión
        contexto.close();
        System.out.println("Cierre de la conexión a la base de datos realizado correctamente.");
    }
}
