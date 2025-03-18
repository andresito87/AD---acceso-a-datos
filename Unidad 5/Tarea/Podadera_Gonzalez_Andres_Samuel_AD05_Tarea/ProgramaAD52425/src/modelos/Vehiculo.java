package modelos;

/**
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

    public Vehiculo(String matricula, int anyoFabricacion, Propietario propietario, String marca, String modelo, int kilometraje) {
        this.matricula = matricula;
        this.anyoFabricacion = anyoFabricacion;
        this.propietario = propietario;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAnyoFabricacion() {
        return anyoFabricacion;
    }

    public void setAnyoFabricacion(int anyoFabricacion) {
        this.anyoFabricacion = anyoFabricacion;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

}
