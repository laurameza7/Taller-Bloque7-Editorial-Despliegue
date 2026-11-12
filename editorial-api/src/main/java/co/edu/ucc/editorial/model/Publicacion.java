package co.edu.ucc.editorial.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Clase abstracta del modelo de dominio (Diagrama 1).
 * Concentra los atributos comunes que heredan Libro y Disco.
 * Todas las publicaciones se guardan en la colección "publicaciones";
 * el campo "tipo" distingue el subtipo (LIBRO, DISCO).
 */
@Document(collection = "publicaciones")
public abstract class Publicacion {

    @Id
    private String id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private LocalDate fechaPublicacion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private BigDecimal precio;

    private String tipo;

    protected Publicacion(String tipo) {
        this.tipo = tipo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDate getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDate fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
