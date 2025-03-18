package modelos;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author andres
 */
public class Reparacion {

    private String matricula;
    private LocalDate inicio;
    private LocalDate fin;
    private Mecanico mecanico;

    public Reparacion(String matricula, LocalDate inicio, LocalDate fin, Mecanico mecanico) {
        this.matricula = matricula;
        this.inicio = inicio;
        this.fin = fin;
        this.mecanico = mecanico;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

}
