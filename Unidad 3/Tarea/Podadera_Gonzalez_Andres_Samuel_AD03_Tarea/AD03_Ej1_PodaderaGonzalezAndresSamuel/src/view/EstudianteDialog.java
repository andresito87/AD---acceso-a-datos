package view;

import controller.EstudianteController;
import controller.UniversidadController;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import model.dto.RespuestaDTO;
import model.entity.Estudiante;
import model.entity.Universidad;

/**
 *
 * @author ANDRES SAMUEL PODADERA GONZALEZ
 */
public class EstudianteDialog extends javax.swing.JDialog {

    private int codigoUniversidad = -1;
    private DefaultTableModel tableModel;
    private boolean esOperacionActalizacion = false;

    /**
     * Creates new form EstudianteDialog
     *
     * @param parent
     * @param modal
     * @param codigoUniversidad
     */
    public EstudianteDialog(java.awt.Frame parent, boolean modal, int codigoUniversidad) {
        super(parent, modal);
        this.codigoUniversidad = codigoUniversidad;
        initComponents();
        setTitle("Estudiantes");
        setSize(1200, 800);
        setLocationRelativeTo(null);

        this.cargarEstudiantes(this.codigoUniversidad);
        this.inicializarFormulario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuTablaEstudiantes = new javax.swing.JPopupMenu();
        menuItemModificar = new javax.swing.JMenuItem();
        menuItemEliminar = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEstudiantes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoNif = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoImporteMat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        campoApellidos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        campoDireccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campoCodigoUni = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        campoProvincia = new javax.swing.JTextField();
        botonSalir = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        checkboxBecado = new javax.swing.JCheckBox();
        spinnerFechaNac = new javax.swing.JSpinner();

        menuItemModificar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemModificar.setText("modificar");
        menuItemModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuItemModificarMousePressed(evt);
            }
        });
        popupMenuTablaEstudiantes.add(menuItemModificar);

        menuItemEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        menuItemEliminar.setText("eliminar");
        menuItemEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuItemEliminarMousePressed(evt);
            }
        });
        popupMenuTablaEstudiantes.add(menuItemEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("Estudiantes");

        tablaEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NIF", "Nombre", "Apellidos", "Fecha naciemiento", "Direccion", "Provincia", "Importe Matricula", "Becado", "Universidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEstudiantes.setComponentPopupMenu(popupMenuTablaEstudiantes);
        jScrollPane1.setViewportView(tablaEstudiantes);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Agregar/modificar un estudiante");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("NIF:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Importe matrícula:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Apellidos:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Nombre:");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Fecha nacimiento:");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Direccion:");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Codigo Universidad:");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Provincia:");

        botonSalir.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        botonSalir.setForeground(new java.awt.Color(255, 0, 0));
        botonSalir.setText("Salir");
        botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonSalirMouseClicked(evt);
            }
        });

        botonGuardar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        botonGuardar.setForeground(new java.awt.Color(0, 153, 0));
        botonGuardar.setText("Guardar");
        botonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonGuardarMouseClicked(evt);
            }
        });

        checkboxBecado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        checkboxBecado.setText("Becado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNif, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoImporteMat, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(campoCodigoUni, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkboxBecado)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(412, 412, 412)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(59, 59, 59))
            .addGroup(layout.createSequentialGroup()
                .addGap(474, 474, 474)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(283, 283, 283))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(385, 385, 385))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(campoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoImporteMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(campoProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(campoCodigoUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxBecado))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_botonSalirMouseClicked

    private void botonGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonGuardarMouseClicked

        String nif = this.campoNif.getText();
        String nombre = this.campoNombre.getText();
        String apellidos = this.campoApellidos.getText();
        Date fechaNacimiento = (Date) this.spinnerFechaNac.getValue();
        String direccion = this.campoDireccion.getText();
        String provincia = this.campoProvincia.getText();
        float importeMatricula = Float.parseFloat(this.campoImporteMat.getText().replace(",", "."));
        boolean esBecado = this.checkboxBecado.isSelected();
        String codUniversidad = this.campoCodigoUni.getText();
        Universidad universidad = UniversidadController.obtener(codUniversidad);

        if (nif != null && !nif.trim().isEmpty()
                && nombre != null && !nombre.trim().isEmpty()
                && apellidos != null && !apellidos.trim().isEmpty()
                && fechaNacimiento != null
                && direccion != null && !direccion.trim().isEmpty()
                && provincia != null && !provincia.trim().isEmpty()
                && codUniversidad != null && !codUniversidad.trim().isEmpty()
                && universidad != null) {

            Estudiante nuevoEstudiante = new Estudiante();
            nuevoEstudiante.setNif(nif);
            nuevoEstudiante.setNombre(nombre);
            nuevoEstudiante.setApellidos(apellidos);
            nuevoEstudiante.setFechaNacimiento(fechaNacimiento);
            nuevoEstudiante.setDireccion(direccion);
            nuevoEstudiante.setProvincia(provincia);
            nuevoEstudiante.setImporteMatricula(importeMatricula);
            nuevoEstudiante.setBecado(esBecado);
            nuevoEstudiante.setUniversidad(universidad);

            // compruebo si quiere actualizar
            if (esOperacionActalizacion) {

                RespuestaDTO respuesta = modificar(nuevoEstudiante);

                if (!respuesta.isSuccess()) {
                    JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    limpiarFormulario();
                    cargarEstudiantes(codigoUniversidad);
                    JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {

                RespuestaDTO respuesta = agregar(nuevoEstudiante);

                if (!respuesta.isSuccess()) {
                    JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    limpiarFormulario();
                    cargarEstudiantes(codigoUniversidad);
                    JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            // actualizo la tabla con las modifcaciones
            cargarEstudiantes(this.codigoUniversidad);
        } else {
            JOptionPane.showMessageDialog(this, "Debes rellenar todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_botonGuardarMouseClicked

    private void menuItemModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItemModificarMousePressed

        // comprobar si hay una fila seleccionada
        if (!hayFilaSeleccionada()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una fila de la tabla", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // operacion de modificacion
        esOperacionActalizacion = true;

        // obtener la fila seleccionada
        int filaSeleccionada = tablaEstudiantes.getSelectedRow();

        // extraer los valores de la fila seleccionada
        Object valor1 = tablaEstudiantes.getValueAt(filaSeleccionada, 0); // Nif (ID)
        Object valor2 = tablaEstudiantes.getValueAt(filaSeleccionada, 1); // Nombre
        Object valor3 = tablaEstudiantes.getValueAt(filaSeleccionada, 2); // Apellidos
        Object valor4 = tablaEstudiantes.getValueAt(filaSeleccionada, 3); // Fecha de nacimiento
        Object valor5 = tablaEstudiantes.getValueAt(filaSeleccionada, 4); // Dirección
        Object valor6 = tablaEstudiantes.getValueAt(filaSeleccionada, 5); // Provincia
        Object valor7 = tablaEstudiantes.getValueAt(filaSeleccionada, 6); // Importe matricula
        Object valor8 = tablaEstudiantes.getValueAt(filaSeleccionada, 7); // Becado (true/false)

        this.campoNif.setText(valor1.toString());
        this.campoNombre.setText(valor2.toString());
        this.campoApellidos.setText(valor3.toString());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // convertir el String a Date
            Date fecha = dateFormat.parse(valor4.toString());

            // asignar la fecha al JSpinner
            spinnerFechaNac.setValue(new Date(fecha.getTime()));

        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha: " + e.getMessage());
        }

        this.campoDireccion.setText(valor5.toString());
        this.campoProvincia.setText(valor6.toString());
        this.campoImporteMat.setText(valor7.toString());
        this.checkboxBecado.setSelected(!(valor8 == "No"));

        // actualizar los cambios en la tabla
        this.cargarEstudiantes(this.codigoUniversidad);

    }//GEN-LAST:event_menuItemModificarMousePressed

    private void menuItemEliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuItemEliminarMousePressed

        // comprobar si hay una fila seleccionada
        if (!hayFilaSeleccionada()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una fila primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // obtener la fila seleccionada
        int filaSeleccionada = tablaEstudiantes.getSelectedRow();

        // Extraer los valores de la fila seleccionada
        String nifEstudiante = tablaEstudiantes.getValueAt(filaSeleccionada, 0).toString(); // nif (ID)

        // confirmar la eliminación
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que quieres eliminar el estudiante \"" + tablaEstudiantes.getValueAt(filaSeleccionada, 1).toString() + "\"?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // eliminar el estudiante de la base de datos
            RespuestaDTO respuesta = eliminar(nifEstudiante);

            if (respuesta.isSuccess()) {
                // eliminar la fila de la JTable
                DefaultTableModel model = (DefaultTableModel) tablaEstudiantes.getModel();
                model.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, respuesta.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_menuItemEliminarMousePressed

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
            java.util.logging.Logger.getLogger(EstudianteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstudianteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstudianteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstudianteDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                EstudianteDialog dialog = new EstudianteDialog(new javax.swing.JFrame(), true, 0);
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

    private void cargarEstudiantes(int codigoUniversidad) {
        // llamar al método listarTodas() del controlador
        List<Estudiante> listaEstudiantes = EstudianteController.listarPorCodigoUniversidad(codigoUniversidad);

        // limpiar el modelo de la tabla antes de cargar nuevos datos
        tableModel = (DefaultTableModel) tablaEstudiantes.getModel();
        tableModel.setRowCount(0);

        // añadir cada universidad a la tabla
        if (listaEstudiantes != null) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNacimientoFormateada = "";

            for (Estudiante estudiante : listaEstudiantes) {
                try {
                    // convierte el String a Date
                    Date fechaNacimiento = parseFormat.parse(estudiante.getFechaNacimiento().toString());

                    // formatea la fecha al formato deseado (dd/MM/yyyy)
                    fechaNacimientoFormateada = dateFormat.format(fechaNacimiento);

                } catch (ParseException e) {
                    System.err.println("Error al convertir la fecha: " + e.getMessage());
                }
                Object[] fila = {
                    estudiante.getNif(),
                    estudiante.getNombre(),
                    estudiante.getApellidos(),
                    fechaNacimientoFormateada,
                    estudiante.getDireccion(),
                    estudiante.getProvincia(),
                    String.format("%.2f", estudiante.getImporteMatricula()),
                    estudiante.isBecado() ? "Sí" : "No",
                    estudiante.getUniversidad().getNombre(), // el controlador devuelve el objeto universidad completo
                };
                tableModel.addRow(fila);
            }
            // configurar tamaños de las columnas
            ajustarTamanioColumnas();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la lista de universidades", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ajustarTamanioColumnas() {
        // obtener el modelo de columnas
        javax.swing.table.TableColumnModel columnModel = tablaEstudiantes.getColumnModel();

        // definir tamaños para cada columna
        columnModel.getColumn(0).setPreferredWidth(80);  // NIF
        columnModel.getColumn(0).setMinWidth(80);
        columnModel.getColumn(0).setMaxWidth(100);

        columnModel.getColumn(1).setPreferredWidth(150); // Nombre
        columnModel.getColumn(1).setMinWidth(100);
        columnModel.getColumn(1).setMaxWidth(200);

        columnModel.getColumn(2).setPreferredWidth(150); // Apellidos
        columnModel.getColumn(2).setMinWidth(100);
        columnModel.getColumn(2).setMaxWidth(200);

        columnModel.getColumn(3).setPreferredWidth(120); // Fecha nacimiento
        columnModel.getColumn(3).setMinWidth(100);
        columnModel.getColumn(3).setMaxWidth(150);

        columnModel.getColumn(4).setPreferredWidth(180); // Dirección
        columnModel.getColumn(4).setMinWidth(150);
        columnModel.getColumn(4).setMaxWidth(250);

        columnModel.getColumn(5).setPreferredWidth(100); // Provincia
        columnModel.getColumn(5).setMinWidth(80);
        columnModel.getColumn(5).setMaxWidth(120);

        columnModel.getColumn(6).setPreferredWidth(120); // Importe Matrícula
        columnModel.getColumn(6).setMinWidth(100);
        columnModel.getColumn(6).setMaxWidth(150);

        columnModel.getColumn(7).setPreferredWidth(80); // Becado (checkbox)
        columnModel.getColumn(7).setMinWidth(50);
        columnModel.getColumn(7).setMaxWidth(80);

        columnModel.getColumn(8).setPreferredWidth(150); // Nombre Universidad
        columnModel.getColumn(8).setMinWidth(100);
        columnModel.getColumn(8).setMaxWidth(200);
    }

    private void colocarFechaDeNacimiento() {
        try {
            // obtener la fecha actual con java.util.Date
            Date fechaHoy = new Date();

            // configurar el modelo de fecha para el spinner (no se usa incremento explícito de días)
            SpinnerDateModel model = new SpinnerDateModel(
                    fechaHoy, // fecha inicial
                    null, // límite inferior (null = sin límite)
                    null, // límite superior (null = sin límite)
                    GregorianCalendar.DAY_OF_MONTH // incremento de días
            );

            // asignar el modelo al spinner
            spinnerFechaNac.setModel(model);

            // configurar el editor del spinner con el formato "dd/MM/yyyy"
            JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerFechaNac, "dd/MM/yyyy");
            spinnerFechaNac.setEditor(editor);

            // establecer la fecha actual en el spinner
            spinnerFechaNac.setValue(fechaHoy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarFormulario() {
        this.campoCodigoUni.setText(String.valueOf(this.codigoUniversidad));
        this.campoCodigoUni.setEditable(false);

        this.colocarFechaDeNacimiento();

        // ===========================
        // BOTÓN GUARDAR (Color Azul)
        // ===========================
        // cambiar el color de fondo
        botonGuardar.setBackground(new Color(70, 130, 180));
        botonGuardar.setForeground(Color.WHITE);

        // quitar borde de enfoque
        botonGuardar.setFocusPainted(false);

        // cambiar el cursor a 'pointer'
        botonGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // evento para cambiar el color de fondo cuando el ratón está sobre el botón
        botonGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonGuardar.setBackground(new Color(100, 149, 237));
                botonGuardar.setForeground(new Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonGuardar.setBackground(new Color(70, 130, 180));
                botonGuardar.setForeground(new Color(255, 255, 255));
            }
        });

        // ===========================
        // BOTÓN SALIR (Color Rojo)
        // ===========================
        // cambiar el color de fondo
        botonSalir.setBackground(new Color(178, 34, 34)); // Rojo fuego
        botonSalir.setForeground(Color.WHITE); // Texto en blanco

        // quitar borde de enfoque
        botonSalir.setFocusPainted(false);

        // cambiar el cursor a 'pointer'
        botonSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // evento para cambiar el color de fondo cuando el ratón está sobre el botón
        botonSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonSalir.setBackground(new Color(220, 20, 60));
                botonSalir.setForeground(new Color(0, 0, 0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonSalir.setBackground(new Color(178, 34, 34));
                botonSalir.setForeground(new Color(255, 255, 255));
            }
        });

    }

    private RespuestaDTO agregar(Estudiante nuevoEstudiante) {
        return EstudianteController.agregarNuevo(nuevoEstudiante);
    }

    private RespuestaDTO eliminar(String nifEstudiante) {
        return EstudianteController.eliminar(nifEstudiante);
    }

    private RespuestaDTO modificar(Estudiante estudiante) {
        // vuelvo a operacion de creacion
        esOperacionActalizacion = false;

        return EstudianteController.modificar(estudiante);
    }

    private void limpiarFormulario() {
        this.campoNif.setText("");
        this.campoNombre.setText("");
        this.campoApellidos.setText("");
        this.spinnerFechaNac.setValue(new Date());
        this.campoDireccion.setText("");
        this.campoProvincia.setText("");
        this.campoImporteMat.setText("");
        this.checkboxBecado.setSelected(false);
    }

    private boolean hayFilaSeleccionada() {
        return this.tablaEstudiantes.getSelectedRow() != -1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JTextField campoApellidos;
    private javax.swing.JTextField campoCodigoUni;
    private javax.swing.JTextField campoDireccion;
    private javax.swing.JTextField campoImporteMat;
    private javax.swing.JTextField campoNif;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoProvincia;
    private javax.swing.JCheckBox checkboxBecado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuItemEliminar;
    private javax.swing.JMenuItem menuItemModificar;
    private javax.swing.JPopupMenu popupMenuTablaEstudiantes;
    private javax.swing.JSpinner spinnerFechaNac;
    private javax.swing.JTable tablaEstudiantes;
    // End of variables declaration//GEN-END:variables
}
