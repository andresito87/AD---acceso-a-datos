package vistas;

import controladores.BaseDeDatos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.*;
import org.basex.query.QueryException;

public class Main {

    public static void main(String[] args) {
        try {

            Context contexto = BaseDeDatos.conectar();

            JFramePrincipal formularioPrincipal = new JFramePrincipal(contexto);
            formularioPrincipal.setVisible(true);
            formularioPrincipal.setTitle("Acceso a Datos - Tarea 05 - Andrés Samuel Podadera González - Curso: 2024/2025");
            formularioPrincipal.setLocationRelativeTo(null);
            
            // Cierra la conexión a la base de datos al cerrar la ventana
            formularioPrincipal.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    BaseDeDatos.desconectar();
                }
            });

        } catch (IOException ex) {
            System.out.println("Error de Entrada/Salida " + ex.getMessage());
        } catch (QueryException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
