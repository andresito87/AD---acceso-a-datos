package controladores;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;
import db.ConexionDB4O;
import java.util.ArrayList;
import java.util.List;
import modelos.Cancion;

/**
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class CancionController {

    private static ObjectContainer db = null;

    public static boolean guardar(Cancion cancion) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Buscar si el autor ya existe en la base de datos por nombre
            Query query = db.query();
            query.constrain(Cancion.class);
            query.descend("titulo").constrain(cancion.getTitulo());

            ObjectSet<Cancion> resultado = query.execute();

            if (!resultado.isEmpty()) {
                // Si existe, actualizamos los datos
                Cancion cancionExistente = resultado.next();
                cancionExistente.setTitulo(cancion.getTitulo());
                cancionExistente.setDuracion(cancion.getDuracion());
                cancionExistente.setAnyoCreacion(cancion.getAnyoCreacion());
                cancionExistente.setGenero(cancion.getGenero());
                cancionExistente.setAutor(cancion.getAutor());

                db.store(cancionExistente);
                System.out.println("Canción actualizada correctamente: " + cancionExistente.getTitulo());
            } else {
                // Si no existe, lo guardamos como un nuevo autor
                db.store(cancion);
                System.out.println("Canción guardada correctamente: " + cancion.getTitulo());
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
            } catch (DatabaseClosedException | DatabaseReadOnlyException | Db4oIOException rollbackError) {
                System.err.println("Error al revertir la transacción: " + rollbackError.getMessage());
            }
        }

        return false; // Indica que la operación falló
    }

    public static List<Cancion> obtenerTodas() {
        List<Cancion> canciones = new ArrayList<>();

        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Consulta todas las canciones en la base de datos
            ObjectSet<Cancion> resultados = db.query(Cancion.class);

            while (resultados.hasNext()) {
                canciones.add(resultados.next());
            }
        } catch (DatabaseClosedException e) {
            System.err.println("Error: La base de datos está cerrada. " + e.getMessage());
        } catch (Db4oIOException e) {
            System.err.println("Error de I/O en db4o: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado al obtener las canciones: " + e.getMessage());
        }

        return canciones; // Devuelve la lista, incluso si está vacía
    }

    public static boolean eliminar(String idCancion) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Crear una consulta con restricciones por ID
            Query query = db.query();
            query.constrain(Cancion.class);
            query.descend("identificador").constrain(idCancion);

            ObjectSet<Cancion> resultado = query.execute();

            if (!resultado.isEmpty()) {
                Cancion cancionAEliminar = resultado.next();
                db.delete(cancionAEliminar);
                db.commit(); // Confirmar cambios en la base de datos
                System.out.println("Canción eliminada correctamente: " + cancionAEliminar.getTitulo());
                return true;
            } else {
                System.out.println("No se encontró la canción con ID: " + idCancion);
            }
        } catch (DatabaseClosedException e) {
            System.err.println("Error: La base de datos está cerrada. " + e.getMessage());
        } catch (Db4oIOException e) {
            System.err.println("Error de I/O en db4o: " + e.getMessage());
        } catch (DatabaseReadOnlyException e) {
            System.err.println("Error de escritura al eliminar la canción: " + e.getMessage());
        }

        return false;
    }

    public static List<Cancion> obtenerTodasOrdenadasPorTitulo() {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            Query query = db.query();
            query.constrain(Cancion.class);
            query.descend("titulo").orderAscending(); // Ordenar por título ascendente, alfabéticamente

            return query.execute();
        } catch (DatabaseClosedException e) {
            System.err.println("Error al obtener las canciones ordenadas: " + e.getMessage());
            return new ArrayList<>(); // Devuelve una lista vacía en caso de error
        }
    }

    public static List<Cancion> obtenerTodasDelAutor(String nombreAutor) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            Query query = db.query();
            query.constrain(Cancion.class);
            query.descend("autor").descend("nombre").constrain(new Evaluation() { // filtra resultados ignorando mayúsculas
                @Override
                public void evaluate(Candidate candidate) {
                    String nombreEnBD = (String) candidate.getObject();
                    if (nombreEnBD != null && nombreEnBD.equalsIgnoreCase(nombreAutor)) {
                        candidate.include(true);
                    } else {
                        candidate.include(false);
                    }
                }
            });

            return query.execute(); // Devuelve los resultados directamente desde DB4O

        } catch (DatabaseClosedException e) {
            System.err.println("Error al obtener canciones del autor " + nombreAutor + ": " + e.getMessage());
        }

        return new ArrayList<>();
    }

    public static List<Cancion> obtenerTodasDelAutorConNacionalidad(String nacionalidad) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            Query query = db.query();
            query.constrain(Cancion.class);
            query.descend("autor").descend("nacionalidad").constrain(new Evaluation() { // filtra resultados ignorando mayúsculas
                @Override
                public void evaluate(Candidate candidate) {
                    String nombreEnBD = (String) candidate.getObject();
                    if (nombreEnBD != null && nombreEnBD.equalsIgnoreCase(nacionalidad)) {
                        candidate.include(true);
                    } else {
                        candidate.include(false);
                    }
                }
            });

            return query.execute(); // Devuelve los resultados directamente desde DB4O

        } catch (DatabaseClosedException e) {
            System.err.println("Error al obtener canciones de los autores con nacionalidad " + nacionalidad + ": " + e.getMessage());
        }

        return new ArrayList<>();
    }
}
