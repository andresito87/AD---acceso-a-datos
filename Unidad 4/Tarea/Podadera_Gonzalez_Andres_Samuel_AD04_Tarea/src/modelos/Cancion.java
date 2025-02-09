package modelos;

import java.util.UUID;

/**
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

    public Cancion(String titulo, int duracion, int anyo_creacion, String genero, Autor autor) {
        this.identificador = UUID.randomUUID().toString(); // generar id único
        this.titulo = titulo;
        this.duracion = duracion;
        this.anyo_creacion = anyo_creacion;
        this.genero = genero;
        this.autor=autor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAnyoCreacion() {
        return anyo_creacion;
    }

    public void setAnyoCreacion(int anyo_creacion) {
        this.anyo_creacion = anyo_creacion;
    }

    public GeneroMusical getGenero() {
        return GeneroMusical.fromString(genero);
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero.toString();
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Identificador: " + this.getIdentificador() + " - Título: " + this.getTitulo()
                + " - Duración: " + this.getDuracion() + " segundos - Año de creación: "
                + this.getAnyoCreacion() + " - Género: " + this.getAnyoCreacion()
                + " - Autor: " + (autor != null ? autor.getNombre() : "Desconocido");
    }

}
