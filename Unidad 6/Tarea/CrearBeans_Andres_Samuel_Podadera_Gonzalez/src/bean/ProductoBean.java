package bean;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

/**
 * Clase JavaBean que gestiona operaciones CRUD sobre productos almacenados en
 * una base de datos MySQL. Además, permite la notificación de eventos cuando la
 * base de datos ha sido modificada.
 *
 * Esta clase funciona como un modelo de datos que encapsula la lógica de
 * conexión a base de datos, así como la manipulación de productos. Implementa
 * `Serializable` para permitir su uso en contextos donde se requiera
 * serialización (como aplicaciones distribuidas).
 *
 * También permite la suscripción a eventos de cambio mediante un listener
 * personalizado.
 *
 * @autor: andres
 */
public class ProductoBean implements Serializable {

    private final PropertyChangeSupport propertySupport = new PropertyChangeSupport(this);
    private BDModificadaListener receptor;

    // Propiedades del bean
    protected String nombreBD;
    protected String referencia;
    protected String nombre;
    protected String descripcion;
    protected float precio;
    protected float descuento;

    // Atributos privados
    List<Producto> productos = new ArrayList<>();
    Connection conexionDB = null;

    public ProductoBean() {
        this.nombreBD = "comercio";
    }

    /**
     * Get the value of nombreBD
     *
     * @return the value of nombreBD
     */
    public String getNombreBD() {
        return nombreBD;
    }

    /**
     * Set the value of nombreBD
     *
     * @param nombreBD new value of nombreBD
     */
    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    /**
     * Get the value of referencia
     *
     * @return the value of referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Set the value of referencia
     *
     * @param referencia new value of referencia
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Get the value of descripcion
     *
     * @return the value of descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Set the value of descripcion
     *
     * @param descripcion new value of descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Get the value of precio
     *
     * @return the value of precio
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Set the value of precio
     *
     * @param precio new value of precio
     */
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * Get the value of descuento
     *
     * @return the value of descuento
     */
    public float getDescuento() {
        return descuento;
    }

    /**
     * Set the value of descuento
     *
     * @param descuento new value of descuento
     */
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    /**
     * Establece una conexión a la base de datos definida por el atributo
     * `nombreBD`. Se conecta a una base de datos MySQL
     */
    private void conectarBD() {

        // parametros de la conexion
        String username = "root";
        String password = "admin";
        String nombreDB = this.nombreBD;

        try {
            // establece la conexion
            conexionDB = DriverManager
                    .getConnection("jdbc:mysql://localhost:3307/" + nombreDB, username, password);
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error de conexión a la base de datos. " + ex.getMessage());
        }
    }

    /**
     * Cierra la conexión activa con la base de datos, si existe
     */
    public void cerrarConexionBD() {
        // si la conexion esta creada, la cerramos
        if (conexionDB != null) {
            try {
                conexionDB.close();
                System.out.println("Conexión a la base de datos cerrada correctamente.");
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión a la base de datos." + ex.getMessage());
            }
        }

    }

    /**
     * Actualiza los campos del bean con los valores del producto ubicado en la
     * posición dada.
     *
     * @param i índice del producto en la lista `productos`.
     */
    public void seleccionarFila(int i) {

        if (i < this.productos.size()) {
            Producto p = (Producto) productos.get(i);
            this.referencia = p.getReferencia();
            this.nombre = p.getNombre();
            this.descripcion = p.getDescripcion();
            this.precio = p.getPrecio();
            this.descuento = p.getDescuento();
        } else {
            this.referencia = "";
            this.nombre = "";
            this.descripcion = "";
            this.precio = 0;
            this.descuento = 0;
        }
    }

    /**
     * Obtiene todos los productos desde la base de datos y los almacena en la
     * lista interna `productos`.
     *
     * @return lista de objetos Producto obtenidos de la base de datos.
     */
    public List<Producto> obtenerTodos() {
        if (this.conexionDB == null) {
            this.conectarBD();
        }

        productos = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            // sentencia SQL para obtener todos los productos
            String sql = "SELECT * FROM productos";

            // prepara la sentencia
            pstmt = conexionDB.prepareStatement(sql);

            // ejecuta la consulta
            rs = pstmt.executeQuery();

            // procesa los resultados
            while (rs.next()) {
                String referenciaProd = rs.getString("referencia");
                String nombreProd = rs.getString("nombre");
                String descripcionProd = rs.getString("descripcion");
                float precioProd = rs.getFloat("precio");
                float descuentoProd = rs.getFloat("descuento");

                this.productos.add(new Producto(referenciaProd, nombreProd, descripcionProd, precioProd, descuentoProd));
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener todos los productos. " + ex.getMessage());
        }

        return this.productos;
    }

    /**
     * Retorna la cantidad de productos cargados en la lista interna.
     *
     * @return número de productos en la lista.
     */
    public int getCantidad() {
        return this.productos.size();
    }

    /**
     * Añade un nuevo producto a la base de datos utilizando los valores
     * actuales del bean.
     *
     * @return true si el producto fue añadido exitosamente, false en caso
     * contrario.
     */
    public boolean anadir() {
        if (this.conexionDB == null) {
            this.conectarBD();
        }

        PreparedStatement pstmt;
        boolean resultado = false;

        try {
            String referenciaActual = this.getReferencia();

            // sentencia de inserción SQL con parametros
            String sql = "INSERT INTO productos (referencia, nombre, descripcion, precio, descuento) "
                    + "VALUES (?, ?, ?, ?, ?)";

            pstmt = conexionDB.prepareStatement(sql);

            // Asignar valores a los parámetros
            pstmt.setString(1, this.getReferencia());
            pstmt.setString(2, this.getNombre());
            pstmt.setString(3, this.getDescripcion());
            pstmt.setFloat(4, this.getPrecio());
            pstmt.setFloat(5, this.getDescuento());

            // Ejecutar y verificar si se insertó una fila
            int filas = pstmt.executeUpdate();
            resultado = filas > 0;

            if (resultado && receptor != null) {
                receptor.capturarBDModificada(new BDModificadaEvent(this));
                System.out.println("Producto con referencia " + referenciaActual
                        + " añadido correctamente.");
            }

        } catch (SQLException ex) {
            System.out.println("Error al añadir un producto. " + ex.getMessage());
        }

        return resultado;
    }

    /**
     * Elimina un producto de la base de datos por su referencia.
     *
     * @param referenciaProducto referencia del producto a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean eliminar(String referenciaProducto) {
        if (this.conexionDB == null) {
            this.conectarBD();
        }

        PreparedStatement pstmt;
        boolean resultado = false;

        try {

            // sentencia de eliminacion SQL con parametros
            String sql = "DELETE FROM productos WHERE referencia = ?";
            pstmt = conexionDB.prepareStatement(sql);
            pstmt.setString(1, referenciaProducto);

            int filas = pstmt.executeUpdate();
            resultado = filas > 0;

            if (resultado && receptor != null) {
                receptor.capturarBDModificada(new BDModificadaEvent(this));
                System.out.println("Producto con referencia " + referenciaProducto
                        + " eliminado correctamente.");
            }

        } catch (SQLException ex) {
            System.out.println("Error al eliminar un producto. " + ex.getMessage());
        }

        return resultado;
    }

    /**
     * Clase interna que representa un producto.
     */
    private class Producto {

        private final String referencia;
        private final String nombre;
        private final String descripcion;
        private final float precio;
        private final float descuento;

        public Producto(String referencia, String nombre, String descripcion, float precio, float descuento) {
            this.referencia = referencia;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.precio = precio;
            this.descuento = descuento;
        }

        public String getReferencia() {
            return referencia;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public float getPrecio() {
            return precio;
        }

        public float getDescuento() {
            return descuento;
        }

    }

    /**
     * Clase que representa un evento personalizado que se dispara cuando la
     * base de datos ha sido modificada.
     */
    public class BDModificadaEvent extends EventObject {

        /**
         * Constructor del evento.
         *
         * @param source el objeto que origina el evento.
         */
        public BDModificadaEvent(Object source) {
            super(source);
        }
    }

    /**
     * Interfaz para los listeners que desean ser notificados cuando la base de
     * datos ha sido modificada.
     */
    public interface BDModificadaListener extends EventListener {

        /**
         * Método que se invoca cuando ocurre una modificación en la base de
         * datos.
         *
         * @param ev evento que encapsula la información de la modificación.
         */
        void capturarBDModificada(BDModificadaEvent ev);
    }

    /**
     * Registra un listener para eventos de modificación de base de datos.
     *
     * @param receptor listener a registrar.
     */
    public void addBDModificadaListener(BDModificadaListener receptor) {
        this.receptor = receptor;
    }

    /**
     * Elimina un listener de eventos de modificación de base de datos.
     *
     * @param receptor listener a eliminar.
     */
    public void removeBDModificadaListener(BDModificadaListener receptor) {
        this.receptor = null;
    }

    /**
     * Agrega un {@link PropertyChangeListener} que será notificado cada vez que
     * ocurra un cambio en una propiedad del bean.
     *
     * @param listener el oyente que desea ser notificado de cambios en las
     * propiedades del bean.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    /**
     * Elimina un {@link PropertyChangeListener} previamente registrado.
     *
     * @param listener el oyente que ya no desea recibir notificaciones de
     * cambios de propiedades.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}
