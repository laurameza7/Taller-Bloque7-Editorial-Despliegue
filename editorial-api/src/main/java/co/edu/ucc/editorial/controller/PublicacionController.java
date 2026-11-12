package co.edu.ucc.editorial.controller;

import co.edu.ucc.editorial.model.Publicacion;
import co.edu.ucc.editorial.service.PublicacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Capa de Presentación: recibe HTTP y delega en PublicacionService.
 */
@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    /** GET /api/publicaciones -> 200 OK */
    @GetMapping
    public ResponseEntity<List<Publicacion>> listar() {
        return ResponseEntity.ok(publicacionService.listar());
    }

    /** GET /api/publicaciones/{id} -> 200 OK | 404 */
    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(publicacionService.buscarPorId(id));
    }

    /** DELETE /api/publicaciones/{id} -> 204 No Content | 404 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        publicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
