/**
 * Esta tarea vamos a crear un programa en Java, que permite introducir el nombre,
 * apellidos, y e-mail de un usuario mediante un menú que pida estos datos y
 * los grabe de forma secuencial en un fichero llamado "datosContacto.dat" del
 * directorio local del proyecto. Sería necesario introducir un carácter como
 * por ejemplo $ para separar un campo de otro. Deberás documentar bien
 * tu código así como realizar el adecuado manejo de las posibles excepciones.
 */
package ejercicio2;

import java.awt.Cursor;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Clase Ejercicio2 para la resolución del ejercicio 2.
 * <p>
 * Esta clase implementa una interfaz gráfica en Java Swing que permite escribir
 * la informacion introducida en un archivo de datos binario</p>
 *
 * <p>
 * La ventana se centra automáticamente en la pantalla y tiene como título
 * "Ejercicio 2".</p>
 *
 * @author Andrés Samuel Podadera González
 * @version 1.0
 */
public class Ejercicio2 extends javax.swing.JFrame {

    /**
     * crea un nuevo formulario Ejercicio2
     */
    public Ejercicio2() {
        initComponents();
        // centrar el formulario en la pantalla
        this.setLocationRelativeTo(null);
        // colocar titulo a la ventana
        this.setTitle("Ejercicio 2");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jLabelapellidos = new javax.swing.JLabel();
        jTextFieldApellidos = new javax.swing.JTextField();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelInfo = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jLabelResultado = new javax.swing.JLabel();
        jButtonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelPrincipal.setBackground(new java.awt.Color(204, 255, 204));

        jPanelTitulo.setBackground(new java.awt.Color(255, 255, 153));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 51, 0));
        jLabelTitulo.setText("Ejercicio 2 - Programa grabador de texto");

        javax.swing.GroupLayout jPanelTituloLayout = new javax.swing.GroupLayout(jPanelTitulo);
        jPanelTitulo.setLayout(jPanelTituloLayout);
        jPanelTituloLayout.setHorizontalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTituloLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(186, 186, 186))
        );
        jPanelTituloLayout.setVerticalGroup(
            jPanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTituloLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jLabelNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(0, 51, 51));
        jLabelNombre.setText("Nombre:");

        jLabelapellidos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelapellidos.setForeground(new java.awt.Color(0, 51, 51));
        jLabelapellidos.setText("Apellidos:");

        jTextFieldApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldApellidosActionPerformed(evt);
            }
        });

        jLabelEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelEmail.setForeground(new java.awt.Color(0, 51, 51));
        jLabelEmail.setText("E-mail:");

        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });

        jLabelInfo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelInfo.setForeground(new java.awt.Color(0, 51, 51));
        jLabelInfo.setText("Completa la siguiente información:");

        jButtonGuardar.setBackground(new java.awt.Color(102, 204, 0));
        jButtonGuardar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonGuardar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonGuardarMouseClicked(evt);
            }
        });
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonLimpiar.setBackground(new java.awt.Color(204, 0, 0));
        jButtonLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonLimpiar.setForeground(new java.awt.Color(0, 0, 0));
        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLimpiarMouseClicked(evt);
            }
        });

        jLabelResultado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelResultado.setForeground(new java.awt.Color(0, 0, 0));
        jLabelResultado.setText("Resultado:");

        jButtonSalir.setBackground(new java.awt.Color(0, 204, 204));
        jButtonSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonSalir.setForeground(new java.awt.Color(0, 0, 0));
        jButtonSalir.setText("Salir");
        jButtonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSalirMouseClicked(evt);
            }
        });
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelapellidos)
                            .addComponent(jLabelEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelNombre, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jTextFieldApellidos)
                            .addComponent(jTextFieldEmail)))
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabelInfo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardar)
                    .addComponent(jButtonLimpiar)
                    .addComponent(jButtonSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jLabelResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        jButtonGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jTextFieldApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldApellidosActionPerformed

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jButtonSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_jButtonSalirMouseClicked

    private void jButtonLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLimpiarMouseClicked
        // limpio los campos
        jTextFieldNombre.setText("");
        jTextFieldApellidos.setText("");
        jTextFieldEmail.setText("");
    }//GEN-LAST:event_jButtonLimpiarMouseClicked

    private void jButtonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMouseClicked
        // comprobar que los campos no estan vacios, si lo están, no se permite el guardado
        if (this.hayCamposVacios()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Aviso: hay campos vacíos, ingresa toda la información para continuar.", // mensaje a mostrar
                    "Campos Vacíos", // título de la ventana del diálogo
                    JOptionPane.WARNING_MESSAGE // tipo de mensaje
            );
            return;
        }
        // crear el archivo
        File fichero = new File("datosContacto.dat");

        // si el archivo no existe, lo creamos
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error al crear el fichero");
            }
        }
        // guardar la informacion
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fichero, true); // modo append, agrega no reemplaza
            dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF(this.jTextFieldNombre.getText());
            dataOutputStream.writeUTF(this.jTextFieldApellidos.getText());
            dataOutputStream.writeUTF(this.jTextFieldEmail.getText());

            // muestro el resultado en la etiqueta correspondiente
            this.jLabelResultado.setText("Resultado: Datos almacenados correctamente en el archivo 'datosContacto.dat'");

            // muestro aviso al usuario que la información se ha guardado correctamente
            JOptionPane.showMessageDialog(
                    this,
                    "Datos almacenados correctamente en el archivo 'datosContacto.dat'", // mensaje a mostrar
                    "Guardado correcto", // título de la ventana del diálogo
                    JOptionPane.INFORMATION_MESSAGE // tipo de mensaje
            );

            // despues de guardar limpio el formulario
            this.jTextFieldNombre.setText("");
            this.jTextFieldApellidos.setText("");
            this.jTextFieldEmail.setText("");

        } catch (IOException ex) {
            System.out.println("Error de escritura en el archivo 'datosContacto.dat'");

            // muestro error en la etiqueta correspondiente
            this.jLabelResultado.setText("Resultado: Error en el proceso de escritura en el archivo 'datosContacto.dat'");

            // muestro aviso al usuario de que se ha producido un error de escritura en el archivo
            JOptionPane.showMessageDialog(
                    this,
                    "Error en el proceso de escritura en el archivo 'datosContacto.dat'", // mensaje a mostrar
                    "Error", // título de la ventana del diálogo
                    JOptionPane.ERROR_MESSAGE // tipo de mensaje
            );

        } finally {
            // cerrar los flujos en bloques try-catch individuales
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar DataOutputStream");
            }
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar FileOutputStream");
            }
        }

    }//GEN-LAST:event_jButtonGuardarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ejercicio2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ejercicio2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ejercicio2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ejercicio2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ejercicio2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelInfo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelResultado;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelapellidos;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JTextField jTextFieldApellidos;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

    /**
     * Método que comprueba si hay algún campo vacío
     * @return 
     */
    boolean hayCamposVacios() {
        return this.jTextFieldNombre.getText().trim().isEmpty()
                || this.jTextFieldNombre.getText().trim().isEmpty()
                || this.jTextFieldEmail.getText().trim().isEmpty();
    }

}
