package co.edu.ucc.editorial.service;

import co.edu.ucc.editorial.exception.RecursoNoEncontradoException;
import co.edu.ucc.editorial.model.Publicacion;
import co.edu.ucc.editorial.repository.PublicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de Negocio: casos de uso comunes a todas las publicaciones.
 */
@Service
public class PublicacionService {

    private final PublicacionRepository repositorio;

    public PublicacionService(PublicacionRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Publicacion> listar() {
        return repositorio.findAll();
    }

    public Publicacion buscarPorId(String id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la publicación con id " + id));
    }

    public void eliminar(String id) {
        if (!repositorio.existsById(id)) {
            throw new RecursoNoEncontradoException("No existe la publicación con id " + id);
        }
        repositorio.deleteById(id);
    }
}
