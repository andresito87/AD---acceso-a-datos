package programaad52425;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.CreateDB;

/**
 *
 * @author andres
 */
public class BaseDeDatos {

    // Crear contexto de BaseX
    private static final Context context = new Context();

    public static Context conectar() {
        String databaseName = "tallerDB";
        String filePath = "BD/taller.xml";

        try {
            // Crear la base de datos a partir del archivo XML
            new CreateDB(databaseName, filePath).execute(context);

            System.out.println("Conexión y creación de la Base de datos realizada correctamente.");
            return context;
        } catch (BaseXException ex) {
            System.out.println("Error en la conexión a la base de datos XML");
        }

        return null;
    }

    public static void desconectar() {
        // Cerrar la conexión
        context.close();
        System.out.println("Se ha cerrado la conexión a la base de datos");
    }
}
