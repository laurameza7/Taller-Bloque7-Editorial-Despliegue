package co.edu.ucc.editorial.service;

import co.edu.ucc.editorial.exception.ConflictoException;
import co.edu.ucc.editorial.exception.RecursoNoEncontradoException;
import co.edu.ucc.editorial.model.Libro;
import co.edu.ucc.editorial.repository.PublicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de Negocio: casos de uso de Libro. Aquí viven las reglas de negocio
 * (el Controller no valida reglas; el Repository solo guarda).
 */
@Service
public class LibroService {

    private final PublicacionRepository repositorio;

    public LibroService(PublicacionRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Libro registrar(Libro libro) {
        validarIsbnUnico(libro.getIsbn());
        libro.setId(null);
        libro.setTipo(Libro.TIPO);
        return repositorio.save(libro);
    }

    public List<Libro> listarLibros() {
        return repositorio.findByTipo(Libro.TIPO).stream()
                .map(Libro.class::cast)
                .toList();
    }

    public Libro actualizar(String id, Libro libro) {
        Libro actual = repositorio.findById(id)
                .filter(Libro.class::isInstance)
                .map(Libro.class::cast)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el libro con id " + id));
        if (!actual.getIsbn().equals(libro.getIsbn())) {
            validarIsbnUnico(libro.getIsbn());
        }
        libro.setId(id);
        libro.setTipo(Libro.TIPO);
        return repositorio.save(libro);
    }

    private void validarIsbnUnico(String isbn) {
        if (repositorio.existeLibroConIsbn(isbn)) {
            throw new ConflictoException("Ya existe un libro con el ISBN " + isbn);
        }
    }
}
