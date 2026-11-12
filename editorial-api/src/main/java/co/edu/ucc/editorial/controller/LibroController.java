package co.edu.ucc.editorial.controller;

import co.edu.ucc.editorial.model.Libro;
import co.edu.ucc.editorial.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * Capa de Presentación: valida el formato básico (@Valid) y delega en LibroService.
 */
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    /** POST /api/libros -> 201 Created | 400 | 409 */
    @PostMapping
    public ResponseEntity<Libro> registrar(@Valid @RequestBody Libro libro) {
        Libro creado = libroService.registrar(libro);
        return ResponseEntity.created(URI.create("/api/libros/" + creado.getId())).body(creado);
    }

    /** GET /api/libros -> 200 OK */
    @GetMapping
    public ResponseEntity<List<Libro>> listar() {
        return ResponseEntity.ok(libroService.listarLibros());
    }

    /** PUT /api/libros/{id} -> 200 OK | 400 | 404 */
    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable String id, @Valid @RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.actualizar(id, libro));
    }
}
