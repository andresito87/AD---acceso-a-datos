package modelos;

/**
 * Clase que representa un vehículo dentro del sistema de gestión del taller.
 * Contiene información sobre la matrícula, año de fabricación, propietario,
 * marca, modelo y kilometraje del vehículo.
 *
 * <p>
 * Esta clase proporciona métodos para obtener y modificar los datos del
 * vehículo.</p>
 *
 * @author andres
 */
public class Vehiculo {

    private String matricula;
    private int anyoFabricacion;
    private Propietario propietario;
    private String marca;
    private String modelo;
    private int kilometraje;

    /**
     * Constructor que inicializa un nuevo vehículo con los datos especificados.
     *
     * @param matricula Matrícula del vehículo.
     * @param anyoFabricacion Año de fabricación del vehículo.
     * @param propietario Propietario del vehículo.
     * @param marca Marca del vehículo.
     * @param modelo Modelo del vehículo.
     * @param kilometraje Kilometraje actual del vehículo.
     */
    public Vehiculo(String matricula, int anyoFabricacion, Propietario propietario, String marca, String modelo, int kilometraje) {
        this.matricula = matricula;
        this.anyoFabricacion = anyoFabricacion;
        this.propietario = propietario;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
    }

    /**
     * Obtiene la matrícula del vehículo.
     *
     * @return La matrícula del vehículo.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Establece la matrícula del vehículo.
     *
     * @param matricula Nueva matrícula del vehículo.
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Obtiene el año de fabricación del vehículo.
     *
     * @return El año de fabricación.
     */
    public int getAnyoFabricacion() {
        return anyoFabricacion;
    }

    /**
     * Establece el año de fabricación del vehículo.
     *
     * @param anyoFabricacion Nuevo año de fabricación.
     */
    public void setAnyoFabricacion(int anyoFabricacion) {
        this.anyoFabricacion = anyoFabricacion;
    }

    /**
     * Obtiene el propietario del vehículo.
     *
     * @return El propietario del vehículo.
     */
    public Propietario getPropietario() {
        return propietario;
    }

    /**
     * Establece el propietario del vehículo.
     *
     * @param propietario Nuevo propietario del vehículo.
     */
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    /**
     * Obtiene la marca del vehículo.
     *
     * @return La marca del vehículo.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del vehículo.
     *
     * @param marca Nueva marca del vehículo.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el modelo del vehículo.
     *
     * @return El modelo del vehículo.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del vehículo.
     *
     * @param modelo Nuevo modelo del vehículo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtiene el kilometraje del vehículo.
     *
     * @return El kilometraje del vehículo.
     */
    public int getKilometraje() {
        return kilometraje;
    }

    /**
     * Establece el kilometraje del vehículo.
     *
     * @param kilometraje Nuevo kilometraje del vehículo.
     */
    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

}
