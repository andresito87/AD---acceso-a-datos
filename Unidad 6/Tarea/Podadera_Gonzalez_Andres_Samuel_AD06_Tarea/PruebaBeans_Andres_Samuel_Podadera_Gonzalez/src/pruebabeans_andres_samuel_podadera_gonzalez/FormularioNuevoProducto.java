package pruebabeans_andres_samuel_podadera_gonzalez;

import bean.ProductoBean;
import java.awt.Frame;
import java.text.DecimalFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.text.DefaultFormatterFactory;

/**
 *
 * @author andres
 */
public class FormularioNuevoProducto extends javax.swing.JDialog {

    public static ProductoBean productos;

    /**
     * Creates new form FrameNuevoProducto
     * @param parent
     * @param modal
     * @param productos
     */
    public FormularioNuevoProducto(Frame parent, boolean modal, ProductoBean productos) {
        super(parent, modal);
        initComponents();
        FormularioNuevoProducto.productos = productos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelReferencia = new javax.swing.JLabel();
        jComboBoxCodigo = new javax.swing.JComboBox<>();
        jSpinnerNumero = new javax.swing.JSpinner();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelDescripcion = new javax.swing.JLabel();
        jTextFieldDescripcion = new javax.swing.JTextField();
        jLabelPrecio = new javax.swing.JLabel();
        jSpinnerPrecio = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jSpinnerDescuento = new javax.swing.JSpinner();
        jButtonAnadir = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jButtonCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTitulo.setText("Añadir nuevo producto");

        jLabelReferencia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelReferencia.setText("Referencia:");

        jComboBoxCodigo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AS", "CC", "PC", "PS" }));

        jSpinnerNumero.setModel(new javax.swing.SpinnerNumberModel(1, 0, 9999, 1));

        jLabelNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelNombre.setText("Nombre:");

        jLabelDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDescripcion.setText("Descripción:");

        jLabelPrecio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelPrecio.setText("Precio:");

        jSpinnerPrecio.setModel(new javax.swing.SpinnerNumberModel(0.00f, 0.00f, Float.MAX_VALUE, 1.0f));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Descuento:");

        jSpinnerDescuento.setModel(new javax.swing.SpinnerNumberModel(0.00f, 0.00f, Float.MAX_VALUE, 1.0f));

        jButtonAnadir.setBackground(new java.awt.Color(0, 204, 51));
        jButtonAnadir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonAnadir.setText("Añadir");
        jButtonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnadirActionPerformed(evt);
            }
        });

        jButtonLimpiar.setBackground(new java.awt.Color(0, 153, 153));
        jButtonLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jButtonCerrar.setBackground(new java.awt.Color(255, 51, 51));
        jButtonCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelReferencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabelNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabelDescripcion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jLabelPrecio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonAnadir)
                                .addGap(45, 45, 45)
                                .addComponent(jButtonLimpiar)
                                .addGap(46, 46, 46)
                                .addComponent(jButtonCerrar)
                                .addGap(137, 137, 137))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabelTitulo)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabelTitulo)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelReferencia)
                    .addComponent(jComboBoxCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombre)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescripcion)
                    .addComponent(jTextFieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrecio)
                    .addComponent(jSpinnerPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jSpinnerDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAnadir)
                    .addComponent(jButtonLimpiar)
                    .addComponent(jButtonCerrar))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        JFormattedTextField txt = ((JSpinner.DefaultEditor) jSpinnerNumero.getEditor()).getTextField();
        txt.setFormatterFactory(new DefaultFormatterFactory(
            new JFormattedTextField.AbstractFormatter() {
                private final DecimalFormat formato = new DecimalFormat("0000");

                {
                    formato.setParseIntegerOnly(true);
                }

                @Override
                public Object stringToValue(String text) throws java.text.ParseException {
                    Number num = formato.parse(text);
                    int valor = num.intValue();

                    if (valor < 0 || valor > 9999) {
                        throw new java.text.ParseException("Valor fuera de rango (0000-9999)", 0);
                    }

                    return Integer.valueOf(valor);
                }

                @Override
                public String valueToString(Object value) throws java.text.ParseException {
                    if (value == null) return "0001";
                    return formato.format(((Number) value).intValue());
                }
            }
        ));
        JFormattedTextField txtPrecio = ((JSpinner.DefaultEditor) jSpinnerPrecio.getEditor()).getTextField();
        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
            new javax.swing.JFormattedTextField.AbstractFormatter() {
                private final java.text.DecimalFormat formato = new java.text.DecimalFormat("0.00");

                {
                    formato.setParseBigDecimal(true); // Opcional: más preciso si querés
                }

                @Override
                public Object stringToValue(String text) throws java.text.ParseException {
                    Number num = formato.parse(text);
                    double valor = num.doubleValue();

                    if (valor < 0.0 || valor > Double.MAX_VALUE) {
                        throw new java.text.ParseException("El valor debe estar entre 0 y " + Double.MAX_VALUE, 0);
                    }
                    return Double.valueOf(valor); // asegurate del tipo correcto
                }

                @Override
                public String valueToString(Object value) throws java.text.ParseException {
                    if (value == null) return "0.00";
                    return formato.format(((Number) value).doubleValue());
                }
            }
        ));
        JFormattedTextField txtDescuento = ((JSpinner.DefaultEditor) jSpinnerDescuento.getEditor()).getTextField();

        // Formato para mostrar siempre 2 cifras (opcional, por estética)
        txtDescuento.setFormatterFactory(new DefaultFormatterFactory(
            new JFormattedTextField.AbstractFormatter() {
                private final java.text.DecimalFormat formato = new java.text.DecimalFormat("0.00");
                {
                    formato.setParseBigDecimal(true);
                }

                @Override
                public Object stringToValue(String text) throws java.text.ParseException {
                    Number num = formato.parse(text);
                    double valor = num.doubleValue();

                    if (valor < 0.0 || valor > Double.MAX_VALUE) {
                        throw new java.text.ParseException("El valor debe estar entre 0 y " + Double.MAX_VALUE, 0);
                    }
                    return Double.valueOf(valor);
                }

                @Override
                public String valueToString(Object value) throws java.text.ParseException {
                    if (value == null) return "0.00";
                    return formato.format(((Number) value).doubleValue());
                }
            }
        ));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        // TODO add your handling code here:
        this.limpiarFormulario();
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButtonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirActionPerformed
        // TODO add your handling code here:

        if (!esReferenciaValida()) {
            JOptionPane.showMessageDialog(null, "Verifica el número de referencia introducido",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (!esNombreValido()) {
            JOptionPane.showMessageDialog(null, "Verifica el nombre introducido",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (!esDescripcionValida()) {
            JOptionPane.showMessageDialog(null, "Verifica la descripción introducida",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (!esPrecioValido()) {
            JOptionPane.showMessageDialog(null, "Verifica el precio introducido",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (!esDescuentoValido()) {
            JOptionPane.showMessageDialog(null, "Verifica el descuento introducido",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            String numeroFormateado = String.format("%04d", Integer.valueOf(this.jSpinnerNumero.getValue().toString()));
            String codigo = this.jComboBoxCodigo.getItemAt(this.jComboBoxCodigo.getSelectedIndex());
            productos.setReferencia(codigo + numeroFormateado);
            productos.setNombre(this.jTextFieldNombre.getText());
            productos.setDescripcion(this.jTextFieldDescripcion.getText());
            productos.setPrecio(Float.parseFloat(this.jSpinnerPrecio.getValue().toString()));
            productos.setDescuento(Float.parseFloat(this.jSpinnerDescuento.getValue().toString()));

            boolean resultado = productos.anadir();
            if (resultado) {
                this.limpiarFormulario();
                JOptionPane.showMessageDialog(null, "Producto añadido correctamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Se ha producido un error al intentar agregar un producto",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_jButtonAnadirActionPerformed

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        // TODO add your handling code here:
        
        this.dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioNuevoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioNuevoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioNuevoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioNuevoProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormularioNuevoProducto dialog = new FormularioNuevoProducto(new javax.swing.JFrame(), true, productos);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    private boolean esReferenciaValida() {
        try {
            Integer.valueOf(this.jSpinnerNumero.getValue().toString());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Error al obtener el numero de referencia. " + ex.getMessage());
        }
        return false;
    }

    private boolean esNombreValido() {
        return !this.jTextFieldNombre.getText().isBlank();

    }

    private boolean esDescripcionValida() {
        return !this.jTextFieldDescripcion.getText().isBlank();
    }

    private boolean esPrecioValido() {
        try {
            Float.valueOf(this.jSpinnerPrecio.getValue().toString());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Error al obtener el precio del producto. " + ex.getMessage());
        }
        return false;
    }

    private boolean esDescuentoValido() {
        try {
            Float.valueOf(this.jSpinnerDescuento.getValue().toString());
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Error al obtener el descuento del producto. " + ex.getMessage());
        }
        return false;
    }

    private void limpiarFormulario() {
        this.jComboBoxCodigo.setSelectedIndex(0);
        this.jSpinnerNumero.setValue(1);
        this.jTextFieldNombre.setText("");
        this.jTextFieldDescripcion.setText("");
        this.jSpinnerDescuento.setValue(0);
        this.jSpinnerPrecio.setValue(0);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnadir;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JComboBox<String> jComboBoxCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelReferencia;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinnerDescuento;
    private javax.swing.JSpinner jSpinnerNumero;
    private javax.swing.JSpinner jSpinnerPrecio;
    private javax.swing.JTextField jTextFieldDescripcion;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
