package db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Clase que gestiona la conexión a la base de datos DB4O.
 *
 * <p>
 * Esta clase proporciona métodos estáticos para abrir y cerrar la conexión a la
 * base de datos de canciones (denominada "songsvault.db4o"). La conexión se
 * mantiene durante toda la vida de la aplicación y se cierra cuando ya no se
 * necesita.</p>
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class ConexionDB4O {

    /**
     * Instancia de {@link ObjectContainer} que representa la conexión a la base
     * de datos. Esta variable es privada y estática, lo que significa que se
     * comparte a lo largo de la vida de la aplicación.
     */
    private static ObjectContainer db = null;

    /**
     * Establece una conexión con la base de datos DB4O.
     *
     * <p>
     * Este método abre una nueva conexión con la base de datos
     * "songsvault.db4o". Si ya existe una conexión abierta, se verifica su
     * estado antes de reabrirla.</p>
     *
     * @return Un objeto {@link ObjectContainer} que representa la conexión a la
     * base de datos. Si la conexión es exitosa, se devuelve el contenedor de
     * objetos DB4O.
     */
    public static ObjectContainer conectar() {
        if (db == null || db.ext().isClosed()) {
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
                    "songsvault.db4o");
            System.out.println("Conexión a DB4O establecida.");
        }
        return db;
    }

    /**
     * Cierra la conexión actual con la base de datos DB4O.
     *
     * <p>
     * Este método cierra la conexión a la base de datos si está abierta.</p>
     *
     * <p>
     * Se recomienda llamar a este método cuando ya no sea necesario acceder a
     * la base de datos para liberar recursos.</p>
     */
    public static void cerrar() {
        if (db != null) {
            db.close();
            System.out.println("Conexión a DB4O cerrada.");
        }
    }
}
