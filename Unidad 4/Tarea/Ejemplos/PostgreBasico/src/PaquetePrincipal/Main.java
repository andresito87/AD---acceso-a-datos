package PaquetePrincipal;

/*
 * acceso a las librerías JDBC
 */
import java.sql.*;

/******************************************************************************
 * ilustra característica meramente relacionales de PostgreSQL mediante el
 * uso de comandos
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
    String url = "jdbc:postgresql://localhost:5432/anaconda";

    //conexión con la base de datos
    Connection conn = null;

    try {
      //abre la conexión con la base de datos a la que apunta el url
      //mediante la contraseña del usuario postgres
      conn = DriverManager.getConnection(url, "admin", "admin");

      //elimina las tablas (si existen)
      drop_Ejemplo(conn);

      //vuelve a crear las tablas de ejemplo
      crearTablas(conn);

      //inserta algunos regitros
      insertarRegistros(conn);

      //realiza alguna consultas
      consulta_Ejemplo1(conn);
      consulta_Ejemplo2(conn);

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
   * borra todas las tablas del ejemplo (si es que existen)
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void drop_Ejemplo(Connection conn) throws SQLException {

    //consulta SQL
    String consulta = "DROP TABLE IF EXISTS datos_meteo;"
            + "DROP TABLE IF EXISTS meses;"
            + "DROP TABLE IF EXISTS provincias;";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta
    sta.execute(consulta);

    //cierra el objeto auxiliar
    sta.close();
  }

  /****************************************************************************
   * creamos las tablas ('meses', 'provincias' y 'datos_meteo') y establecemos 
   * tanto las claves primarias ('mes_id', 'provincia_id' y 'datos_meteo_id'), 
   * como las foráneas (columnas 'mes_id' y 'provincia_id' de la tabla 
   * 'datos_meteo')
   * 
   * el tipo de las claves primarias "mes_id" y "provincia_id" es entero. El 
   * tipo de la clave primaria de la tabla 'datos_meteo' es serial (entero 
   * autoincremental en PostgreSQL). El resto son cadenas de longitud variable
   * , o numeric (el tipo numérico para cálculos exactos).
   * 
   * la tabla 'meses' tiene un relacion de uno a varios con la tabla 
   * 'datos_meteo', mediante el campo 'mes_id' de la segunda
   * 
   * la tabla 'provincias' tiene un relacion de uno a varios con la tabla 
   * 'datos_meteo', mediante el campo 'provincia_id' de la segunda
   * 
   * ambas relaciones implementan actualizaciones y eliminaciones en cascada
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void crearTablas(Connection conn) throws SQLException {

    //consulta
    String consulta = "CREATE TABLE meses("
            + "mes_id integer NOT NULL,"
            + "mes varchar(25),"
            + "CONSTRAINT mes_id PRIMARY KEY (mes_id )"
            + ");"
            
            + "CREATE TABLE provincias("
            + "provincia_id integer NOT NULL,"
            + "provincia varchar(25),"
            + "CONSTRAINT provincia_id PRIMARY KEY (provincia_id )"
            + ");"
            
            + "CREATE TABLE datos_meteo("
            + "datos_meteo_id serial NOT NULL,"
            + "provincia_id integer,"
            + "mes_id integer,"
            + "temp_min numeric,"
            + "temp_max numeric,"
            + "precipitaciones integer,"
            + "CONSTRAINT datos_meteo_id PRIMARY KEY (datos_meteo_id ),"
            + "CONSTRAINT mes_id FOREIGN KEY (mes_id) REFERENCES "
            + "meses (mes_id) MATCH SIMPLE ON UPDATE CASCADE ON "
            + "DELETE CASCADE,"
            + "CONSTRAINT provincia_id FOREIGN KEY (provincia_id) REFERENCES "
            + "provincias (provincia_id) MATCH SIMPLE ON UPDATE CASCADE "
            + "ON DELETE CASCADE"
            + ");";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta 
    sta.execute(consulta);

    //cierra el objeto auxiliar
    sta.close();
  }

  
  /***********************************************************************
   * insertamos algunos registros en las tablas (por suspuesto, respetando
   * la integridad de las relaciones)
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void insertarRegistros(Connection conn) throws SQLException {
    
    //consulta
    String consulta = "INSERT INTO meses (mes_id, mes) VALUES (1, 'enero');"
            + "INSERT INTO meses (mes_id, mes) VALUES (2, 'febrero');"
            + "INSERT INTO meses (mes_id, mes) VALUES (3, 'marzo');"
            + "INSERT INTO meses (mes_id, mes) VALUES (4, 'abril');"
            + "INSERT INTO meses (mes_id, mes) VALUES (5, 'mayo');"
            + "INSERT INTO meses (mes_id, mes) VALUES (6, 'junio');"
            + "INSERT INTO meses (mes_id, mes) VALUES (7, 'julio');"
            + "INSERT INTO meses (mes_id, mes) VALUES (8, 'agosto');"
            + "INSERT INTO meses (mes_id, mes) VALUES (9, 'septiembre');"
            + "INSERT INTO meses (mes_id, mes) VALUES (10, 'octubre');"
            + "INSERT INTO meses (mes_id, mes) VALUES (11, 'noviembre');"
            + "INSERT INTO meses (mes_id, mes) VALUES (12, 'diciembre');"
            
            + "INSERT INTO provincias (provincia_id, provincia) "
            + "VALUES (1, 'Barcelona');"
            + "INSERT INTO provincias (provincia_id, provincia) "
            + "VALUES (2, 'Madrid');"
            + "INSERT INTO provincias (provincia_id, provincia) "
            + "VALUES (3, 'Murcia');"
            + "INSERT INTO provincias (provincia_id, provincia) "
            + "VALUES (4, 'Valencia');"
            
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (1, 1, 4.4, 13.4, 41);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (1, 4, 8.5, 17.6, 49);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (1, 7, 18.6, 27.5, 20);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (1, 10, 12.6, 21.5, 91);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (2, 1, 0.3, 10.6, 33);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (2, 4, 5.4, 18.0, 39);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (2, 7, 16.1, 33.0, 11);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (2, 10, 8.3, 20.6, 39);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (3, 1, 5.12, 15.82, 38);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (3, 4, 9.3, 19.9, 25);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (3, 7, 19.9, 28.4, 6);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (3, 10, 18.7, 23.4, 14);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (4, 1, 5.0, 15.5, 38);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (4, 4, 9.4, 20.6, 38);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (4, 7, 19.8, 30.9, 14);"
            + "INSERT INTO datos_meteo (provincia_id, mes_id, temp_min, "
            + "temp_max, precipitaciones) VALUES (4, 10, 13.3, 23.4, 74);";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta 
    sta.execute(consulta);

    //cierra el objeto auxiliar
    sta.close();
  }

  /**************************************************************************
   * mostramos todos los registros de la tabla 'datos_meteo'. Tanto en bruto,
   * como mostrando los nombres de los meses y de la provincias. En el segundo
   * caso, redondeamos las columnas 'temp_min' y 'temp_max"
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void consulta_Ejemplo1(Connection conn) throws SQLException {
    
    //
    System.out.print("Todos los datos meteorológicos, tanto en bruto:\n");

    //consulta SQL
    String consulta = "SELECT * FROM datos_meteo";

    //comando auxiliar para ejecutar la consulta
    Statement sta = conn.createStatement();

    //ejecuta la consulta para que devuelva un conjunto de registros
    ResultSet res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //
    System.out.print("\ncomo con los valores tomados de las "
            + "tablas relacionadas:\n");

    //consulta SQL
    consulta = "SELECT "
            + "datos_meteo.datos_meteo_id, "
            + "provincias.provincia, "
            + "meses.mes, "
            + "round(datos_meteo.temp_min), "
            + "round(datos_meteo.temp_max), "
            + "datos_meteo.precipitaciones "
            + "FROM meses,provincias,datos_meteo "
            + "WHERE "
            + "meses.mes_id=datos_meteo.mes_id "
            + "AND "
            + "provincias.provincia_id=datos_meteo.provincia_id";

    //comando auxiliar para ejecutar la consulta
    sta = conn.createStatement();

    //reutiliza el comando para enviar la nueva consulta y que
    //devuelva un ResultSet
    res = sta.executeQuery(consulta);
    
    //imprime el resultado
    imprimir_ResultSet(res);

    //cierra los objetos auxiliares
    res.close();
    sta.close();
  }

  /*************************************************************************
   * calcula la temperatura media por mes de Murcia (provincia_id=3)
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void consulta_Ejemplo2(Connection conn) throws SQLException {

    //
    System.out.print("\nTemperatura media por mes de Murcia:\n");

    //consulta SQL
    String consulta = "SELECT "
            + "meses.mes, "
            + "ROUND((datos_meteo.temp_max+datos_meteo.temp_min)/2,2) "
            + "FROM meses,datos_meteo "
            + "WHERE "
            + "meses.mes_id=datos_meteo.mes_id "
            + "AND "
            + "datos_meteo.provincia_id=3";

    //comando SQL
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
   * cambia la clave principal del registro 'Valencia' en la tabla 'provincias'
   * (de 4 a 5), y comprueba la actualización en cascada de los registros 
   * relacionados en la tabla 'datos_meteo'
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void update_Ejemplo(Connection conn) throws SQLException {

    //
    System.out.print("\nCambia la clave principal de 'Valencia' en la tabla "
            + "'provincias'(de 4 a 5).");

    //consulta SQL
    String consulta = "UPDATE provincias "
            + "SET provincia_id=5 "
            + "WHERE "
            + "provincia_id=4";

    //comando SQL
    Statement sta = conn.createStatement();

    //ejecuta la consulta para que muestre el número de filas modificadas
    System.out.print("\nComo resultado, " + sta.executeUpdate(consulta)
            + " fila actualizada:\n");

    //nueva consulta SQL
    consulta = "SELECT * FROM provincias";

    //reutiliza el comando para enviar la nueva consulta y que
    //devuelva un ResultSet
    ResultSet res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //
    System.out.print("\ny muestra la actualización en cascada de los registros "
            + "relacionados en la tabla 'datos_meteo':\n");

    //nueva consulta SQL
    consulta = "SELECT * FROM datos_meteo WHERE provincia_id=5";

    //reutiliza el comando para enviar la nueva consulta y que
    //devuelva un ResultSet
    res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //cierra los objetos auxiliares
    res.close();
    sta.close();
  }

  /****************************************************************************
   * borra la provincia de 'Valencia' en la tabla 'provincias', y comprueba el
   * borrado en cascada de los registros relacionados en la tabla 'datos_meteo'
   * 
   * @param conn
   * @throws SQLException 
   */
  private static void delete_Ejemplo(Connection conn) throws SQLException {

    //
    System.out.print("\nBorra la provincia 'Valencia' en la tabla "
            + "'provincias':\n");

    //consulta SQL
    String consulta = "DELETE FROM provincias WHERE provincia = 'Valencia'";

    //comando SQL
    Statement sta = conn.createStatement();

    //ejecuta la consulta para que muestre las filas eliminadas
    System.out.print("\nComo resultado, " + sta.executeUpdate(consulta)
            + " fila eliminada:\n");

    //nueva consulta
    consulta = "SELECT * FROM provincias";

    //reutiliza el comando para enviar la nueva consulta y que
    //devuelva un ResultSet
    ResultSet res = sta.executeQuery(consulta);

    //imprime el resultado
    imprimir_ResultSet(res);

    //
    System.out.print("\ny muestra el borrado en cascada de los registros "
            + "relacionados en la tabla 'datos_meteo':\n");

    //nueva consulta
    consulta = "SELECT "
            + "datos_meteo.datos_meteo_id, "
            + "provincias.provincia, "
            + "meses.mes, "
            + "round(datos_meteo.temp_min), "
            + "round(datos_meteo.temp_max), "
            + "datos_meteo.precipitaciones "
            + "FROM meses,provincias,datos_meteo "
            + "WHERE "
            + "meses.mes_id=datos_meteo.mes_id "
            + "AND "
            + "provincias.provincia_id=datos_meteo.provincia_id";

    //reutiliza el comando para enviar la nueva consulta y que
    //devuelva un ResultSet
    res = sta.executeQuery(consulta);

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

      //línea en blanco
      System.out.println();
    }

  }
}
