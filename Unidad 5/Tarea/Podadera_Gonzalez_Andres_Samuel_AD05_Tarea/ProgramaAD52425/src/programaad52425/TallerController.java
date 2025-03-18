package programaad52425;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.XQuery;

/**
 *
 * @author andres
 */
public class TallerController {

    private static final String databaseName = "tallerDB";

    public static void guardarVehiculos(Context context, String directorioSalida) {
        try {
            // Consulta para obtener cada vehiculo por separado
            String query
                    = "for $v in collection('" + databaseName + "')//vehiculo "
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
                    = "for $r in collection('" + databaseName + "')//reparacion "
                    + "let $matricula := $r/preceding-sibling::matricula/text() " //preceding-sibling accede al nodo hermano anterior
                    + "let $inicio := $r/inicio/text() "
                    +  "let $fin := if ($r/fin) then $r/fin/text() else 'Por determinar' " //compruebo si tiene fecha fin y pongo texto por defecto sino la tiene
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

    static void guardarMarcas(Context context, String directorioSalida) {
        try {
            // Consulta para obtener cada marca por separado
            String query
                    = "for $v in collection('" + databaseName + "')//vehiculo "
                    + "let $marca := $v/marca/text() "
                    + "return concat('Marca: ', $marca)";

            String result = new XQuery(query).execute(context);

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
}
