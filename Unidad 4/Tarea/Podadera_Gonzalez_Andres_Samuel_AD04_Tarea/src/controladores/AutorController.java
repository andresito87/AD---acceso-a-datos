package controladores;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Query;
import db.ConexionDB4O;
import java.util.ArrayList;
import java.util.List;
import modelos.Autor;
import modelos.Cancion;

/**
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class AutorController {

    private static ObjectContainer db = null;

    public static boolean guardar(Autor autor) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Buscar si el autor ya existe en la base de datos por nombre
            Query query = db.query();
            query.constrain(Autor.class);
            query.descend("nombre").constrain(autor.getNombre());

            ObjectSet<Autor> resultado = query.execute();

            if (!resultado.isEmpty()) {
                // Si existe, actualizamos los datos
                Autor autorExistente = resultado.next();
                autorExistente.setNacionalidad(autor.getNacionalidad());
                autorExistente.setPaisResidencia(autor.getPaisResidencia());
                autorExistente.setAnyoDeNacimiento(autor.getAnyoDeNacimiento());
                autorExistente.setIngresosAnuales(autor.getIngresosAnuales());

                db.store(autorExistente);
                System.out.println("Autor actualizado correctamente: " + autorExistente.getNombre());
            } else {
                // Si no existe, lo guardamos como un nuevo autor
                db.store(autor);
                System.out.println("Autor guardado correctamente: " + autor.getNombre());
            }

            db.commit(); // Confirmamos los cambios
            return true;

        } catch (DatabaseClosedException e) {
            System.err.println("Error: La base de datos está cerrada. " + e.getMessage());
        } catch (Db4oIOException e) {
            System.err.println("Error de I/O en db4o: " + e.getMessage());
        } catch (DatabaseReadOnlyException e) {
            System.err.println("Error: La base de datos está en modo solo lectura. " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error: Se intentó guardar un objeto nulo. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al guardar o actualizar el autor: " + e.getMessage());
        } finally {
            try {
                db.rollback(); // Revertimos la transacción en caso de error
            } catch (Exception rollbackError) {
                System.err.println("Error al revertir la transacción: " + rollbackError.getMessage());
            }
        }

        return false; // Indica que la operación falló
    }

    public static List<Autor> obtenerTodos() {

        List<Autor> autores = new ArrayList<>();

        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Consulta todas los autores en la base de datos
            ObjectSet<Autor> resultados = db.query(Autor.class);

            while (resultados.hasNext()) {
                autores.add(resultados.next());
            }
        } catch (DatabaseClosedException e) {
            System.err.println("Error: La base de datos está cerrada. " + e.getMessage());
        } catch (Db4oIOException e) {
            System.err.println("Error de I/O en db4o: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener los autores: " + e.getMessage());
        }

        return autores; // Devuelve la lista, incluso si está vacía
    }

    public static Autor obtener(String nombre) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Crear una consulta en db4o para buscar el autor por nombre
            Query query = db.query();
            query.constrain(Autor.class);
            query.descend("nombre").constrain(nombre);

            ObjectSet<Autor> resultado = query.execute();

            if (!resultado.isEmpty()) {
                return resultado.next(); // Retorna el primer autor encontrado
            } else {
                System.out.println("No se encontró el autor: " + nombre);
            }
        } catch (DatabaseClosedException e) {
            System.err.println("Error: La base de datos está cerrada. " + e.getMessage());
        } catch (Db4oIOException e) {
            System.err.println("Error de I/O en db4o: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al buscar el autor: " + e.getMessage());
        }

        return null; // Si no se encontró, devuelve null
    }

    public static boolean eliminar(String nombreAutor) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Verificar si el autor tiene canciones asociadas
            Query queryCanciones = db.query();
            queryCanciones.constrain(Cancion.class);
            queryCanciones.descend("autor").descend("nombre").constrain(nombreAutor);

            if (!queryCanciones.execute().isEmpty()) {
                System.out.println("No se puede eliminar el autor '" + nombreAutor + "' porque tiene canciones asociadas.");
                return false;
            }

            // Buscar el autor en la base de datos
            Query query = db.query();
            query.constrain(Autor.class);
            query.descend("nombre").constrain(nombreAutor);

            ObjectSet<Autor> resultado = query.execute();

            if (!resultado.isEmpty()) {
                Autor autorAEliminar = resultado.next();
                db.delete(autorAEliminar);
                db.commit(); // Confirmar cambios
                System.out.println("Autor eliminado correctamente: " + autorAEliminar.getNombre());
                return true;
            } else {
                System.out.println("No se encontró el autor: " + nombreAutor);
                return false;
            }
        } catch (DatabaseClosedException e) {
            System.err.println("Error: La base de datos está cerrada. " + e.getMessage());
        } catch (Db4oIOException e) {
            System.err.println("Error de I/O en db4o: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error: Se intentó acceder a un objeto nulo. " + e.getMessage());
        } catch (DatabaseReadOnlyException e) {
            System.err.println("Error de escritura al eliminar el autor: " + e.getMessage());
        }

        return false; // Si hubo algún error, devolvemos false
    }

}
