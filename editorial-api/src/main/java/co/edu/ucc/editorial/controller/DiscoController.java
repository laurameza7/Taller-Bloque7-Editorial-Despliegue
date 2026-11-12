package co.edu.ucc.editorial.controller;

import co.edu.ucc.editorial.model.Disco;
import co.edu.ucc.editorial.service.DiscoService;
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
 * Capa de Presentación: valida el formato básico (@Valid) y delega en DiscoService.
 */
@RestController
@RequestMapping("/api/discos")
public class DiscoController {

    private final DiscoService discoService;

    public DiscoController(DiscoService discoService) {
        this.discoService = discoService;
    }

    /** POST /api/discos -> 201 Created | 400 */
    @PostMapping
    public ResponseEntity<Disco> registrar(@Valid @RequestBody Disco disco) {
        Disco creado = discoService.registrar(disco);
        return ResponseEntity.created(URI.create("/api/discos/" + creado.getId())).body(creado);
    }

    /** GET /api/discos -> 200 OK */
    @GetMapping
    public ResponseEntity<List<Disco>> listar() {
        return ResponseEntity.ok(discoService.listarDiscos());
    }

    /** PUT /api/discos/{id} -> 200 OK | 400 | 404 */
    @PutMapping("/{id}")
    public ResponseEntity<Disco> actualizar(@PathVariable String id, @Valid @RequestBody Disco disco) {
        return ResponseEntity.ok(discoService.actualizar(id, disco));
    }
}
