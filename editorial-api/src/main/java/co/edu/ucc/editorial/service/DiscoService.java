package co.edu.ucc.editorial.service;

import co.edu.ucc.editorial.exception.RecursoNoEncontradoException;
import co.edu.ucc.editorial.exception.ReglaNegocioException;
import co.edu.ucc.editorial.model.Disco;
import co.edu.ucc.editorial.repository.PublicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de Negocio: casos de uso de Disco.
 */
@Service
public class DiscoService {

    private final PublicacionRepository repositorio;

    public DiscoService(PublicacionRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Disco registrar(Disco disco) {
        validarDuracion(disco.getDuracionMinutos());
        disco.setId(null);
        disco.setTipo(Disco.TIPO);
        return repositorio.save(disco);
    }

    public List<Disco> listarDiscos() {
        return repositorio.findByTipo(Disco.TIPO).stream()
                .map(Disco.class::cast)
                .toList();
    }

    public Disco actualizar(String id, Disco disco) {
        boolean existe = repositorio.findById(id).filter(Disco.class::isInstance).isPresent();
        if (!existe) {
            throw new RecursoNoEncontradoException("No existe el disco con id " + id);
        }
        validarDuracion(disco.getDuracionMinutos());
        disco.setId(id);
        disco.setTipo(Disco.TIPO);
        return repositorio.save(disco);
    }

    private void validarDuracion(int minutos) {
        if (minutos <= 0) {
            throw new ReglaNegocioException("La duración del disco debe ser mayor que cero minutos");
        }
    }
}
