package db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class ConexionDB4O {

    private static ObjectContainer db = null;

    // Método para abrir la conexión a la base de datos, si el archivo no existe lo creará
    public static ObjectContainer conectar() {
        if (db == null || db.ext().isClosed()) {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
            "songsvault.db4o");
            System.out.println("Conexión a DB4O establecida.");
        }
        return db;
    }

    // Método para cerrar la conexión
    public static void cerrar() {
        if (db != null) {
            db.close();
            System.out.println("Conexión a DB4O cerrada.");
        }
    }
}
