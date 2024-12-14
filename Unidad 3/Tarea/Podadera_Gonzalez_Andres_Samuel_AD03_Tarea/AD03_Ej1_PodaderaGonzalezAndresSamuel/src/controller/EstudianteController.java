package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.dto.RespuestaDTO;
import model.entity.Estudiante;
import model.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author ANDRES SAMUEL PODADERA GONZALEZ
 */
public class EstudianteController {

    /**
     * Recupera una lista con todos los estudiantes de la base de datos,
     * ordenados de forma predeterminada.
     *
     * Este método llama a la versión de {@link #listarTodos(String, String)}
     * con los valores predeterminados para el atributo y el orden. De forma
     * predeterminada, la lista se ordena por el atributo **"nombre"** en
     * **orden ascendente (ASC)**.
     *
     * @return Una lista de objetos {@link Estudiante} que contiene todos los
     * estudiantes de la base de datos, ordenados por **nombre en orden
     * ascendente (ASC)**. Si ocurre algún error, se devuelve una lista vacía.
     */
    public static List<Estudiante> listarTodos() {
        // usa la versión sobrecargada con parámetros con valores predeterminados
        return listarTodos("nif", "ASC");
    }

    /**
     * Recupera una lista de todos los estudiantes, ordenados por un atributo y
     * en un orden específico.
     *
     * @param atributo El atributo por el que se ordenarán los estudiantes.
     * @param orden El tipo de orden, puede ser "ASC" o "DESC".
     * @return Una lista de objetos {@link Estudiante} que contiene todos los
     * estudiantes ordenados.
     */
    public static List<Estudiante> listarTodos(String atributo, String orden) {
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        List<String> atributosPermitidos = Arrays.asList("nif", "nombre", "apellidos", "fechaNacimiento", "direccion", "provincia", "importeMatricula", "becado");
        Session session = null;

        try {
            if (!atributosPermitidos.contains(atributo)) {
                throw new IllegalArgumentException("El atributo '" + atributo + "' no está permitido.");
            }

            if (!orden.equalsIgnoreCase("ASC") && !orden.equalsIgnoreCase("DESC")) {
                throw new IllegalArgumentException("El orden debe ser 'ASC' o 'DESC'.");
            }

            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Estudiante e ORDER BY e." + atributo + " " + orden;
            listaEstudiantes = session.createQuery(hql).list();

        } catch (IllegalArgumentException | HibernateException e) {
            System.out.println("Error al listar todos los estudiantes. " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listaEstudiantes;
    }

    /**
     * Lista los estudiantes de una universidad específica.
     *
     * @param codigoUniversidad El código de la universidad cuyos estudiantes se
     * quieren listar.
     * @return Lista de estudiantes que pertenecen a la universidad.
     */
    public static List<Estudiante> listarPorCodigoUniversidad(int codigoUniversidad) {

        List<Estudiante> listaEstudiantes = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            // JOIN FETCH carga de inmediato la universidad y evita problemas con el lazyloading o carga retardada de hibernate 
            listaEstudiantes = session.createQuery(
                    "FROM Estudiante e JOIN FETCH e.universidad WHERE e.universidad.codigo = :codigoUniversidad"
            )
                    .setParameter("codigoUniversidad", codigoUniversidad)
                    .list();

        } catch (Exception e) {
            System.err.println("Error al listar los estudiantes de la universidad con código: " + codigoUniversidad);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return listaEstudiantes;
    }

    /**
     * Inserta una nuevo Estudiante en la base de datos.
     *
     * @param estudiante El estudiante que se va a insertar.
     * @return RespuestaDTO Devuelve una respuesta con la operacion realizada
     */
    public static RespuestaDTO agregarNuevo(Estudiante estudiante) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // iniciar la transacción
            transaction = session.beginTransaction();

            // guardar la universidad
            session.save(estudiante);

            // confirmar la transacción
            transaction.commit();

            System.out.println("Estudiante agregado con éxito: " + estudiante);

            // configurar la respuesta de éxito
            respuesta.setSuccess(true);
            respuesta.setMessage("Estudiante agregado con éxito");
            respuesta.setData(estudiante); // devolvemos el estudiante agregado

        } catch (ConstraintViolationException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // revertir la transacción en caso de error
            }

            System.err.println("Clave primaria duplicada: " + e.getMessage());

            // configurar la respuesta de error específica para clave duplicada
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo agregar el estudiante. El nif ya existe en la base de datos.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // revertir la transacción en caso de error
            }

            System.err.println("Error al insertar el estudiante: " + e.getMessage());

            // configurar la respuesta de error general
            respuesta.setSuccess(false);
            respuesta.setMessage("Error al insertar el estudiante: " + e.getMessage());
        } finally {
            // cerrar la sesión de forma segura
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar la sesión: " + e.getMessage());
                }
            }
        }
        return respuesta;
    }

    /**
     * Elimina un Estudiante de la base de datos.
     *
     * @param nifEstudiante El nif del estudiante que se va a eliminar.
     * @return RespuestaDTO Devuelve una respuesta con la operacion realizada
     */
    public static RespuestaDTO eliminar(String nifEstudiante) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // obtener estudiante de la base de datos
            Estudiante estudiante = EstudianteController.obtener(nifEstudiante);

            // iniciar la transacción
            transaction = session.beginTransaction();

            // eliminar el estudiante
            session.delete(estudiante);

            // confirmar la transacción
            transaction.commit();

            // configurar respuesta de éxito
            respuesta.setSuccess(true);
            respuesta.setMessage("Estudiante eliminado con éxito.");
            respuesta.setData(estudiante); // retorna el estudiante eliminado

        } catch (Exception e) {
            // revertir la transacción si se produjo un error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            // configurar la respuesta de error
            respuesta.setSuccess(false);
            respuesta.setMessage("Error al eliminar el estudiante: " + e.getMessage());
        } finally {
            // cerrar la sesión de forma segura
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar la sesión: " + e.getMessage());
                }
            }
        }
        return respuesta;
    }

    /**
     * Modifica un Estudiante de la base de datos.
     *
     * @param estudiante El estudiante que se va a modificar.
     * @return RespuestaDTO Devuelve una respuesta con la operacion realizada
     */
    public static RespuestaDTO modificar(Estudiante estudiante) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // iniciar la transacción
            transaction = session.beginTransaction();

            // modificar la entidad (update)
            session.update(estudiante);

            // confirmar la transacción
            transaction.commit();
            System.out.println("Estudiante modificado con éxito: " + estudiante);

            // configurar la respuesta de éxito
            respuesta.setSuccess(true);
            respuesta.setMessage("Estudiante modificado con éxito.");
            respuesta.setData(estudiante); // devolvemos el estudiante modificado

        } catch (ConstraintViolationException e) {
            // revertir la transacción si hay algún error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Clave primaria duplicada: " + e.getMessage());

            // configurar la respuesta de error específica para clave duplicada
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo modificar el estudiante. El nif ya existe en la base de datos.");
        } catch (Exception e) {
            // revertir la transacción si hay algún error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al modificar el estudiante: " + e.getMessage());

            // configurar la respuesta de error general
            respuesta.setSuccess(false);
            respuesta.setMessage("Error al modificar el estudiante: " + e.getMessage());
        } finally {
            // cerrar la sesión de forma segura
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar la sesión: " + e.getMessage());
                }
            }
        }

        return respuesta;
    }

    /**
     * Devuelve una Universidad de la base de datos.
     *
     * @param nifEstudiante Nif del estudiante que va a devolver.
     * @return RespuestaDTO Devuelve una respuesta con la operacion realizada
     */
    public static Estudiante obtener(String nifEstudiante) {
        Session session = null;
        Estudiante estudiante = null;

        try {
            // Abrir la sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();

            // Obtener la universidad usando el código proporcionado
            estudiante = (Estudiante) session.get(Estudiante.class, nifEstudiante);

            // Validar si la universidad existe
            if (estudiante == null) {
                System.err.println("No se encontró el estudiante con el nif: " + nifEstudiante);
            } else {
                System.out.println("Estudiante encontrado: " + estudiante.getNif());
            }

        } catch (HibernateException e) {
            System.err.println("Error de Hibernate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        } finally {
            // Cerrar la sesión de forma segura
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar la sesión: " + e.getMessage());
                }
            }
        }

        return estudiante;
    }

    /**
     * Obtiene una lista de estudiantes que residen en la provincia de "Almería"
     * y que además están becados.
     *
     * Este método utiliza una consulta HQL para filtrar los estudiantes cuya
     * provincia es "Almería" y cuyo estado de becado es verdadero (true). La
     * consulta se realiza mediante la apertura de una sesión de Hibernate y el
     * uso de parámetros en la consulta para hacerla más segura.
     *
     * @return Una lista de objetos {@link Estudiante} que contiene los
     * estudiantes que cumplen con la condición de estar en la provincia de
     * "ALMERIA" y estar becados. Si ocurre algún error o no se encuentran
     * estudiantes, se devuelve **null**.
     *
     * @throws HibernateException Si ocurre algún problema al abrir la sesión,
     * ejecutar la consulta o procesar los resultados.
     */
    public static List<Estudiante> obtenerAlmeriensesBecados() {
        Session session = null;
        List<Estudiante> listaEstudiantes = null;

        try {
            // Abrir la sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            // Crear la consulta HQL para obtener la lista de estudiantes
            String hql = "SELECT e FROM Estudiante e "
                    + "WHERE e.provincia = :provincia "
                    + "AND e.becado = :becado";

            // Crear la consulta con parámetros
            Query query = session.createQuery(hql);
            query.setParameter("provincia", "ALMERIA");
            query.setParameter("becado", true);

            // Obtener la lista de resultados
            listaEstudiantes = query.list();

        } catch (HibernateException e) {
            System.err.println("Error de Hibernate: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        } finally {
            // Cerrar la sesión de forma segura
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar la sesión: " + e.getMessage());
                }
            }
        }

        return listaEstudiantes;
    }

    public static RespuestaDTO modificarImporte(float nuevoImporte, String nif) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta;

        try {
            // abrir la sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // consulta SQL nativa
            String sql = "UPDATE estudiante SET importe_matricula = :nuevoImporte WHERE nif = :nif";

            // crear la consulta SQL nativa
            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter("nuevoImporte", nuevoImporte);
            query.setParameter("nif", nif);

            // ejecutar la actualización
            int filasAfectadas = query.executeUpdate();

            // confirmar la transacción
            transaction.commit();

            if (filasAfectadas > 0) {
                respuesta = new RespuestaDTO(true, "Importe actualizado correctamente para el NIF: " + nif);
            } else {
                respuesta = new RespuestaDTO(false, "No se encontró ningún estudiante con el NIF: " + nif);
            }

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback(); // Deshacer la transacción en caso de error
            }
            respuesta = new RespuestaDTO(false, "Error de Hibernate: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Deshacer la transacción en caso de error general
            }
            respuesta = new RespuestaDTO(false, "Error general: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar la sesión: " + e.getMessage());
                }
            }
        }

        return respuesta;
    }
}
