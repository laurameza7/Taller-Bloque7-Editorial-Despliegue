package co.edu.ucc.editorial.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Subclase de Publicacion (generalización). Solo declara sus atributos propios.
 */
public class Disco extends Publicacion {

    public static final String TIPO = "DISCO";

    private int duracionMinutos;

    @NotBlank(message = "El formato es obligatorio")
    private String formato;

    public Disco() {
        super(TIPO);
    }

    public int getDuracionMinutos() { return duracionMinutos; }
    public void setDuracionMinutos(int duracionMinutos) { this.duracionMinutos = duracionMinutos; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }
}
