package modelos;

import java.util.UUID;

/**
 * Representa una canción con atributos como su identificador único, título,
 * duración, año de creación, género musical y el autor de la canción.
 *
 * <p>
 * Esta clase permite manejar la información básica de una canción y su autor,
 * además de generar un identificador único para cada canción.</p>
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public class Cancion {

    private String identificador;
    private String titulo;
    private int duracion;
    private int anyo_creacion;
    private String genero;
    private Autor autor;

    /**
     * Constructor de la clase Cancion.
     *
     * @param titulo El título de la canción.
     * @param duracion La duración de la canción en segundos.
     * @param anyo_creacion El año de creación de la canción.
     * @param genero El género musical de la canción.
     * @param autor El autor de la canción.
     */
    public Cancion(String titulo, int duracion, int anyo_creacion, String genero, Autor autor) {
        this.identificador = UUID.randomUUID().toString(); // generar id único
        this.titulo = titulo;
        this.duracion = duracion;
        this.anyo_creacion = anyo_creacion;
        this.genero = genero;
        this.autor = autor;
    }

    /**
     * Obtiene el identificador único de la canción.
     *
     * @return El identificador único de la canción.
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Establece el identificador de la canción.
     *
     * @param identificador El identificador a asignar a la canción.
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * Obtiene el título de la canción.
     *
     * @return El título de la canción.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título de la canción.
     *
     * @param titulo El título a asignar a la canción.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la duración de la canción en segundos.
     *
     * @return La duración de la canción en segundos.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Establece la duración de la canción en segundos.
     *
     * @param duracion La duración en segundos a asignar a la canción.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Obtiene el año de creación de la canción.
     *
     * @return El año de creación de la canción.
     */
    public int getAnyoCreacion() {
        return anyo_creacion;
    }

    /**
     * Establece el año de creación de la canción.
     *
     * @param anyo_creacion El año de creación a asignar a la canción.
     */
    public void setAnyoCreacion(int anyo_creacion) {
        this.anyo_creacion = anyo_creacion;
    }

    /**
     * Obtiene el género musical de la canción como un valor de la enumeración
     * {@link GeneroMusical}.
     *
     * @return El género musical de la canción.
     */
    public GeneroMusical getGenero() {
        return GeneroMusical.fromString(genero);
    }

    /**
     * Establece el género musical de la canción a partir de un valor de la
     * enumeración {@link GeneroMusical}.
     *
     * @param genero El género musical a asignar a la canción.
     */
    public void setGenero(GeneroMusical genero) {
        this.genero = genero.toString();
    }

    /**
     * Obtiene el autor de la canción.
     *
     * @return El autor de la canción.
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * Establece el autor de la canción.
     *
     * @param autor El autor a asignar a la canción.
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * Representa la canción en formato de cadena de texto.
     *
     * <p>
     * Devuelve una cadena que contiene todos los atributos de la canción en el
     * siguiente formato: "Identificador: [identificador] - Título: [titulo] -
     * Duración: [duracion] segundos - Año de creación: [anyo] - Género:
     * [genero] - Autor: [autor].</p>
     *
     * @return La representación en cadena de la canción.
     */
    @Override
    public String toString() {
        return "Identificador: " + this.getIdentificador() + " - Título: " + this.getTitulo()
                + " - Duración: " + this.getDuracion() + " segundos - Año de creación: "
                + this.getAnyoCreacion() + " - Género: " + this.getAnyoCreacion()
                + " - Autor: " + (autor != null ? autor.getNombre() : "Desconocido");
    }

}
