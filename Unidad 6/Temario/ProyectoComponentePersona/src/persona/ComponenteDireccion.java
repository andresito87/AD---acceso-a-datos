package persona;

import java.io.Serializable;

/**
 *
 * @author andres
 */
public class ComponenteDireccion implements Serializable {

    protected String direccion;
    protected String poblacion;
    protected String provincia;

    public ComponenteDireccion() {
         this.direccion = "";
        this.poblacion = "";
        this.provincia = "";
    }
    
    // Constructor con parámetros (opcional)
    public ComponenteDireccion(String direccion, String poblacion, String provincia) {
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

    /**
     * Get the value of direccion
     *
     * @return the value of direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set the value of direccion
     *
     * @param direccion new value of direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Get the value of poblacion
     *
     * @return the value of poblacion
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Set the value of poblacion
     *
     * @param poblacion new value of poblacion
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * Get the value of provincia
     *
     * @return the value of provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Set the value of provincia
     *
     * @param provincia new value of provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    // Método para crear una instancia de ComponenteDireccion
    public static ComponenteDireccion createComponenteDireccion(String direccion, String poblacion, String provincia) {
        return new ComponenteDireccion(direccion, poblacion, provincia);
    }

    // Sobrecarga de toString()
    @Override
    public String toString() {
        return "Dirección: " + direccion + ", Población: " + poblacion + ", Provincia: " + provincia;
    }

}
