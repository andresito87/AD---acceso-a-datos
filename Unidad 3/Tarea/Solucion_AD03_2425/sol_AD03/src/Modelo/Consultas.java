package Modelo;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sol_AD03.HibernateUtil;

public class Consultas {
    
    /* 
    * Método que agrega una universidad a la base de datos
    */
    public static boolean agregarUniversidad(Universidad nuevaUni) {
        Transaction transaction = null;
        boolean resul = false;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Guardar la universidad en la base de datos
            session.save(nuevaUni);
            transaction.commit(); 
            resul = true;
        } catch (Exception e) {
            if (transaction != null) {                
                transaction.rollback(); 
            }      
            System.out.println(e.getMessage());
        }
        return resul;
    }  
    /* 
    * Método que elimina una universidad de la base de datos
    */
    public static boolean eliminarUniversidad(int codigou) {
        Transaction transaction = null;
        boolean resul = false;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Obtener el objeto Universidad a través de su código
            Universidad universidadaborrar = (Universidad) session.get(Universidad.class, codigou); 
            // Borrar el objeto de la base de datos
            session.delete(universidadaborrar);
            transaction.commit();
            resul = true;
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } 
            System.out.println(e.getMessage());
        }     
        return resul;
    }      
    /* 
    * Método que devuelve una lista de objetos Universidad con todas las universidades de la base de datos
    */
    public static List<Universidad> obtenerUniversidades() {
        Transaction transaction = null;
        List<Universidad> universidades = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Consulta para obtener todas las universidades
            String hql = "FROM Universidad ORDER BY nombre";
            Query query = session.createQuery(hql);
            universidades = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } 
            System.out.println(e.getMessage());
        }
        return universidades;
    }
    /* 
    * Método que devuelve el número de estudiantes de una universidad de la que se tiene su código
    */
    public static long numeroEstudiantes(Integer coduniversidad) {
        Transaction transaction = null;
        long totalEstudiantes = 0;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            //consulta para contar los estudiantes de una Universidad    
            String hql = "SELECT COUNT(*) FROM Estudiante e WHERE e.universidad.codigo = :codigouniversidad";
            Query query = session.createQuery(hql);
            query.setParameter("codigouniversidad", coduniversidad);        
            List resultado = query.list();
            totalEstudiantes = (Long) resultado.get(0);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }   
            System.out.println(e.getMessage());
        }
        return totalEstudiantes;
    }
    /**
     * Método que devuelve el nombre de la Universidad cuyo código recibe cómo parámetro
     * @param cod
     * @return String
     */
    public static String buscarNombreUniversidad(int cod){
        Transaction transaction = null;
        List<Universidad> universidad = null;
        String nomUniversidad="";

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Consulta HQL
            String hql = "FROM Universidad WHERE codigo = " + cod;
            Query query = session.createQuery(hql);
            universidad = query.list();
            if (!universidad.isEmpty()) {
                for (Universidad u : universidad) {
                    nomUniversidad = u.getNombre();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return nomUniversidad;
    }
    /*
    * Método que devuelve la Universidad cuyo nombre se le ha pasado como parámetro
    */
    public static List<Universidad> buscarUniversidadNombre(String nomUni) {
        Transaction transaction = null;
        List<Universidad> universidades = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // consulta HQL
            String hql = "FROM Universidad WHERE nombre = :nUni";
            Query query = session.createQuery(hql);
            query.setParameter("nUni", nomUni);
            universidades = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());    
        }
        return universidades;
    }
    /* 
    * Método que agrega un estudiante a la base de datos
    */
    public static boolean agregarEstudiante(Estudiante nuevoAlumno) {
        Transaction transaction = null;
        boolean resul = false;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Guardar el estudiante en la base de datos
            session.save(nuevoAlumno);
            transaction.commit();
            resul = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } 
            System.out.println(e.getMessage());
        }     
        return resul;
    }
    /* 
    * Método que elimina un estudiante a la base de datos
    */
    public static boolean eliminarEstudiante(String dnie ) {
        Transaction transaction = null;
        boolean resul = false;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Obtiene el objeto Estudiante a través del dni
            Estudiante estudianteaborrar = (Estudiante) session.get(Estudiante.class, dnie);            
            // Borra el objeto de la base de datos
            session.delete(estudianteaborrar);
            transaction.commit();
            resul = true;            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }  
            System.out.println(e.getMessage());
        }  
        return resul;
    }
    /* 
    * Método que devuelve una lista de objetos Estudiantes con todos los estudiantes de la base de datos
    */
    public static List<Estudiante> obtenerEstudiantes() {
        Transaction transaction = null;
        List<Estudiante> estudiantes = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Consulta para obtener todos los estudiantes
            String hql = "FROM Estudiante";
            Query query = session.createQuery(hql);
            estudiantes = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return estudiantes;
    }
    /* 
    * Método que devuelve una lista de objetos Estudiantes con todos los estudiantes de la base de datos
    * ordenados de menor a mayor según el importe de su matrícula
    */
    public static List<Estudiante> obtenerEstudiantesOrdenadosPorMatricula() {
        Transaction transaction = null;
        List<Estudiante> estudiantes = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Consulta para obtener todos los estudiantes ordenados por el valor su atributo importe_matricula
            String hql = "FROM Estudiante ORDER BY importe_matricula";
            Query query = session.createQuery(hql);
            estudiantes = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }  
            System.out.println(e.getMessage());
        }
        return estudiantes;
    }
    /* 
    * Método que devuelve una lista de Estudiantes que cumplen que son de la provincia de Almería y que tienen beca
    */
    public static List<Estudiante> obtenerEstudiantesAlmeriaBecados() {
        Transaction transaction = null;
        List<Estudiante> estudiantes = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Consulta para obtener los estudiantes con las restricciones ser de Almeria y becados
            String hql = "FROM Estudiante e WHERE e.provincia LIKE :prov AND becado=true";
            Query query = session.createQuery(hql);
            query.setParameter("prov", "Almería");
            estudiantes = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return estudiantes;
    }
    /*
    * Método que modifica el importe de matricula de un estudiante.
    * Recibe como parámetros el dni del estudiante a modificar y el nuevo importe de matricula.
    */
    public static boolean modificarImporteMatricula(String dnim, float nuevoimportematricula){
        Transaction transaction = null;
        boolean resul = false;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Obtengo el objeto estudiante que tengo que modificar a través de su dni
            Estudiante estudianteamodificar = (Estudiante) session.get(Estudiante.class, dnim);
            // Modifico el valor de su atributo ImporteMatricula
            estudianteamodificar.setImporteMatricula(nuevoimportematricula);
            // Acutalizo los cambios en la base de datos
            session.update(estudianteamodificar);
            transaction.commit();
            resul=true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } 
            System.out.println(e.getMessage());
        }       
        return resul;
    }
    
    /**
     * Método que devuelve una lista con cada una de las Universidades y la suma de sus ingresos por matriculas de estudiantes
     * @return 
     */
    public static List<Object[]> totalMatriculasUniversidad(){
        Transaction transaction = null;
        List<Object[]> resultados = null;
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Crear la consulta HQL con join y suma de importe matriculas
            // la relación entre estas dos tablas se maneja mediante el atributo estudiantes 
            // en la clase Universidad
            String hql = "SELECT e, SUM(f.importeMatricula) " +
                         "FROM Universidad e " +
                         "JOIN e.estudiantes f " +
                         "GROUP BY e " +
                         "ORDER BY SUM(f.importeMatricula) DESC";
            Query query = session.createQuery(hql);
            // Obtener los resultados de la consulta
            resultados = query.list();
            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            } 
            System.out.println(e.getMessage());
        } 
        return resultados;
    }
    
}
