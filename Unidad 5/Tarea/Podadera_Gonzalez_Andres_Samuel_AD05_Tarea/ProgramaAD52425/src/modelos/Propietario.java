package modelos;

/**
 * Clase que representa a un propietario de un vehículo en el sistema de gestión
 * del taller. Contiene información básica como el nombre y los apellidos del
 * propietario.
 *
 * <p>
 * Esta clase proporciona métodos para obtener y modificar los datos del
 * propietario.</p>
 *
 * @author andres
 */
public class Propietario {

    private String nombre;
    private String apellidos;

    /**
     * Constructor que inicializa un nuevo propietario con su nombre y
     * apellidos.
     *
     * @param nombre Nombre del propietario.
     * @param apellidos Apellidos del propietario.
     */
    public Propietario(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el nombre del propietario.
     *
     * @return El nombre del propietario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del propietario.
     *
     * @param nombre Nuevo nombre del propietario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del propietario.
     *
     * @return Los apellidos del propietario.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del propietario.
     *
     * @param apellidos Nuevos apellidos del propietario.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
