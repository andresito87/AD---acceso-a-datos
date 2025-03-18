package controladores;

import java.io.IOException;
import java.nio.file.Files;
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
import org.basex.io.out.ArrayOutput;
import org.basex.query.QueryException;
import org.basex.query.QueryIOException;
import org.basex.query.QueryProcessor;

/**
 *
 * @author andres
 */
public class TallerController {

    private static final String DATABASE_NAME = "tallerDB";

    public static void guardarVehiculos(Context context, String directorioSalida) {
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

            String result = new XQuery(query).execute(context);

            // Separar correctamente cada nodo
            String[] items = result.split("\n");

            int index = 1;
            for (String item : items) {
                if (!item.trim().isEmpty()) {  // Evitar líneas vacías
                    String fileName = directorioSalida + "/vehiculo" + index + ".txt";
                    Files.writeString(Paths.get(fileName), item.trim());
                    index++;
                }
            }

            System.out.println("Guardados " + (index - 1) + " vehículos");
        } catch (BaseXException ex) {
            System.err.println("Error procesando vehículos: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error accediendo/escribiendo en archivo " + directorioSalida + ": " + ex.getMessage());
        }
    }

    public static void guardarReparaciones(Context context, String directorioSalida) {
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

            String result = new XQuery(query).execute(context);

            // Separar correctamente cada nodo
            String[] items = result.split("\n");

            int index = 1;
            for (String item : items) {
                if (!item.trim().isEmpty()) {  // Evitar líneas vacías
                    String fileName = directorioSalida + "/reparacion" + index + ".txt";
                    Files.writeString(Paths.get(fileName), item.trim());
                    index++;
                }
            }

            System.out.println("Guardadas " + (index - 1) + " reparaciones");
        } catch (BaseXException ex) {
            System.err.println("Error procesando reparaciones: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error accediendo/escribiendo en archivo " + directorioSalida + ": " + ex.getMessage());
        }
    }

    static void guardarMarcas(Context contexto, String directorioSalida) {
        try {
            // Consulta para obtener cada marca por separado
            String query
                    = "for $v in collection('" + DATABASE_NAME + "')//vehiculo "
                    + "let $marca := $v/marca/text() "
                    + "return concat('Marca: ', $marca)";

            String result = new XQuery(query).execute(contexto);

            // Separar correctamente cada nodo
            String[] items = result.split("\n");

            int index = 1;
            for (String item : items) {
                if (!item.trim().isEmpty()) {  // Evitar líneas vacías
                    String fileName = directorioSalida + "/marca" + index + ".txt";
                    Files.writeString(Paths.get(fileName), item.trim());
                    index++;
                }
            }

            System.out.println("Guardadas " + (index - 1) + " marcas");
        } catch (BaseXException ex) {
            System.err.println("Error procesando marcas: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error accediendo/escribiendo en archivo " + directorioSalida + ": " + ex.getMessage());
        }
    }

    public static List<Vehiculo> obtenerTodosVehiculos(Context contexto) throws QueryException, QueryIOException {
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
        }

        return vehiculos;
    }

    public static List<Reparacion> obtenerTodasReparaciones(Context contexto) throws QueryException, QueryIOException {
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
        }

        return reparaciones;
    }

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

    private static Propietario extraerPropietario(String vehiculoXML) {
        // Extraer el resultado (nombre y apellidos)
        String nombre = extractValue(vehiculoXML, "nombre");
        String apellidos = extractValue(vehiculoXML, "apellidos");
        Propietario propietario = new Propietario(nombre, apellidos);

        return propietario;
    }

    private static Mecanico extraerMecanico(String reparacionXML) {
        // Extraer el resultado (nombre, apellidos y telefono)
        String nombre = extractValue(reparacionXML, "nombre");
        String apellidos = extractValue(reparacionXML, "apellidos");
        String telefono = extractValue(reparacionXML, "telefono");
        Mecanico mecanico = new Mecanico(nombre, apellidos, telefono);

        return mecanico;
    }

}
