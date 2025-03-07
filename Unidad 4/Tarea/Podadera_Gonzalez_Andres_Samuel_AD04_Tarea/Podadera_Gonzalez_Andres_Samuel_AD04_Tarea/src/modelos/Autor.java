package modelos;

/**
 * Representa a un autor con información personal relevante, como su nombre,
 * nacionalidad, país de residencia, año de nacimiento e ingresos anuales.
 *
 * <p>
 * Esta clase proporciona métodos para obtener y modificar los atributos
 * relacionados con un autor, así como un método {@code toString()} para
 * representar la información del autor en formato de texto.</p>
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class Autor {

    private String nombre;
    private String nacionalidad;
    private String pais_residencia;
    private int anyo_de_nacimiento;
    private int ingresos_anuales;

    /**
     * Constructor de la clase Autor.
     *
     * @param nombre El nombre del autor.
     * @param nacionalidad La nacionalidad del autor.
     * @param pais_residencia El país de residencia del autor.
     * @param anyo_de_nacimiento El año de nacimiento del autor.
     * @param ingresos_anuales Los ingresos anuales del autor.
     */
    public Autor(String nombre, String nacionalidad, String pais_residencia, int anyo_de_nacimiento, int ingresos_anuales) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.pais_residencia = pais_residencia;
        this.anyo_de_nacimiento = anyo_de_nacimiento;
        this.ingresos_anuales = ingresos_anuales;
    }

    /**
     * Obtiene el nombre del autor.
     *
     * @return El nombre del autor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del autor.
     *
     * @param nombre El nombre a asignar al autor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la nacionalidad del autor.
     *
     * @return La nacionalidad del autor.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del autor.
     *
     * @param nacionalidad La nacionalidad a asignar al autor.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene el país de residencia del autor.
     *
     * @return El país de residencia del autor.
     */
    public String getPaisResidencia() {
        return pais_residencia;
    }

    /**
     * Establece el país de residencia del autor.
     *
     * @param pais_residencia El país de residencia a asignar al autor.
     */
    public void setPaisResidencia(String pais_residencia) {
        this.pais_residencia = pais_residencia;
    }

    /**
     * Obtiene el año de nacimiento del autor.
     *
     * @return El año de nacimiento del autor.
     */
    public int getAnyoDeNacimiento() {
        return anyo_de_nacimiento;
    }

    /**
     * Establece el año de nacimiento del autor.
     *
     * @param anyo_de_nacimiento El año de nacimiento a asignar al autor.
     */
    public void setAnyoDeNacimiento(int anyo_de_nacimiento) {
        this.anyo_de_nacimiento = anyo_de_nacimiento;
    }

    /**
     * Obtiene los ingresos anuales del autor.
     *
     * @return Los ingresos anuales del autor.
     */
    public int getIngresosAnuales() {
        return ingresos_anuales;
    }

    /**
     * Establece los ingresos anuales del autor.
     *
     * @param ingresos_anuales Los ingresos anuales a asignar al autor.
     */
    public void setIngresosAnuales(int ingresos_anuales) {
        this.ingresos_anuales = ingresos_anuales;
    }

    /**
     * Representa al autor en formato de cadena de texto.
     *
     * <p>
     * Devuelve una cadena que contiene todos los atributos del autor en el
     * siguiente formato: "Nombre: [nombre] - Nacionalidad: [nacionalidad] -
     * País: [pais] - Año de nacimiento: [año] - Ingresos anuales:
     * [ingresos].</p>
     *
     * @return La representación en cadena del autor.
     */
    @Override
    public String toString() {
        return "Nombre: " + this.getNombre() + " - Nacionalidad: " + this.getNacionalidad()
                + " - País: " + this.getPaisResidencia() + " - Año de naciemiento: " + this.getAnyoDeNacimiento()
                + " - Ingresos anuales: " + this.getIngresosAnuales();
    }

}
