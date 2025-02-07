/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doctoradomatisse;

/**
 *
 * @author IMCG
 */
//librería proporcionada por Matisse (necesaria porque vamos a utilizar los
//objetos propios de Matisse, en lugar de los genéricos que nos
//proporcionaría una conexión mediante JDBC)
import com.matisse.MtDatabase;
import com.matisse.MtException;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //crea el objeto base de datos MtDatabase indicando la cadena de conexión
        //nombre del host "zailla" y base de datos "doctorado"
        //no se necesita usuario porque no se ha deficnido un control de acceso
        MtDatabase db = new MtDatabase("zailla", "doctorado");
        //mensaje para la Salida
        System.out.println("=========== connectFromMtDatabase ==========\n");
        try {
            //conecta con la base de datos
            db.open();
            //inicia una transacción (matisse gestiona todas las operaciones como transacciones)
            db.startTransaction();
            //insertar datos en registros
            insertarObjetos(db);
        } catch (MtException mte) {
            //mensaje para la Salida
            System.out.println("MtException : " + mte.getMessage());
        } finally {
            //confirma cualquier transacción en proceso
            if (db.isTransactionInProgress()) {
                db.commit();
            }
            //cierra la base de datos
            db.close();
        }
    }
    private static void insertarObjetos(MtDatabase db) {
        //crea objetos Departamento
        Departamento d1 = new Departamento(db);
        d1.setNombre("Bases de Datos");
        Departamento d2 = new Departamento(db);
        d2.setNombre("Lenguajes");
        // Crea objetos Tesis
        Tesis t1 = new Tesis(db);
        t1.setTitulo("Persistencia de objetos");
        t1.setTema("Bases de Objetos");
        Tesis t2 = new Tesis(db);
        t2.setTitulo("Bases de Datos Nativas XML");
        t2.setTema("Bases de Datos XML");
        Tesis t3 = new Tesis(db);
        t3.setTitulo("Mapeo Objeto-Relacional");
        t3.setTema("Bases de Datos");
        Tesis t4 = new Tesis(db);
        t4.setTitulo("Multiproceso en Java");
        t4.setTema("Lenguajes de Programación");
        // Crea un objeto Profesor
        Profesor p1 = new Profesor(db);
        p1.setNombre("Ana Martos Gil");
        p1.setEmail("ana.martos@universi.es");
        p1.setIngreso(1990);
        Profesor p2 = new Profesor(db);
        p2.setNombre("Isabel Ruz Granados");
        p2.setEmail("isabel.ruz@universi.es");
        p2.setIngreso(1986);
        Profesor p3 = new Profesor(db);
        p3.setNombre("Antonio Barea Navarro");
        p3.setEmail("antonioa.barea@universi.es");
        p3.setIngreso(1995);
        //establece relaciones entre Profesor y Departamentoanteriormente creados
        //Al establecer una relación no hace falta establecer la inversa*/
        p1.setPertenece(d1);
        p1.appendDirige(t1);
        p2.setPertenece(d1);
        p2.appendDirige(t2);
        p2.appendDirige(t3);
        p3.setPertenece(d2);
        p3.appendDirige(t4);

    }
}
