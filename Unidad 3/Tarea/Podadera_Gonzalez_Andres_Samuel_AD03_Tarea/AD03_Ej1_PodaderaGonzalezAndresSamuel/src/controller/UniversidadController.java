package controller;

import java.util.ArrayList;
import java.util.List;
import model.dto.RespuestaDTO;
import model.entity.Estudiante;
import model.entity.Universidad;
import model.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author ANDRES SAMUEL PODADERA GONZALEZ
 */
public class UniversidadController {

    /**
     * Obtiene una lista con todas las universidades.
     *
     * @return Lista de universidades o lista vacía si ocurre un error.
     */
    public static List<Universidad> listarTodas() {
        List<Universidad> listaUniversidades = new ArrayList<>();
        Session session = null; // Declarar la sesión fuera del try-catch
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            listaUniversidades = session.createQuery("FROM Universidad").list();
        } catch (Exception e) {
            System.err.println("Error al obtener la lista de universidades: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close(); // Asegurar que la sesión se cierre siempre
            }
        }
        return listaUniversidades;
    }

    /**
     * Inserta una nueva Universidad en la base de datos.
     *
     * @param universidad La universidad que se va a insertar.
     */
    public static RespuestaDTO agregarNueva(Universidad universidad) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // iniciar la transacción
            transaction = session.beginTransaction();

            // guardar la universidad
            session.save(universidad);

            // confirmar la transacción
            transaction.commit();

            System.out.println("Universidad agregada con éxito: " + universidad);

            // configurar la respuesta de éxito
            respuesta.setSuccess(true);
            respuesta.setMessage("Universidad agregada con éxito");
            respuesta.setData(universidad); // devolvemos la universidad agregada

        } catch (ConstraintViolationException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // revertir la transacción en caso de error
            }

            System.err.println("Clave primaria duplicada: " + e.getMessage());

            // configurar la respuesta de error específica para clave duplicada
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo agregar la universidad. El código ya existe en la base de datos.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // revertir la transacción en caso de error
            }

            System.err.println("Error al insertar la universidad: " + e.getMessage());

            // configurar la respuesta de error general
            respuesta.setSuccess(false);
            respuesta.setMessage("Error al insertar la universidad: " + e.getMessage());
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
     * Elimina una Universidad de la base de datos.
     *
     * @param codigoUniversidad El codigo de la universidad que se va a eliminar
     */
    public static RespuestaDTO eliminar(String codigoUniversidad) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // iniciar la transacción
            transaction = session.beginTransaction();

            // comprobar si la universidad tiene estudiantes
            List<Estudiante> estudiantes = EstudianteController.listarPorCodigoUniversidad(Integer.parseInt(codigoUniversidad));

            if (!estudiantes.isEmpty()) {

                // si tiene estudiantes, cancelar la operación
                respuesta.setSuccess(false);
                respuesta.setMessage("No se puede eliminar la universidad porque tiene " + estudiantes.size() + " estudiantes matriculados.");

            } else {
                // obtego la universidad
                Universidad universidad= UniversidadController.obtener(codigoUniversidad);

                // eliminar la universidad
                session.delete(universidad);

                // confirmar la transacción
                transaction.commit();

                // configurar respuesta de éxito
                respuesta.setSuccess(true);
                respuesta.setMessage("Universidad eliminada con éxito.");
                respuesta.setData(universidad); // retorna la universidad eliminada

            }
        } catch (Exception e) {
            // revertir la transacción si se produjo un error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            // configurar la respuesta de error
            respuesta.setSuccess(false);
            respuesta.setMessage("Error al eliminar la universidad: " + e.getMessage());
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
     * Modifica una Universidad de la base de datos.
     *
     * @param universidad La universidad que se va a modificar.
     */
    public static RespuestaDTO modificar(Universidad universidad) {
        Session session = null;
        Transaction transaction = null;
        RespuestaDTO respuesta = new RespuestaDTO(false, "");

        try {
            // abrir la sesión
            session = HibernateUtil.getSessionFactory().openSession();

            // iniciar la transacción
            transaction = session.beginTransaction();

            // modificar la entidad (update)
            session.update(universidad);

            // confirmar la transacción
            transaction.commit();
            System.out.println("Universidad modificada con éxito: " + universidad);

            // configurar la respuesta de éxito
            respuesta.setSuccess(true);
            respuesta.setMessage("Universidad modificada con éxito.");
            respuesta.setData(universidad); // devolvemos la universidad modificada

        } catch (ConstraintViolationException e) {
            // revertir la transacción si hay algún error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Clave primaria duplicada: " + e.getMessage());

            // configurar la respuesta de error específica para clave duplicada
            respuesta.setSuccess(false);
            respuesta.setMessage("No se pudo modificar la universidad. El código ya existe en la base de datos.");
        } catch (Exception e) {
            // revertir la transacción si hay algún error
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al modificar la universidad: " + e.getMessage());

            // configurar la respuesta de error general
            respuesta.setSuccess(false);
            respuesta.setMessage("Error al modificar la universidad: " + e.getMessage());
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
     * @param codigoUniveridad Codigo de la universidad que va a devolver.
     */
    public static Universidad obtener(String codigoUniversidad) {
        Session session = null;
        Universidad universidad = null;

        try {
            // Abrir la sesión de Hibernate
            session = HibernateUtil.getSessionFactory().openSession();

            // Obtener la universidad usando el código proporcionado
            universidad = (Universidad)session.get(Universidad.class, Integer.parseInt(codigoUniversidad));

            // Validar si la universidad existe
            if (universidad == null) {
                System.err.println("No se encontró la universidad con el código: " + codigoUniversidad);
            } else {
                System.out.println("Universidad encontrada: " + universidad);
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

        return universidad;
    }

}
