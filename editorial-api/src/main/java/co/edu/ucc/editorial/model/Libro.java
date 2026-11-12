package co.edu.ucc.editorial.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * Subclase de Publicacion (generalización). Solo declara sus atributos propios.
 */
public class Libro extends Publicacion {

    public static final String TIPO = "LIBRO";

    @NotBlank(message = "El ISBN es obligatorio")
    private String isbn;

    @Positive(message = "El número de páginas debe ser mayor que cero")
    private int numeroPaginas;

    public Libro() {
        super(TIPO);
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getNumeroPaginas() { return numeroPaginas; }
    public void setNumeroPaginas(int numeroPaginas) { this.numeroPaginas = numeroPaginas; }
}
