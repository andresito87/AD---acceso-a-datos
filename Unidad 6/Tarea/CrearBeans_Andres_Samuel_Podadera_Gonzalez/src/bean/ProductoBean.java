package bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andres
 */
public class ProductoBean implements Serializable {

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

    private void conectarBD() {

        // parametros de la conexion
        String username = "root";
        String password = "admin";
        String nombreDB = this.nombreBD;

        try {
            // establece la conexion
            conexionDB = DriverManager
                    .getConnection("jdbc:mysql://localhost:3307/" + nombreDB, username, password);
            System.out.println("Conexion establecida correctamente");
        } catch (SQLException ex) {
            System.out.println("Error de conexión a la base de datos. " + ex.getMessage());
        }
    }

    public void cerrarConexionBD() {
        // si la conexion esta creada, la cerramos
        if (conexionDB != null) {
            try {
                conexionDB.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión a la base de datos." + ex.getMessage());
            }
        }

    }

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

    public List<Producto> obtenerTodos() {
        if (this.conexionDB == null) {
            this.conectarBD();
        }

        productos = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

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

    public int getCantidad() {
        return this.productos.size();
    }

    public boolean anadir() {
        if (this.conexionDB == null) {
            this.conectarBD();
        }

        PreparedStatement pstmt;
        boolean resultado = false;

        try {
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

        } catch (SQLException ex) {
            System.out.println("Error al añadir un producto. " + ex.getMessage());
        }

        return resultado;
    }

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

        } catch (SQLException ex) {
            System.out.println("Error al eliminar un producto. " + ex.getMessage());
        }

        return resultado;
    }

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

}
