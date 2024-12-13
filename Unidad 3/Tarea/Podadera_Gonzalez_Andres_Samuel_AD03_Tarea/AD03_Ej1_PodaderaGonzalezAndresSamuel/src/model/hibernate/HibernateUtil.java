package model.hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration()
                    .configure(
                            HibernateUtil.class.getClassLoader()
                                    .getResource("model/hibernate/hibernate.cfg.xml"))  // agrego archivo de config hibernate
                    .buildSessionFactory();
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> cerrarSessionFactory())); // permite cerrar la session automaticamente
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * Cierra la SessionFactory para liberar todos los recursos de Hibernate.
     * Este método debe llamarse al finalizar la aplicación.
     */
    public static void cerrarSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("SessionFactory cerrado correctamente.");
        }
    }
}
