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
 * Controlador para la gestión de objetos de tipo {@code Autor} en la base de
 * datos DB4O. Se encarga de almacenar y gestionar los datos de los autores.
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class AutorController {

    // Objeto que almacenará la conexión a la base de datos
    private static ObjectContainer db = null;

    /**
     * Guarda un objeto {@code Autor} en la base de datos DB4O.
     *
     * <p>
     * Este método verifica si la conexión a la base de datos está activa y, si
     * no lo está, intenta establecerla antes de almacenar el autor. Luego,
     * realiza un commit para confirmar los cambios. Si ocurre algún error, la
     * transacción se revierte con un rollback.</p>
     *
     * @param autor El objeto {@code Autor} a guardar en la base de datos.
     * @return {@code true} si el autor se guardó correctamente, {@code false}
     * si ocurrió un error.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en DB4O.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura.
     * @throws NullPointerException Si el objeto {@code Autor} es nulo.
     */
    public static boolean guardar(Autor autor) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            db.store(autor);
            System.out.println("Autor guardado correctamente: " + autor.getNombre());

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
     * Modifica un autor existente en la base de datos DB4O.
     *
     * <p>
     * Este método busca un autor en la base de datos a partir de su nombre y,
     * si lo encuentra, actualiza sus datos con la información proporcionada en
     * el objeto {@code Autor}. Luego, confirma los cambios con un commit. En
     * caso de error, la transacción se revierte.</p>
     *
     * @param nombreAutorAModificar El nombre del autor que se desea modificar
     * en la base de datos.
     * @param autor El objeto {@code Autor} con los nuevos datos para
     * actualizar.
     * @return {@code true} si el autor fue modificado correctamente,
     * {@code false} si no se encontró el autor o si ocurrió un error durante la
     * actualización.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en DB4O.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura.
     * @throws NullPointerException Si el objeto {@code Autor} es nulo.
     */
    public static boolean modificar(String nombreAutorAModificar, Autor autor) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Buscar si el autor ya existe en la base de datos por nombre
            Query query = db.query();
            query.constrain(Autor.class);
            query.descend("nombre").constrain(nombreAutorAModificar);

            ObjectSet<Autor> resultado = query.execute();

            if (!resultado.isEmpty()) {
                // Si existe, actualizamos los datos
                Autor autorExistente = resultado.next();
                autorExistente.setNombre(autor.getNombre());
                autorExistente.setNacionalidad(autor.getNacionalidad());
                autorExistente.setPaisResidencia(autor.getPaisResidencia());
                autorExistente.setAnyoDeNacimiento(autor.getAnyoDeNacimiento());
                autorExistente.setIngresosAnuales(autor.getIngresosAnuales());

                db.store(autorExistente);
                System.out.println("Autor actualizado correctamente: " + autorExistente.getNombre());
            } else {
                System.out.println("Error: No se encontró el autor en la BD");
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
     * Obtiene una lista de todos los autores almacenados en la base de datos
     * DB4O.
     *
     * <p>
     * Este método consulta la base de datos y devuelve una lista con todos los
     * objetos {@code Autor} almacenados. Si la base de datos está cerrada, se
     * intenta reconectar. En caso de error, se captura la excepción y se
     * imprime un mensaje de error.</p>
     *
     * @return Una lista de {@code Autor} con todos los autores encontrados en
     * la base de datos. Si no hay autores almacenados o si ocurre un error, se
     * devuelve una lista vacía.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en DB4O.
     */
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

        System.out.println("Consulta de todas los autores realizada con éxito: " + autores.size() + " autores obtenidos.");

        return autores; // Devuelve la lista, incluso si está vacía
    }

    /**
     * Busca y obtiene un autor de la base de datos DB4O según su nombre.
     *
     * <p>
     * Este método consulta la base de datos en busca de un objeto {@code Autor}
     * cuyo atributo {@code nombre} coincida con el proporcionado. Si la base de
     * datos está cerrada, se intenta reconectar. En caso de error, se captura
     * la excepción correspondiente y se imprime un mensaje.</p>
     *
     * @param nombre El nombre del autor que se desea buscar.
     * @return El objeto {@code Autor} si se encuentra en la base de datos, de
     * lo contrario, devuelve {@code null}.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en DB4O.
     */
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
                System.out.println("Autor consultado: " + nombre);
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

    /**
     * Elimina un autor de la base de datos DB4O si no tiene canciones
     * asociadas.
     *
     * <p>
     * Este método busca un autor por su nombre y lo elimina de la base de datos
     * solo si no tiene ninguna canción asociada. Si la base de datos está
     * cerrada, se intenta reconectar. En caso de error, se captura la excepción
     * correspondiente y se imprime un mensaje.</p>
     *
     * @param nombreAutor El nombre del autor que se desea eliminar.
     * @return {@code true} si el autor fue eliminado con éxito, {@code false}
     * si el autor no existe, tiene canciones asociadas o ocurrió un error en la
     * operación.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en DB4O.
     * @throws NullPointerException Si se intenta acceder a un objeto nulo.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura.
     */
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

    /**
     * Obtiene una lista de autores cuyos ingresos anuales se encuentran dentro
     * de un rango especificado.
     *
     * <p>
     * Este método consulta la base de datos DB4O y recupera todos los autores
     * cuyo atributo {@code ingresos_anuales} está comprendido entre
     * {@code cantidadInicial} y {@code cantidadFinal}, ambos inclusive.</p>
     *
     * @param cantidadInicial El valor mínimo de ingresos anuales a considerar.
     * @param cantidadFinal El valor máximo de ingresos anuales a considerar.
     * @return Una lista de autores cuyos ingresos están dentro del rango
     * especificado. Si no se encuentran autores, la lista devuelta estará
     * vacía.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     */
    public static List<Autor> obtenerAutoresConSalarioEntre(int cantidadInicial, int cantidadFinal) {
        List<Autor> autoresFiltrados = new ArrayList<>();

        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Crear consulta con restricciones
            Query query = db.query();
            query.constrain(Autor.class);
            query.descend("ingresos_anuales").constrain(cantidadInicial - 1).greater(); // Incluye la cifra límite
            query.descend("ingresos_anuales").constrain(cantidadFinal + 1).smaller(); // Incluye la cifra límite

            ObjectSet<Autor> resultados = query.execute();

            while (resultados.hasNext()) {
                autoresFiltrados.add(resultados.next());
            }

        } catch (DatabaseClosedException e) {
            System.err.println("Error al obtener autores con ingresos entre " + cantidadInicial + " y " + cantidadFinal + ": " + e.getMessage());
        }

        System.out.println("Consultados todos los autores con salario entre "
                + cantidadInicial + " y " + cantidadFinal + " : " + autoresFiltrados.size() + " autores obtenidos");

        return autoresFiltrados;
    }

    /**
     * Aumenta los ingresos anuales de todos los autores en la base de datos en
     * un porcentaje dado.
     *
     * <p>
     * Este método recupera todos los autores almacenados en la base de datos
     * DB4O, calcula el nuevo ingreso aplicando el porcentaje de incremento y
     * actualiza el valor en la base de datos.</p>
     *
     * @param porcentaje El porcentaje en el que se incrementarán los ingresos
     * anuales de cada autor.
     * @return {@code true} si la operación se realizó con éxito, {@code false}
     * si no se encontraron autores o si ocurrió un error.
     *
     * @throws DatabaseClosedException Si la base de datos está cerrada.
     * @throws Db4oIOException Si ocurre un error de entrada/salida en la base
     * de datos.
     * @throws DatabaseReadOnlyException Si la base de datos está en modo solo
     * lectura y no permite modificaciones.
     */
    public static boolean aumentarIngresosEnPorcentaje(int porcentaje) {
        if (db == null || db.ext().isClosed()) {
            db = ConexionDB4O.conectar();
        }

        try {
            // Obtener todos los autores
            ObjectSet<Autor> autores = db.query(Autor.class);

            if (autores.isEmpty()) {
                System.out.println("No hay autores en la base de datos.");
                return false;
            }

            // Aplicar el incremento a cada autor
            for (Autor autor : autores) {
                double nuevoIngreso = autor.getIngresosAnuales() * (1 + porcentaje / 100.0);
                autor.setIngresosAnuales((int) nuevoIngreso);
                db.store(autor); // Guardar cambios en la base de datos
            }

            db.commit(); // Confirmar transacción
            System.out.println("Ingresos anuales de todos los autores aumentados un " + porcentaje + " %.");
            return true;

        } catch (DatabaseClosedException e) {
            System.err.println("Error: La base de datos está cerrada. " + e.getMessage());
        } catch (Db4oIOException e) {
            System.err.println("Error de I/O en db4o: " + e.getMessage());
        } catch (DatabaseReadOnlyException e) {
            System.err.println("Error inesperado al actualizar ingresos: " + e.getMessage());
        }

        return false;
    }
}
