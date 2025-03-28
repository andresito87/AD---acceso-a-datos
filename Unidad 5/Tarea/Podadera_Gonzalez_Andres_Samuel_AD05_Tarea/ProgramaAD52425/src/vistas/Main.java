package vistas;

import controladores.ConexionBD;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.*;
import org.basex.query.QueryException;

/**
 * Clase principal que inicia la aplicación de gestión de datos en BaseX. Se
 * encarga de establecer la conexión con la base de datos y abrir la ventana
 * principal. Al cerrar la ventana, la conexión con la base de datos se cierra
 * automáticamente.
 *
 * <p>
 * Curso: 2024/2025</p>
 * <p>
 * Autor: Andrés Samuel Podadera González</p>
 */
public class Main {

    public static void main(String[] args) {
        try {

            Context contexto = ConexionBD.conectar();

            JFramePrincipal formularioPrincipal = new JFramePrincipal(contexto);
            formularioPrincipal.setVisible(true);
            formularioPrincipal.setTitle("Acceso a Datos - Tarea 05 - Andrés Samuel Podadera González - Curso: 2024/2025");
            formularioPrincipal.setLocationRelativeTo(null);

            // Cierra la conexión a la base de datos al cerrar la ventana
            formularioPrincipal.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ConexionBD.desconectar();
                }
            });

        } catch (QueryException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
