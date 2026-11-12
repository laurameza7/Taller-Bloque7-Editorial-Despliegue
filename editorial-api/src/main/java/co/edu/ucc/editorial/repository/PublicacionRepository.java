package co.edu.ucc.editorial.repository;

import co.edu.ucc.editorial.model.Publicacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Capa de Datos. Un solo repositorio para toda la jerarquía de Publicacion.
 * Hereda save, findAll, findById y deleteById de MongoRepository.
 * Es la única clase que habla con MongoDB.
 */
@Repository
public interface PublicacionRepository extends MongoRepository<Publicacion, String> {

    /** Consulta por tipo (recomendación acertada de la sección 9). */
    List<Publicacion> findByTipo(String tipo);

    /** ¿Ya existe un libro con este ISBN? (regla de negocio de LibroService). */
    @Query(value = "{ 'tipo': 'LIBRO', 'isbn': ?0 }", exists = true)
    boolean existeLibroConIsbn(String isbn);
}
