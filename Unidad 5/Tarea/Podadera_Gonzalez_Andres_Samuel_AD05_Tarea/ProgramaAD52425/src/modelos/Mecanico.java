package modelos;

/**
 * Clase que representa a un mecánico en el sistema de gestión del taller.
 * Contiene información básica como el nombre, apellidos y teléfono de contacto.
 *
 * <p>
 * Esta clase proporciona métodos para obtener y modificar los datos del
 * mecánico.</p>
 *
 * @author andres
 */
public class Mecanico {

    private String nombre;
    private String apellidos;
    private String telefono;

    /**
     * Constructor que inicializa un nuevo mecánico con sus datos personales.
     *
     * @param nombre Nombre del mecánico.
     * @param apellidos Apellidos del mecánico.
     * @param telefono Número de teléfono del mecánico.
     */
    public Mecanico(String nombre, String apellidos, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    /**
     * Obtiene el nombre del mecánico.
     *
     * @return El nombre del mecánico.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del mecánico.
     *
     * @param nombre Nuevo nombre del mecánico.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del mecánico.
     *
     * @return Los apellidos del mecánico.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del mecánico.
     *
     * @param apellidos Nuevos apellidos del mecánico.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el número de teléfono del mecánico.
     *
     * @return El número de teléfono del mecánico.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del mecánico.
     *
     * @param telefono Nuevo número de teléfono del mecánico.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
