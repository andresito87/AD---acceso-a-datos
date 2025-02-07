package PaquetePrincipal;

/*
 * acceso a las librerías JDBC
 */
import java.sql.*;

/**
 *
 * @author IMCG
 */
public class Main {

  /****************************************************************************
   * @param args
   * @throws SQLException 
   */
  public static void main(String[] args) throws SQLException {

    //cadena url de la base de datos anaconda en el servidor local
    String url = "jdbc:postgresql://localhost/anaconda";

    //conexión con la base de datos
    Connection conn = null;

    try {
      //abre la conexión con la base de datos a la que apunta el url
      //mediante la contraseña del usuario postgres
      conn = DriverManager.getConnection(url, "postgres", "1234");

      //elimina la tabla y los tipos de datos (si existen)
      drop_Ejemplo(conn);

      //crea los tipos de datos y sus constructores
      crear_Tipos(conn);

      //crea una tabla basada en los nuevos tipos de datos
      crear_Tabla(conn);

      //introduce algunos registros de ejemplo
      insertar_Registros(conn);

      //realiza alguna consultas
      consulta_Ejemplo1(conn);
      consulta_Ejemplo2(conn);
      consulta_Ejemplo3(conn);

      //modifica algunos registros
      update_Ejemplo(conn);

      //borra algunos registros
      delete_Ejemplo(conn);

    } catch (SQLException ex) {
      //imprime la excepción
      System.out.println(ex.toString());
    } finally {

      //cierra la conexión
      conn.close();
    }
  }

  /****************************************************************************
   * borra todos los objetos antes de volver a crearlos (el orden de borrado
   * es inverso al de su creación)
   *
   * @param conn
   * @throws SQLException
   */
  private static void drop_Ejemplo(Connection conn) throws SQLException {

    //consulta SQL
    String consulta = "DROP TABLE IF EXISTS viviendas_nuevas;"
            + "DROP TYPE IF EXISTS vivienda, direccion";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta
    sta.execute(consulta);

    //cierra el objeto auxiliar
    sta.close();
  }

  /****************************************************************************
   * crea el tipo de dato vivienda y el tipo de dato direccion
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void crear_Tipos(Connection conn) throws SQLException {

    //consulta SQL
    String consulta = "CREATE TYPE vivienda AS("
            + "planta integer, "
            + "metros_2 integer, "
            + "num_habitaciones integer, "
            + "num_bannios integer, "
            + "promotor varchar(25));"
            
            + "CREATE TYPE direccion AS("
            + "calle varchar(40), "
            + "ciudad varchar(25), "
            + "codigo_postal varchar(9));";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta
    sta.execute(consulta);

    //cierra el objeto auxiliar
    sta.close();
  }

  /****************************************************************************
   * crea una tabla basada en los nuevos tipos de datos, con una clave principal
   * de tipo serial
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void crear_Tabla(Connection conn) throws SQLException {

    //consulta SQL
    String consulta = "CREATE TABLE viviendas_nuevas("
            + "vivienda_id serial,"
            + "caracteristicas vivienda, "
            + "ubicacion direccion, "
            + "CONSTRAINT vivienda_id PRIMARY KEY (vivienda_id)"
            + ")";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta
    sta.execute(consulta);

    //cierra el objeto auxiliar
    sta.close();
  }

  /****************************************************************************
   * inserta unos cuantos registros de ejemplo
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void insertar_Registros(Connection conn) throws SQLException {

    //consulta SQL
    String consulta = "INSERT INTO viviendas_nuevas("
            + "caracteristicas,ubicacion) VALUES("
            + "ROW(3,69,2,1,'Construcciones Perico'),"
            + "ROW('Pepito López','Madrid','28013'));"
            
            + "INSERT INTO viviendas_nuevas("
            + "caracteristicas,ubicacion) VALUES("
            + "ROW(2,101,3,2,'Construcciones Julian'),"
            + "ROW('Juanita Pedroches','Murcia','30300'));"
            
            + "INSERT INTO viviendas_nuevas("
            + "caracteristicas,ubicacion) VALUES("
            + "ROW(10,194,5,2,'Construcciones Rodrigo'),"
            + "ROW('Evaristo Torcuato','Madrid','28013')"
            + ")";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta
    sta.execute(consulta);

    //cierra el objeto auxiliar
    sta.close();
  }

  /****************************************************************************
   * muestra todas las viviendas ofertadas
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void consulta_Ejemplo1(Connection conn) throws SQLException {

    //
    System.out.print("\nOferta de viviendas de nueva construcción:\n");

    //consulta SQL
    String consulta = "SELECT * FROM viviendas_nuevas";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta para que devuelva un conjunto de registros
    ResultSet res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //cierra los objetos auxiliares
    res.close();
    sta.close();
  }

  /****************************************************************************
   * muestra todas las viviendas ofertadas en 'Madrid'
   * 
   * @param conn
   * @throws SQLException
   */
  private static void consulta_Ejemplo2(Connection conn) throws SQLException {

    //
    System.out.print("\nOferta de viviendas de nueva construcción "
            + "en Madrid:\n");

    //consulta SQL
    String consulta = "SELECT * "
            + "FROM viviendas_nuevas "
            + "WHERE (viviendas_nuevas.ubicacion).ciudad='Madrid'";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta para que devuelva un conjunto de registros
    ResultSet res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //cierra los objetos auxiliares
    res.close();
    sta.close();
  }

  /****************************************************************************
   * muestra el promotor de 'Madrid' que oferta una vivienda nueva con 3 o más
   * habitaciones
   * 
   * @param conn
   * @throws SQLException
   */
  private static void consulta_Ejemplo3(Connection conn) throws SQLException {

    System.out.print("\nPromotores en Madrid de viviendas nuevas "
            + "con 3 habitaciones o más:\n");

    //consulta SQL
    String consulta = "SELECT "
            + "(viviendas_nuevas.caracteristicas).promotor AS prom "
            + "FROM viviendas_nuevas "
            + "WHERE (viviendas_nuevas.ubicacion).ciudad='Madrid' "
            + "AND (viviendas_nuevas.caracteristicas).num_habitaciones>2";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta para que devuelva un conjunto de registros
    ResultSet res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //cierra los objetos auxiliares
    res.close();
    sta.close();
  }

  /****************************************************************************
   * cambia el código postal de la vivienda ofertada en la calle 'Evaristo 
   * Torcuato'
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void update_Ejemplo(Connection conn) throws SQLException {

    //
    System.out.print("\nCambia el código postal de la vivienda ofertada "
            + "en la calle 'Evaristo Torcuato'");

    //consulta SQL
    String consulta = "UPDATE viviendas_nuevas "
            + "SET ubicacion.codigo_postal='28006' "
            + "WHERE "
            + "(viviendas_nuevas.ubicacion).calle='Evaristo Torcuato'";

    //comando SQL
    Statement sta = conn.createStatement();

    //ejecuta la consulta para que muestre las filas afectadas
    System.out.print("\nComo resultado, " + sta.executeUpdate(consulta)
            + " fila actualizada:\n");

    //nueva consulta SQL
    consulta = "SELECT * "
            + "FROM viviendas_nuevas "
            + "WHERE (viviendas_nuevas.ubicacion).calle='Evaristo Torcuato'";

    //reutiliza el comando para enviar la nueva consulta y que
    //devuelva un ResultSet
    ResultSet res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //cierra los objetos auxiliares
    res.close();
    sta.close();
  }

  /****************************************************************************
   * borra la vivienda ofertada en 'Murcia'
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void delete_Ejemplo(Connection conn) throws SQLException {

    //
    System.out.print("\nBorra la vivienda ofertadas en Murcia");

    //consulta SQL
    String consulta = "DELETE "
            + "FROM viviendas_nuevas "
            + "WHERE (viviendas_nuevas.ubicacion).ciudad='Murcia'";

    //comando SQL
    Statement sta = conn.createStatement();

    //ejecuta la consulta para que muestre el número de filas eliminadas
    System.out.print("\nComo resultado, " + sta.executeUpdate(consulta)
            + " fila eliminada:\n");

    //nueva consulta SQL
    consulta = "SELECT * "
            + "FROM viviendas_nuevas";

    //reutiliza el comando para enviar la nueva consulta y que
    //devuelva un ResultSet
    ResultSet res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //cierra los objetos auxiliares
    res.close();
    sta.close();
  }

  /****************************************************************************
   * imprime en la Salida el resultSet especificado, con los datos de cada
   * columna tabulados
   * 
   * @param resultSet
   * @throws SQLException 
   */
  private static void imprimir_ResultSet(ResultSet resultSet) throws
          SQLException {

    //número de columnas del resultset
    ResultSetMetaData metaDatos = resultSet.getMetaData();
    int columnas = metaDatos.getColumnCount();

    //mientras quedan registros por leer en el ResultSet
    while (resultSet.next()) {
      //imprime los registros
      for (int i = 1; i <= columnas; i++) {
        //seguidos de un tabulador
        System.out.print(resultSet.getString(i) + "\t");
      }

      //hueco entre registros
      System.out.println();
    }
    
  }
}
