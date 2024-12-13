package controller;

import java.util.ArrayList;
import java.util.List;
import model.dto.RespuestaDTO;
import model.entity.Estudiante;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author ANDRES SAMUEL PODADERA GONZALEZ
 */
public class EstudianteController {

    // Singleton: SessionFactory solo se crea una vez
    private static SessionFactory sessionFactory;

    // Método para inicializar SessionFactory de forma segura y eficiente (patrón Singleton)
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure(UniversidadController.class.getClassLoader().getResource("model/hibernate/hibernate.cfg.xml"))
                        .buildSessionFactory();

                Runtime.getRuntime().addShutdownHook(new Thread(() -> cerrarSessionFactory())); // permite cerrar la session automaticamente
            } catch (Exception e) {
                System.err.println("Error al crear SessionFactory: " + e.getMessage());
            }
        }
        return sessionFactory;
    }

    public static List<Estudiante> listarTodos() {
        List<Estudiante> listaEstudiantes = new ArrayList<>();
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            listaEstudiantes = session.createQuery("FROM Estudiante").list();
        } catch (Exception e) {
            System.out.println("Error al listar todos los estudiantes." + e.getMessage());
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
            session = getSessionFactory().openSession();

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
     */
    public static RespuestaDTO agregarNuevo(Estudiante estudiante) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = getSessionFactory().openSession();

            // iniciar la transacción
            transaction = session.beginTransaction();

            // guardar la universidad
            session.save(estudiante);

            // confirmar la transacción
            transaction.commit();

            System.out.println("Estudiante agregado con éxito: " + estudiante);

            // configurar la respuesta de éxito
            respuesta.setSuccess(true);
            respuesta.setMessage("Estudiante agregada con éxito");
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
     * @param estudiante El estudiante que se va a eliminar.
     */
    public static RespuestaDTO eliminar(Estudiante estudiante) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = getSessionFactory().openSession();

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
     */
    public static RespuestaDTO modificar(Estudiante estudiante) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = getSessionFactory().openSession();

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
     * Cierra la SessionFactory para liberar todos los recursos de Hibernate.
     * Este método debe llamarse al finalizar la aplicación.
     */
    public static void cerrarSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("SessionFactory Estudiantes cerrado correctamente.");
        }
    }

}
