package modelos;

/**
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class Autor {

    private String nombre;
    private String nacionalidad;
    private String pais_residencia;
    private int  anyo_de_nacimiento;
    private int ingresos_anuales;

    public Autor(String nombre, String nacionalidad, String pais_residencia, int anyo_de_nacimiento, int ingresos_anuales) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.pais_residencia = pais_residencia;
        this.anyo_de_nacimiento = anyo_de_nacimiento;
        this.ingresos_anuales = ingresos_anuales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPaisResidencia() {
        return pais_residencia;
    }

    public void setPaisResidencia(String pais_residencia) {
        this.pais_residencia = pais_residencia;
    }

    public int getAnyoDeNacimiento() {
        return anyo_de_nacimiento;
    }

    public void setAnyoDeNacimiento(int anyo_de_nacimiento) {
        this.anyo_de_nacimiento = anyo_de_nacimiento;
    }

    public int getIngresosAnuales() {
        return ingresos_anuales;
    }

    public void setIngresosAnuales(int ingresos_anuales) {
        this.ingresos_anuales = ingresos_anuales;
    }

    public String toString() {
        return "Nombre: " + this.getNombre() + " - Nacionalidad: " + this.getNacionalidad()
                + " - País: " + this.getPaisResidencia() + " - Año de naciemiento: " + this.getAnyoDeNacimiento()
                + " - Ingresos anuales: " + this.getIngresosAnuales();
    }

}
