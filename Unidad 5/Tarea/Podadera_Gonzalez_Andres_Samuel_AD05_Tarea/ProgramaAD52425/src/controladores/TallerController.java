package controladores;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelos.Mecanico;
import modelos.Propietario;
import modelos.Reparacion;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.XQuery;
import modelos.Vehiculo;
import org.basex.core.cmd.Export;
import org.basex.io.out.ArrayOutput;
import org.basex.query.QueryException;
import org.basex.query.QueryIOException;
import org.basex.query.QueryProcessor;

/**
 * Controlador para gestionar las operaciones del taller mecánico. Proporciona
 * métodos para guardar información de vehículos, reparaciones y marcas, además
 * de recuperar datos de la base de datos.
 *
 * @author andres
 */
public class TallerController {

    private static final String DATABASE_NAME = "tallerDB";
    private static final String DIRECTORIO_SALIDA = "AD52425";

    /**
     * Guarda la información de los vehículos en archivos de texto.
     *
     * @param contexto Contexto de BaseX para la ejecución de consultas.
     * @return {@code true} si la operación se realizó con éxito, {@code false}
     * en caso de error.
     */
    public static boolean guardarVehiculos(Context contexto) {
        try {
            // Consulta para obtener cada vehiculo por separado
            String query
                    = "for $v in collection('" + DATABASE_NAME + "')//vehiculo "
                    + "let $nombre := $v/propietario/nombre/text() "
                    + "let $apellidos := $v/propietario/apellidos/text() "
                    + "let $matricula := $v/matricula/text() "
                    + "let $marca := $v/marca/text() "
                    + "let $modelo := $v/modelo/text() "
                    + "let $kilometros := $v/kilometros/text() "
                    + "return concat('Propietario/a: ', $nombre, ' ', $apellidos, "
                    + "' | Matrícula: ', $matricula, "
                    + "' | Marca: ', $marca, "
                    + "' | Modelo: ', $modelo, "
                    + "' | Kilómetros: ', $kilometros)";

            String result = new XQuery(query).execute(contexto);

            // Separar correctamente cada nodo
            String[] items = result.split("\n");

            Path vehiculosPath = Paths.get(DIRECTORIO_SALIDA, "/vehiculos");

            // Crear todos los directorios necesarios si no existen
            Files.createDirectories(vehiculosPath);

            int index = 1;
            for (String item : items) {
                if (!item.trim().isEmpty()) {  // Evitar líneas vacías
                    String fileName = vehiculosPath + "/vehiculo" + index + ".txt";
                    Files.writeString(Paths.get(fileName), item.trim());
                    index++;
                }
            }

            System.out.println("Guardados " + (index - 1) + " vehículos");
            return true;
        } catch (BaseXException ex) {
            System.err.println("Error procesando vehículos: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error accediendo/escribiendo en archivo " + DIRECTORIO_SALIDA + ": " + ex.getMessage());
        }

        return false;
    }

    /**
     * Guarda la información de las reparaciones en archivos de texto.
     *
     * @param contexto Contexto de BaseX para la ejecución de consultas.
     * @return {@code true} si la operación se realizó con éxito, {@code false}
     * en caso de error.
     */
    public static boolean guardarReparaciones(Context contexto) {
        try {
            // Consulta para obtener cada reparacion por separado
            String query
                    = "for $r in collection('" + DATABASE_NAME + "')//reparacion "
                    + "let $matricula := $r/preceding-sibling::matricula/text() " //preceding-sibling accede al nodo hermano anterior
                    + "let $inicio := $r/inicio/text() "
                    + "let $fin := if ($r/fin) then $r/fin/text() else 'Por determinar' " //compruebo si tiene fecha fin y pongo texto por defecto sino la tiene
                    + "let $telefono := $r/mecanico/telefono/text() "
                    + "let $nombre := $r/mecanico/nombre/text() "
                    + "let $apellidos := $r/mecanico/apellidos/text() "
                    + "return concat('Matrícula: ', $matricula, "
                    + "' | Inicio: ', $inicio, "
                    + "' | Fin: ', $fin, "
                    + "' | Teléfono: ', $telefono, "
                    + "' | Mecánico/a: ', $nombre, ' ', $apellidos)";

            String result = new XQuery(query).execute(contexto);

            // Separar correctamente cada nodo
            String[] items = result.split("\n");

            Path reparacionesPath = Paths.get(DIRECTORIO_SALIDA, "/reparaciones");

            // Crear todos los directorios necesarios si no existen
            Files.createDirectories(reparacionesPath);

            int index = 1;
            for (String item : items) {
                if (!item.trim().isEmpty()) {  // Evitar líneas vacías
                    String fileName = reparacionesPath + "/reparacion" + index + ".txt";
                    Files.writeString(Paths.get(fileName), item.trim());
                    index++;
                }
            }

            System.out.println("Guardadas " + (index - 1) + " reparaciones");
            return true;
        } catch (BaseXException ex) {
            System.err.println("Error procesando reparaciones: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error accediendo/escribiendo en archivo " + DIRECTORIO_SALIDA + ": " + ex.getMessage());
        }
        return false;
    }

    /**
     * Guarda la información de las marcas en archivos de texto.
     *
     * @param contexto Contexto de BaseX para la ejecución de consultas.
     * @return {@code true} si la operación se realizó con éxito, {@code false}
     * en caso de error.
     */
    public static boolean guardarMarcas(Context contexto) {
        try {
            // Consulta para obtener cada marca por separado
            String query
                    = "for $v in collection('" + DATABASE_NAME + "')//vehiculo "
                    + "let $marca := $v/marca/text() "
                    + "return concat('Marca: ', $marca)";

            String result = new XQuery(query).execute(contexto);

            // Separar correctamente cada nodo
            String[] items = result.split("\n");

            Path marcasPath = Paths.get(DIRECTORIO_SALIDA, "/marcas");

            // Crear todos los directorios necesarios si no existen
            Files.createDirectories(marcasPath);

            int index = 1;
            for (String item : items) {
                if (!item.trim().isEmpty()) {  // Evitar líneas vacías
                    String fileName = marcasPath + "/marca" + index + ".txt";
                    Files.writeString(Paths.get(fileName), item.trim());
                    index++;
                }
            }

            System.out.println("Guardadas " + (index - 1) + " marcas");
            return true;
        } catch (BaseXException ex) {
            System.err.println("Error procesando marcas: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error accediendo/escribiendo en archivo " + DIRECTORIO_SALIDA + ": " + ex.getMessage());
        }

        return false;
    }

    /**
     * Obtiene una lista de todos los vehículos almacenados en la base de datos
     * XML.
     *
     * @param contexto el contexto de BaseX utilizado para ejecutar la consulta
     * XQuery.
     * @return una lista de objetos {@code Vehiculo} representando los vehículos
     * obtenidos.
     */
    public static List<Vehiculo> obtenerTodosVehiculos(Context contexto) {
        List<Vehiculo> vehiculos = new ArrayList<>();

        try {
            // Consulta FLWOR para obtener todos los vehículos
            String query
                    = "for $vehiculo in //vehiculo\n"
                    + "return <vehiculo>\n"
                    + "  <matricula>{ $vehiculo/matricula/text() }</matricula>\n"
                    + "  <marca>{ $vehiculo/marca/text() }</marca>\n"
                    + "  <modelo>{ $vehiculo/modelo/text() }</modelo>\n"
                    + "  <kilometros>{ $vehiculo/kilometros/text() }</kilometros>\n"
                    + "  <afabricacion>{ string($vehiculo/@afabricacion) }</afabricacion>\n"
                    + "  <propietario>\n"
                    + "    <nombre>{ $vehiculo/propietario/nombre/text() }</nombre>\n"
                    + "    <apellidos>{ $vehiculo/propietario/apellidos/text() }</apellidos>\n"
                    + "  </propietario>\n"
                    + "</vehiculo>";

            // Ejecutar la consulta
            QueryProcessor qp = new QueryProcessor(query, contexto);
            ArrayOutput resultado = qp.value().serialize();
            String[] vehiculosXML = resultado.toString().split("</vehiculo>");
            for (String vehiculoXML : vehiculosXML) {
                if (vehiculoXML.contains("<vehiculo>")) {
                    String matricula = extractValue(vehiculoXML, "matricula");
                    String marca = extractValue(vehiculoXML, "marca");
                    String modelo = extractValue(vehiculoXML, "modelo");
                    int año = Integer.parseInt(extractValue(vehiculoXML, "afabricacion"));
                    Propietario propietario = extraerPropietario(vehiculoXML);
                    int kilometraje = Integer.parseInt(extractValue(vehiculoXML, "kilometros"));

                    Vehiculo vehiculo = new Vehiculo(matricula, año, propietario, marca, modelo, kilometraje);
                    vehiculos.add(vehiculo);
                }
            }

            // Cerrar el procesador de consultas
            qp.close();

        } catch (NumberFormatException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (QueryIOException | QueryException ex) {
            System.out.println("Error en las consultas XQuery" + ex.getMessage());
        }

        return vehiculos;
    }

    /**
     * Obtiene una lista de todas las reparaciones almacenadas en la base de
     * datos XML.
     *
     * @param contexto el contexto de BaseX utilizado para ejecutar la consulta
     * XQuery.
     * @return una lista de objetos {@code Reparacion} representando las
     * reparaciones obtenidas.
     */
    public static List<Reparacion> obtenerTodasReparaciones(Context contexto) {
        List<Reparacion> reparaciones = new ArrayList<>();

        try {
            // Consulta FLWOR para obtener todas la reparaciones
            String query
                    = "for $reparacion in //entrada\n"
                    + "return <reparacion>\n"
                    + "  <matricula>{ $reparacion/matricula/text() }</matricula>\n"
                    + "  <inicio>{ $reparacion/reparacion/inicio/text() }</inicio>\n"
                    + "  <fin>{ $reparacion/reparacion/fin/text() }</fin>\n"
                    + "  <mecanico>\n"
                    + "    <nombre>{ $reparacion/reparacion/mecanico/nombre/text() }</nombre>\n"
                    + "    <apellidos>{ $reparacion/reparacion/mecanico/apellidos/text() }</apellidos>\n"
                    + "    <telefono>{ $reparacion/reparacion/mecanico/telefono/text() }</telefono>\n"
                    + "  </mecanico>\n"
                    + "</reparacion>";

            // Ejecutar la consulta
            QueryProcessor qp = new QueryProcessor(query, contexto);
            ArrayOutput resultado = qp.value().serialize();
            String[] reparacionesXML = resultado.toString().split("</reparacion>");
            for (String reparacionXML : reparacionesXML) {
                if (reparacionXML.contains("<reparacion>")) {
                    String matricula = extractValue(reparacionXML, "matricula");
                    String inicio = extractValue(reparacionXML, "inicio");
                    String fin = extractValue(reparacionXML, "fin");
                    Mecanico mecanico = extraerMecanico(reparacionXML);

                    Reparacion reparacion;
                    if (fin != null) {
                        reparacion = new Reparacion(matricula, LocalDate.parse(inicio), LocalDate.parse(fin), mecanico);
                    } else {
                        reparacion = new Reparacion(matricula, LocalDate.parse(inicio), null, mecanico);
                    }

                    reparaciones.add(reparacion);
                }
            }

            // Cerrar el procesador de consultas
            qp.close();

        } catch (NumberFormatException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (QueryException | QueryIOException ex) {
            System.out.println("Error en las consultas XQuery" + ex.getMessage());
        }

        return reparaciones;
    }

    /**
     * Extrae el valor de un campo dentro de un fragmento XML, dado el nombre de
     * la etiqueta. Este método busca la etiqueta dentro del XML y devuelve el
     * texto contenido entre las etiquetas de apertura y cierre.
     *
     * @param xml El fragmento XML del cual se extraerá el valor de la etiqueta.
     * @param tag El nombre de la etiqueta cuyo valor se desea extraer.
     * @return El valor extraído entre las etiquetas, o {@code null} si la
     * etiqueta no se encuentra o no tiene contenido.
     */
    private static String extractValue(String xml, String tag) {
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        int startIndex = xml.indexOf(startTag) + startTag.length();
        int endIndex = xml.indexOf(endTag);
        if (endIndex != -1) {
            return xml.substring(startIndex, endIndex).trim();
        }
        return null;
    }

    /**
     * Extrae la información del propietario de un vehículo a partir de un
     * fragmento XML. Este método extrae el nombre y los apellidos del
     * propietario desde las etiquetas correspondientes en el XML y crea un
     * objeto {@code Propietario} con esa información.
     *
     * @param vehiculoXML El fragmento XML que contiene los datos del vehículo y
     * del propietario. Se espera que el XML contenga las etiquetas <nombre> y
     * <apellidos> para poder extraer la información del propietario.
     *
     * @return Un objeto {@code Propietario} que contiene el nombre y los
     * apellidos extraídos del XML. Si las etiquetas correspondientes no están
     * presentes, se retornará un objeto {@code Propietario} con valores nulos o
     * vacíos, según se implemente.
     */
    private static Propietario extraerPropietario(String vehiculoXML) {
        // Extraer el resultado (nombre y apellidos)
        String nombre = extractValue(vehiculoXML, "nombre");
        String apellidos = extractValue(vehiculoXML, "apellidos");
        Propietario propietario = new Propietario(nombre, apellidos);

        return propietario;
    }

    /**
     * Extrae la información del mecánico a partir de un fragmento XML de una
     * reparación. Este método extrae el nombre, los apellidos y el teléfono del
     * mecánico desde las etiquetas correspondientes en el XML y crea un objeto
     * {@code Mecanico} con esa información.
     *
     * @param reparacionXML El fragmento XML que contiene los datos de la
     * reparación y del mecánico. Se espera que el XML contenga las etiquetas
     * <nombre>, <apellidos> y
     * <telefono> para poder extraer la información del mecánico.
     *
     * @return Un objeto {@code Mecanico} que contiene el nombre, los apellidos
     * y el teléfono extraídos del XML. Si las etiquetas correspondientes no
     * están presentes, se retornará un objeto {@code Mecanico} con valores
     * nulos o vacíos, según se implemente.
     */
    private static Mecanico extraerMecanico(String reparacionXML) {
        // Extraer el resultado (nombre, apellidos y telefono)
        String nombre = extractValue(reparacionXML, "nombre");
        String apellidos = extractValue(reparacionXML, "apellidos");
        String telefono = extractValue(reparacionXML, "telefono");
        Mecanico mecanico = new Mecanico(nombre, apellidos, telefono);

        return mecanico;
    }

    /**
     * Obtiene los vehículos del año 2024 desde la base de datos XML mediante
     * una consulta FLWOR. Este método carga la consulta desde un archivo XQuery
     * y la ejecuta en el contexto proporcionado. Si la consulta es exitosa,
     * devuelve el resultado en formato de cadena.
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar la consulta
     * XQuery. Este contexto debe estar configurado adecuadamente para
     * interactuar con la base de datos XML.
     *
     * @return Un {@code String} con el resultado de la consulta FLWOR que
     * contiene los vehículos del 2024. Si ocurre un error durante la ejecución,
     * se retorna una cadena vacía.
     */
    public static String obtenerVehiculos2024(Context contexto) {
        String resultado = "";
        try {
            // Consulta FLWOR para obtener todos los vehículos del 2024
            // Cargar la consulta desde el archivo
            String query = new String(Files.readAllBytes(Paths.get("Consultas_XQuery/Ejercicio2-1.xq")));

            resultado = new XQuery(query).execute(contexto);

        } catch (BaseXException ex) {
            System.err.println("Error obteniendo vehículos del 2024: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida: " + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Obtiene los vehículos con más de 75,000 kilómetros desde la base de datos
     * XML mediante una consulta FLWOR. Este método carga la consulta desde un
     * archivo XQuery y la ejecuta en el contexto proporcionado. Si la consulta
     * es exitosa, devuelve el resultado en formato de cadena.
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar la consulta
     * XQuery. Debe estar configurado correctamente para acceder a la base de
     * datos XML.
     *
     * @return Un {@code String} con el resultado de la consulta FLWOR que
     * contiene los vehículos con más de 75,000 kilómetros. Si ocurre un error
     * durante la ejecución, se retorna una cadena vacía.
     */
    public static String obtenerVehiculosCon75000(Context contexto) {
        String resultado = "";
        try {
            // Consulta FLWOR para obtener todos los vehículos con mas de 75000 kilometros
            // Cargar la consulta desde el archivo
            String query = new String(Files.readAllBytes(Paths.get("Consultas_XQuery/Ejercicio2-2.xq")));

            resultado = new XQuery(query).execute(contexto);

        } catch (BaseXException ex) {
            System.err.println("Error obteniendo vehículos con más de 75000: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida: " + ex.getMessage());
        }

        return resultado;
    }

    /**
     * Obtiene una lista de propietarios que poseen un vehículo de modelo "Golf"
     * desde la base de datos XML, ejecutando una consulta FLWOR almacenada en
     * un archivo XQuery.
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar la consulta
     * XQuery. Debe estar correctamente configurado para acceder a la base de
     * datos XML.
     *
     * @return Un {@code String} con el resultado de la consulta FLWOR, que
     * contiene la información de los propietarios que tienen un vehículo modelo
     * "Golf". Si ocurre un error durante la ejecución, se retorna una cadena
     * vacía.
     */
    public static String obtenerPropietariosConGolf(Context contexto) {
        String resultado = "";
        try {
            // Consulta FLWOR para obtener todos los propietarios que tienen un golf
            // Cargar la consulta desde el archivo
            String query = new String(Files.readAllBytes(Paths.get("Consultas_XQuery/Ejercicio2-3.xq")));

            resultado = new XQuery(query).execute(contexto);

        } catch (BaseXException ex) {
            System.err.println("Error obteniendo propietarios con un Golf: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida: " + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Obtiene una lista de todos los mecánicos almacenados en la base de datos
     * XML, ejecutando una consulta FLWOR definida en un archivo XQuery.
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar la consulta
     * XQuery. Debe estar correctamente configurado para acceder a la base de
     * datos XML.
     *
     * @return Un {@code String} con el resultado de la consulta FLWOR, que
     * contiene la información de los mecánicos. Si ocurre un error durante la
     * ejecución, se retorna una cadena vacía.
     */
    public static String obtenerMecanicos(Context contexto) {
        String resultado = "";
        try {
            // Consulta FLWOR para obtener todos los mecánicos
            // Cargar la consulta desde el archivo
            String query = new String(Files.readAllBytes(Paths.get("Consultas_XQuery/Ejercicio2-4.xq")));

            resultado = new XQuery(query).execute(contexto);

        } catch (BaseXException ex) {
            System.err.println("Error obteniendo los mecánicos: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida: " + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Modifica los kilómetros del vehículo con matrícula "6666FFF" en la base
     * de datos XML, ejecutando una consulta XQuery definida en un archivo
     * externo.
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar la consulta
     * XQuery. Debe estar correctamente configurado para acceder a la base de
     * datos XML.
     *
     * @return {@code true} si la modificación se realiza con éxito,
     * {@code false} si ocurre un error.
     */
    public static boolean modificarKilometrosDe6666FFF(Context contexto) {
        try {
            // Consulta para modificar los kilometros del vehiculo con matrícula 6666FFF
            // Cargar la consulta desde el archivo
            String query = new String(Files.readAllBytes(Paths.get("Consultas_XQuery/Ejercicio3-1.xq")));

            new XQuery(query).execute(contexto);

            return true;

        } catch (BaseXException ex) {
            System.err.println("Error modificando los kilómetros: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Modifica el nodo "kilómetros" de todos los vehículos en la base de datos
     * XML, reemplazándolo por "km". Luego, recupera los valores actualizados y
     * guarda los cambios.
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar las
     * consultas XQuery. Debe estar correctamente configurado para acceder a la
     * base de datos XML.
     *
     * @return Una cadena con los valores actualizados del nodo "km" de todos
     * los vehículos. En caso de error, devuelve una cadena vacía.
     */
    public static String modificarNodoKilometrosAKm(Context contexto) {
        String resultado = "";
        try {
            // Consulta para reemplazar el nodo kilómetros por km de todos los vehículos
            // Cargar la consulta desde el archivo
            String query = new String(Files.readAllBytes(Paths.get("Consultas_XQuery/Ejercicio3-2.xq")));

            new XQuery(query).execute(contexto);

            query = "for $vehiculo in //vehiculo\n"
                    + "return $vehiculo/kms";

            resultado = new XQuery(query).execute(contexto);

            // Guardamos los cambios en el fichero xml
            guardarCambiosEnXML(contexto);

        } catch (BaseXException ex) {
            System.err.println("Error procesando nodos kilómetro: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida: " + ex.getMessage());
        }
        return resultado;
    }

    /**
     * Modifica el nodo "km" de todos los vehículos en la base de datos XML,
     * reemplazándolo por "kilometros".
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar la consulta
     * XQuery. Debe estar correctamente configurado para acceder a la base de
     * datos XML.
     */
    public static void modificarNodoKmAKilometros(Context contexto) {
        try {
            String query
                    = "for $v in //vehiculo\n"
                    + "let $kilometros := $v/kms/text()\n"
                    + "let $nodoKilometro := $v/kms\n"
                    + "return replace node $nodoKilometro with <kilometros>{$kilometros}</kilometros>";

            // Ejecutar la consulta de actualización
            new XQuery(query).execute(contexto);

        } catch (BaseXException ex) {
            System.err.println("Error procesando kilómetros: " + ex.getMessage());
        }
    }

    /**
     * Inserta una nueva reparación en la base de datos XML utilizando una
     * consulta XQuery. La consulta se carga desde un archivo externo y se
     * ejecuta en el contexto de BaseX. Luego, se guardan los cambios en el
     * archivo XML.
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar la consulta
     * XQuery.
     * @return `true` si la inserción y el guardado en XML fueron exitosos,
     * `false` en caso de error.
     */
    public static boolean insertarNuevaReparacion(Context contexto) {
        try {
            // Consulta para modificar los kilometros del vehiculo con matrícula 6666FFF
            // Cargar la consulta desde el archivo
            String query = new String(Files.readAllBytes(Paths.get("Consultas_XQuery/Ejercicio3-3.xq")));

            new XQuery(query).execute(contexto);

            // Guardamos los cambios en el xml
            boolean estaGuardado = guardarCambiosEnXML(contexto);

            return estaGuardado;

        } catch (BaseXException ex) {
            System.err.println("Error insertando una nueva reparación: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error de entrada/salida: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Guarda los cambios realizados en la base de datos XML exportándolos a un
     * archivo en el directorio de salida.
     *
     * @param contexto El contexto de BaseX utilizado para ejecutar la
     * exportación de datos.
     * @return `true` si la exportación del XML fue exitosa, `false` en caso de
     * error.
     */
    private static boolean guardarCambiosEnXML(Context contexto) {
        try {
            // Guardar la base de datos en el archivo XML
            new Export(DIRECTORIO_SALIDA + "/Taller_Modificado").execute(contexto);

            return true;
        } catch (BaseXException ex) {
            System.out.println("Error al guardar los cambios en el archivo 'tallerModificado.xml'");
        }

        return false;
    }

}
