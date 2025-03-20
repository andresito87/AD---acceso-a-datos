package modelos;

import java.time.LocalDate;

/**
 * Clase que representa una reparación realizada en un vehículo dentro del
 * sistema de gestión del taller. Contiene información sobre la matrícula del
 * vehículo, las fechas de inicio y fin de la reparación, y el mecánico
 * encargado de realizarla.
 *
 * <p>
 * Esta clase proporciona métodos para obtener y modificar los datos de la
 * reparación.</p>
 *
 * @author andres
 */
public class Reparacion {

    private String matricula;
    private LocalDate inicio;
    private LocalDate fin;
    private Mecanico mecanico;

    /**
     * Constructor que inicializa una nueva reparación con los datos
     * especificados.
     *
     * @param matricula Matrícula del vehículo en reparación.
     * @param inicio Fecha de inicio de la reparación.
     * @param fin Fecha de finalización de la reparación.
     * @param mecanico Mecánico encargado de la reparación.
     */
    public Reparacion(String matricula, LocalDate inicio, LocalDate fin, Mecanico mecanico) {
        this.matricula = matricula;
        this.inicio = inicio;
        this.fin = fin;
        this.mecanico = mecanico;
    }

    /**
     * Obtiene la matrícula del vehículo en reparación.
     *
     * @return La matrícula del vehículo.
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Establece la matrícula del vehículo en reparación.
     *
     * @param matricula Nueva matrícula del vehículo.
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * Obtiene la fecha de inicio de la reparación.
     *
     * @return La fecha de inicio.
     */
    public LocalDate getInicio() {
        return inicio;
    }

    /**
     * Establece la fecha de inicio de la reparación.
     *
     * @param inicio Nueva fecha de inicio.
     */
    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    /**
     * Obtiene la fecha de finalización de la reparación.
     *
     * @return La fecha de finalización.
     */
    public LocalDate getFin() {
        return fin;
    }

    /**
     * Establece la fecha de finalización de la reparación.
     *
     * @param fin Nueva fecha de finalización.
     */
    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    /**
     * Obtiene el mecánico encargado de la reparación.
     *
     * @return El mecánico responsable.
     */
    public Mecanico getMecanico() {
        return mecanico;
    }

    /**
     * Asigna un mecánico a la reparación.
     *
     * @param mecanico Nuevo mecánico responsable.
     */
    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

}
