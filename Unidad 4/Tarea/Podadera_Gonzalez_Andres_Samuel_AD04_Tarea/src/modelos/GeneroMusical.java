package modelos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ANDRÉS SAMUEL PODADERA GONZÁLEZ
 */
public enum GeneroMusical {
    CLASICA("Clásica"),
    JAZZ("Jazz"),
    REGGAETON("Reggaetón"),
    RAP("Rap"),
    POP("Pop"),
    ELECTRONICA("Electrónica"),
    INDIE("Indie");

    private static final GeneroMusical GENERO_POR_DEFECTO = CLASICA;

    private String nombre;

    GeneroMusical(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static GeneroMusical fromString(String texto) {
        for (GeneroMusical genero : GeneroMusical.values()) {
            if (genero.nombre.equalsIgnoreCase(texto)) {
                return genero;
            }
        }
        return GENERO_POR_DEFECTO; // Retorna CLASICA si el valor no es válido
    }

    public static List<String> obtenerTodos() {
        return Arrays.stream(GeneroMusical.values())
                .map(GeneroMusical::toString)
                .collect(Collectors.toList());
    }

}
