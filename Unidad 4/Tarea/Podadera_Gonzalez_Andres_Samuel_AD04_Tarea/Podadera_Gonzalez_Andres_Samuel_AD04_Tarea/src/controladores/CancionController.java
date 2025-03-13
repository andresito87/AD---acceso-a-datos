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
 * Controlador para la gestión de objetos de tipo {@code Cancion} en la base de
 * datos DB4O. Se encarga de almacenar y gestionar los datos de las canciones.
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class CancionController {

    // Objeto que almacenará la conexión a la base de datos
    private static ObjectContainer db = null;

    /**
     * Guarda una canción en la base de datos DB4O. Si la canción ya existe
     * (según su identificador), se actualizan sus datos. Si no existe, se
     * guarda como una nueva entrada.
     *
     * <p>
     * El método busca primero la canción en la base de datos usando su
     * identificador. Si la encuentra, actualiza los atributos de la canción
     * existente. Si no la encuentra, la almacena como una nueva canción.</p>
     *
     * @param cancion La canción que se desea guardar o actualizar.
     * @return {@code true} si la operación se realizó con éxito, {@code false}
     * si ocurrió un error.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en la base
     * de datos.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura.
     * @throws NullPointerException Si el objeto {@code cancion} es nulo.
     */
    public static boolean guardar(Cancion cancion) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Buscar si la canción ya existe en la base de datos por nombre
            Query query = db.query();
            query.constrain(Cancion.class);
            query.descend("identificador").constrain(cancion.getIdentificador());

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

    /**
     * Obtiene todas las canciones almacenadas en la base de datos DB4O.
     *
     * <p>
     * Este método realiza una consulta para recuperar todas las instancias de
     * la clase {@code Cancion} almacenadas en la base de datos. Si la base de
     * datos está cerrada, se intenta establecer una nueva conexión.</p>
     *
     * @return Una lista de objetos {@code Cancion}. Si no hay canciones
     * almacenadas, se devuelve una lista vacía.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en la base
     * de datos.
     */
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

        System.out.println("Consulta de todas las canciones realizada con éxito: " + canciones.size() + " canciones obtenidas.");

        return canciones; // Devuelve la lista, incluso si está vacía
    }

    /**
     * Elimina una canción de la base de datos DB4O utilizando su identificador.
     *
     * <p>
     * Este método busca una canción en la base de datos mediante su ID. Si la
     * encuentra, la elimina y confirma la transacción. Si la canción no existe,
     * muestra un mensaje en la consola y no realiza ninguna eliminación.</p>
     *
     * @param idCancion El identificador único de la canción que se desea
     * eliminar.
     * @return {@code true} si la canción fue eliminada correctamente,
     * {@code false} si no se encontró la canción o si ocurrió un error durante
     * la eliminación.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en la base
     * de datos.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura.
     */
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

    /**
     * Obtiene todas las canciones de la base de datos, ordenadas
     * alfabéticamente por su título.
     *
     * <p>
     * Este método realiza una consulta a la base de datos y devuelve una lista
     * de canciones ordenadas de forma ascendente por su título. Si ocurre algún
     * error durante la consulta, devuelve una lista vacía.</p>
     *
     * @return Una lista de objetos {@link Cancion} ordenados alfabéticamente
     * por su título. Si ocurre un error, devuelve una lista vacía.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en la base
     * de datos.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura.
     */
    public static List<Cancion> obtenerTodasOrdenadasPorTitulo() {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            Query query = db.query();
            query.constrain(Cancion.class);
            query.descend("titulo").orderAscending(); // Ordenar por título ascendente, alfabéticamente

            ObjectSet<Cancion> canciones = query.execute();

            System.out.println("Consulta de todas las canciones ordenadas por título realizada con éxito: " + canciones.size() + " canciones obtenidas.");

            return canciones;
        } catch (DatabaseClosedException e) {
            System.err.println("Error al obtener las canciones ordenadas: " + e.getMessage());
            return new ArrayList<>(); // Devuelve una lista vacía en caso de error
        }
    }

    /**
     * Obtiene todas las canciones de un autor específico de la base de datos.
     *
     * <p>
     * Este método realiza una consulta a la base de datos para recuperar todas
     * las canciones cuyo autor tenga un nombre que coincida (ignorando
     * mayúsculas) con el nombre proporcionado.</p>
     *
     * @param nombreAutor El nombre del autor cuyas canciones se desean obtener.
     * El nombre se compara de forma insensible a mayúsculas y minúsculas.
     * @return Una lista de objetos {@link Cancion} que pertenecen al autor
     * especificado. Si ocurre un error durante la consulta o si no se
     * encuentran canciones, devuelve una lista vacía.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en la base
     * de datos.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura.
     */
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
                    if (nombreEnBD != null && nombreEnBD.toLowerCase().contains(nombreAutor.toLowerCase())) {
                        candidate.include(true);
                    } else {
                        candidate.include(false);
                    }
                }
            });

            ObjectSet<Cancion> canciones = query.execute();

            System.out.println("Consulta de todas las canciones del autor " + nombreAutor
                    + " realizada con éxito: " + canciones.size() + " canciones obtenidas.");

            return canciones;

        } catch (DatabaseClosedException e) {
            System.err.println("Error al obtener canciones del autor " + nombreAutor + ": " + e.getMessage());
        }

        return new ArrayList<>();
    }

    /**
     * Obtiene todas las canciones de autores con una nacionalidad específica de
     * la base de datos.
     *
     * <p>
     * Este método realiza una consulta a la base de datos para recuperar todas
     * las canciones cuyo autor tenga una nacionalidad que coincida (ignorando
     * mayúsculas y minúsculas) con la nacionalidad proporcionada.</p>
     *
     * @param nacionalidad La nacionalidad de los autores cuyas canciones se
     * desean obtener. La comparación se realiza de manera insensible a
     * mayúsculas y minúsculas.
     * @return Una lista de objetos {@link Cancion} que pertenecen a los autores
     * cuya nacionalidad coincide con la proporcionada. Si ocurre un error
     * durante la consulta o si no se encuentran canciones, devuelve una lista
     * vacía.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en la base
     * de datos.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura.
     */
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

            ObjectSet<Cancion> canciones = query.execute();

            System.out.println("Consulta de todas las canciones del autor cuya nacionalidad es " + nacionalidad
                    + " realizada con éxito: " + canciones.size() + " canciones obtenidas.");

            return canciones;

        } catch (DatabaseClosedException e) {
            System.err.println("Error al obtener canciones de los autores con nacionalidad " + nacionalidad + ": " + e.getMessage());
        }

        return new ArrayList<>();
    }
}
