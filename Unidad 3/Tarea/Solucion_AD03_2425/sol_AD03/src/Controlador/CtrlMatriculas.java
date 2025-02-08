/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Usuario
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Consultas;
import Modelo.Universidad;
import Modelo.Estudiante;
import Vista.vistaMatriculas;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class CtrlMatriculas implements ActionListener{
    private final Estudiante estudiante;
    private final Consultas consul;
//    private final frmvista vista;    
    private final vistaMatriculas vista;
    
    DefaultTableModel modelo = new DefaultTableModel();
    
    public CtrlMatriculas(Estudiante estudiante, Consultas consul, vistaMatriculas vista) {
        this.estudiante=estudiante; 
        this.consul=consul; 
        this.vista=vista;   
        
        // Agrega controladores de eventos a los botones en la vista de formulario
        this.vista.btnguardaruniversidad.addActionListener(this);
        this.vista.btneliminaruniversidad.addActionListener(this);
        this.vista.btnguardarestudiante.addActionListener(this);
        this.vista.btneliminarestudiante.addActionListener(this);
        this.vista.btnmodificarimportematricula.addActionListener(this);
        this.vista.btnmostrartodo.addActionListener(this);
        this.vista.btnestudiantesordenmatriculas.addActionListener(this);
        this.vista.btnuniversidadestotalmatriculas.addActionListener(this);
        this.vista.btnestudiantesAlmeriabecados.addActionListener(this);
          
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //-------------------------------------
        // Accion al pulsar Guardar Universidad
        //-------------------------------------
        if (e.getSource() == vista.btnguardaruniversidad) {
            int codigo;
            String nombre;
            String provincia;
            Boolean privada;
            try {
                codigo = Integer.parseInt(vista.txtcodigo.getText());                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El código de la Universidad ha de ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (vista.txtnombreuni.getText().isEmpty() || vista.txtprovinciauni.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "No puede haber datos vacíos para registrar una Universidad.", "Error", JOptionPane.ERROR_MESSAGE);    
                return;
            }
            nombre=vista.txtnombreuni.getText();
            provincia=vista.txtprovinciauni.getText();
            privada=vista.ckprivada.isSelected();
            Universidad nuevauniversidad = new Universidad();
            nuevauniversidad.setCodigo(codigo);
            nuevauniversidad.setNombre(nombre);
            nuevauniversidad.setProvinciaUni(provincia); 
            nuevauniversidad.setPrivada(privada);
            boolean resul = consul.agregarUniversidad(nuevauniversidad);     
            if (resul) {
                JOptionPane.showMessageDialog(vista, "Universidad añadida correctamente", "Universidad Guardada", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                actualizarComboUniversidades();
                listarUniversidades(consul.obtenerUniversidades());
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar la Universidad", "Error", JOptionPane.ERROR_MESSAGE);
            }            
        }
        
        //-------------------------------------
        // Accion al pulsar Eliminar Universidad
        //-------------------------------------   
        if (e.getSource() == vista.btneliminaruniversidad) {
            int codborrar;
            
            if (vista.tbluniversidades.getSelectedRow() == -1 ){
                JOptionPane.showMessageDialog(vista, "La universidad a borrar debe estar seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {   
                codborrar= Integer.parseInt(vista.tbluniversidades.getValueAt(vista.tbluniversidades.getSelectedRow(), 0).toString());                
                
                if (consul.numeroEstudiantes(codborrar)!=0){
                    JOptionPane.showMessageDialog(vista, "La universidad no se puede eliminar porque tiene estudiantes matriculados", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else{
                    boolean resul = consul.eliminarUniversidad(codborrar);    
                    if (resul) {
                        JOptionPane.showMessageDialog(vista, "Universidad borrada correctamente", "Universidad Borrada", JOptionPane.INFORMATION_MESSAGE);
                        limpiar();
                        listarUniversidades(consul.obtenerUniversidades());
                        actualizarComboUniversidades();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error al borrar la Universidad", "Error", JOptionPane.ERROR_MESSAGE);
                    }  
                }     
            }
        }
        
        //------------------------------------
        // Acción al pulsar Guardar Estudiante
        //------------------------------------
        if (e.getSource() == vista.btnguardarestudiante) {
            
            String nif;
            Universidad universidad;
            String nombre;
            String apellidos;
            Date fechaNacimiento;
            String direccion;
            String provincia;
            Float importeMatricula;
            Boolean becado;
                        
            // Comprobación de que todos los datos se han introducido y con los valores adecuados            
            if (vista.txtnif.getText().isEmpty() || vista.txtnombre.getText().isEmpty() || 
                vista.txtapellidos.getText().isEmpty() || vista.txtdireccion.getText().isEmpty() || vista.txtprovincia.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "No puede haber datos vacíos para registrar un estudiante.", "Error", JOptionPane.ERROR_MESSAGE);    
                return;
            }
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // Define el formato
            try {
                fechaNacimiento = formato.parse(vista.txtfechanacimiento.getText()); // Convierte el String a Date
            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(vista, "la fecha de nacimiento es incorrecta. Formato correcto: yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                importeMatricula = Float.parseFloat(vista.txtimportematricula.getText());                
            } catch (NumberFormatException exc) {
                JOptionPane.showMessageDialog(vista, "El importe de matricula del estudiante ha de ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
                
            nif=vista.txtnif.getText(); 
            nombre=vista.txtnombre.getText();
            apellidos=vista.txtapellidos.getText();
            direccion=vista.txtdireccion.getText();
            provincia=vista.txtprovincia.getText();
            becado=vista.ckbecado.isSelected();
            
            // Se crea objeto Estudiante con todos los datos introducidos
            Estudiante nuevoestudiante = new Estudiante();
            nuevoestudiante.setNif(nif);
            nuevoestudiante.setNombre(nombre);
            nuevoestudiante.setApellidos(apellidos);
            nuevoestudiante.setFechaNacimiento(fechaNacimiento);
            nuevoestudiante.setDireccion(direccion);
            nuevoestudiante.setProvincia(provincia);
            nuevoestudiante.setImporteMatricula(importeMatricula);
            nuevoestudiante.setBecado(becado);
            
            String nomUni = (String) vista.cbouniversidad.getSelectedItem();
            List <Universidad> universidadbuscar = consul.buscarUniversidadNombre(nomUni);
            if (!universidadbuscar.isEmpty()) {
                for (Universidad uni : universidadbuscar) {
                   nuevoestudiante.setUniversidad(uni); 
                   break;
                }                
            }
            else {
                JOptionPane.showMessageDialog(vista, "El estudiante debe pertenecer a alguna universidad", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
             
            boolean resul = consul.agregarEstudiante(nuevoestudiante);     
            if (resul) {
                JOptionPane.showMessageDialog(vista, "Estudiante añadido correctamemte", "Estudiante Guardado", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                listarEstudiantes(consul.obtenerEstudiantes());
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar el estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            }       
        }
      
        //-------------------------------------
        // Accion al pulsar Eliminar Estudiante
        //-------------------------------------       
        if (e.getSource() == vista.btneliminarestudiante) {
            String dniborrar;
            
            if (vista.tblestudiantes.getSelectedRow() == -1 ){
                JOptionPane.showMessageDialog(vista, "El estudiante a borrar debe estar seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {   
                dniborrar=vista.tblestudiantes.getValueAt(vista.tblestudiantes.getSelectedRow(), 0).toString();
                boolean resul = consul.eliminarEstudiante(dniborrar);     
            
                if (resul) {
                    JOptionPane.showMessageDialog(vista, "Estudiante borrado correctamente", "Estudiante Borrado", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    listarEstudiantes(consul.obtenerEstudiantes());
                } else {
                JOptionPane.showMessageDialog(vista, "Error al borrar el Estudiante", "Error", JOptionPane.ERROR_MESSAGE);
                }  
            }     
        }
        
        //---------------------------------------------
        // Accion al pulsar Modificar importe Matrícula
        //---------------------------------------------       
        if (e.getSource() == vista.btnmodificarimportematricula) {
            String dnimodificar;
            Float nuevoImporteMatricula;
            
            if (vista.tblestudiantes.getSelectedRow() == -1 ){
                JOptionPane.showMessageDialog(vista, "El estudiante a modificar debe estar seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {   
                try {
                nuevoImporteMatricula = Float.parseFloat(vista.txtnuevoimportematricula.getText());                
                } catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(vista, "Debe introducir un valor numérico para el nuevo importe de matricula del estudiante", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dnimodificar=vista.tblestudiantes.getValueAt(vista.tblestudiantes.getSelectedRow(), 0).toString();
                boolean resul = consul.modificarImporteMatricula(dnimodificar, nuevoImporteMatricula);     
            
                if (resul) {
                    JOptionPane.showMessageDialog(vista, "Importe de matrícula de estudiante modificado correctamente", "Estudiante Modificado", JOptionPane.INFORMATION_MESSAGE);
                    limpiar();
                    listarEstudiantes(consul.obtenerEstudiantes());
                } else {
                JOptionPane.showMessageDialog(vista, "Error al modificar el Estudiante", "Error", JOptionPane.ERROR_MESSAGE);
                }  
            }     
        }
        
        
        //-----------------------------------------------
        // Accion al pulsar Mostrar todo
        //----------------------------------------------- 
        if (e.getSource() == vista.btnmostrartodo){
            limpiar();
            listarUniversidades(consul.obtenerUniversidades());
            actualizarComboUniversidades();
            listarEstudiantes(consul.obtenerEstudiantes());
        }
        
        //-----------------------------------------------
        // Accion al pulsar Alumnos por importe matrícula
        //----------------------------------------------- 
        if (e.getSource() == vista.btnestudiantesordenmatriculas){
            limpiar();
            listarEstudiantes(consul.obtenerEstudiantesOrdenadosPorMatricula());
        }
        
        //-----------------------------------------------
        // Accion al pulsar Alumnos por importe matrícula
        //----------------------------------------------- 
        if (e.getSource() == vista.btnestudiantesAlmeriabecados){
            limpiar();
            listarEstudiantes(consul.obtenerEstudiantesAlmeriaBecados());
        }
        
        //-----------------------------------------------
        // Accion al pulsar Mostrar Total Matriculas Universidad
        //----------------------------------------------- 
        if (e.getSource() == vista.btnuniversidadestotalmatriculas){
            List<Object[]> resul = consul.totalMatriculasUniversidad();
            StringBuilder sb = new StringBuilder();
            String cadena = ""; 
        
            for (Object[] r : resul) {
                Universidad uni = (Universidad) r[0];
                Double totalMatriculas = (Double) r[1];
                
                sb.append("Universidad: ").append(uni.getNombre()).append(" --- Total matrículas: ").append(totalMatriculas).append("\n");
            }        
            cadena=sb.toString();
            JTextArea textArea = new JTextArea(cadena);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            //
            // Tamaño máximo del panel
            //
            scrollPane.setPreferredSize(new Dimension(350, 500));
            //
            // Lo mostramos como un JDialog y le pasamos el scrollpane como parámetro.
            //
            JOptionPane.showMessageDialog(null, scrollPane, "Universidades e Ingresos", JOptionPane.INFORMATION_MESSAGE);;
        }
        
    }     
    /**
     * método que muestra todas las universiddes con todos sus atributos en la tabla de la interfaz gráfica
     * @param lunitmostrar 
     */
    public void listarUniversidades(List <Universidad> lunitmostrar){
        modelo = (DefaultTableModel) vista.tbluniversidades.getModel();
        modelo.setRowCount(0);
        
        Object[] object = new Object[4];              
        for (int i=0; i < lunitmostrar.size(); i++){
            object[0]=lunitmostrar.get(i).getCodigo();
            object[1]=lunitmostrar.get(i).getNombre();
            object[2]=lunitmostrar.get(i).getProvinciaUni();
            object[3]=lunitmostrar.get(i).getPrivada();
            modelo.addRow(object);
        }
        vista.tbluniversidades.setModel(modelo);
    }
    /**
     * método que muestra todas las universiddes con todos sus atributos en la tabla de la interfaz gráfica
     * @param lestmostrar 
     */
    public void listarEstudiantes(List <Estudiante> lestmostrar){
        modelo = (DefaultTableModel) vista.tblestudiantes.getModel();
        modelo.setRowCount(0);
        
        Object[] object = new Object[9];              
        for (int i=0; i < lestmostrar.size(); i++){
            object[0]=lestmostrar.get(i).getNif();
            object[1]=lestmostrar.get(i).getNombre();
            object[2]=lestmostrar.get(i).getApellidos();
            object[3]=lestmostrar.get(i).getFechaNacimiento();
            object[4]=lestmostrar.get(i).getDireccion();
            object[5]=lestmostrar.get(i).getProvincia();
            object[6]=lestmostrar.get(i).getImporteMatricula();
            object[7]=lestmostrar.get(i).getBecado();
            int codigo = lestmostrar.get(i).getUniversidad().getCodigo();            
            object[8]=consul.buscarNombreUniversidad(codigo);
            modelo.addRow(object);
        }
        vista.tblestudiantes.setModel(modelo);
    }
    
    /**
     * método para poner título a la ventana e inicializar el combo con las posible universidades que haya en la base de datos
     */ 
    public void iniciar(){
        vista.setTitle("Gestión Matriculas");   
        actualizarComboUniversidades();
    }
    
    /**
     * método para mostrar el combo de Universidades que hay en la base de datos
     */
    private void actualizarComboUniversidades() {
        // Limpiamos el combo antes de añadir nuevos equipos
        vista.cbouniversidad.removeAllItems();
        List<Universidad> lista=consul.obtenerUniversidades(); 
        // Añadimos las universidades al combo
        for (Universidad e : lista) {
            vista.cbouniversidad.addItem(e.getNombre());
        }       
    }
    
    /**
    * método para poner en blanco todos los campos de datos donde el usuario puede introducir valores
    */
    public void limpiar() {
        vista.txtnif.setText(null);
        vista.txtnombre.setText(null);
        vista.txtapellidos.setText(null);                
        vista.txtfechanacimiento.setText(null);
        vista.txtdireccion.setText(null);
        vista.txtprovincia.setText(null);
        vista.txtimportematricula.setText(null);
        vista.ckbecado.setSelected(false);
        vista.txtcodigo.setText(null);
        vista.txtnombreuni.setText(null);
        vista.txtprovinciauni.setText(null); 
        vista.ckprivada.setSelected(false);
        vista.txtnuevoimportematricula.setText(null);
    }
    
}
