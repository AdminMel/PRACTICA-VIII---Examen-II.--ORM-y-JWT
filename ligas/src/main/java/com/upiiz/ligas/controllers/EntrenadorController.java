package com.upiiz.ligas.controllers;

import com.upiiz.ligas.entities.Entrenador;
import com.upiiz.ligas.services.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operaciones relacionadas con Entrenadores.
 */
@RestController
@RequestMapping("/api/entrenadores")
@RequiredArgsConstructor
@Tag(name = "entrenador-controller", description = "Operaciones relacionadas con entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @GetMapping
    @Operation(summary = "Listar entrenadores")
    public ResponseEntity<List<Entrenador>> listarEntrenadores() {
        return ResponseEntity.ok(entrenadorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un entrenador por ID")
    public ResponseEntity<Entrenador> obtenerEntrenador(@PathVariable Long id) {
        return ResponseEntity.ok(entrenadorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar entrenador")
    public ResponseEntity<Entrenador> crearEntrenador(@RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(entrenadorService.create(entrenador));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar entrenador")
    public ResponseEntity<Entrenador> actualizarEntrenador(@PathVariable Long id,
                                                           @RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(entrenadorService.update(id, entrenador));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entrenador")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Long id) {
        entrenadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
