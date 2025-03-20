package controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;

/**
 *
 * @author andres
 */
public class BaseDeDatos {

    // Nombre del directorio donde guardar las colecciones de vehículos, reparaciones y marcas
    private static final String DIRECTORIO_SALIDA = "AD52425";

    // Crear contexto de BaseX
    private static final Context context = new Context();

    public static Context conectar() throws IOException {

        // Crear el directorio si no existe
        Files.createDirectories(Paths.get(DIRECTORIO_SALIDA));

        String databaseName = "tallerDB";
        String filePath = "BD/taller.xml";

        try {
            // Crear la base de datos a partir del archivo XML
            new CreateDB(databaseName, filePath).execute(context);

            System.out.println("Creación y conexión a la base de datos realizada correctamente.");
            return context;
        } catch (BaseXException ex) {
            System.out.println("Error en la conexión a la base de datos XML");
        }

        return null;
    }

    public static void desconectar() {
        // Cerrar la conexión
        context.close();
        System.out.println("Cierre de la conexión a la base de datos realizado correctamente.");
    }
}
