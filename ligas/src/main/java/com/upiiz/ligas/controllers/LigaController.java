package com.upiiz.ligas.controllers;

import com.upiiz.ligas.entities.Liga;
import com.upiiz.ligas.services.LigaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operaciones relacionadas con Ligas.
 */
@RestController
@RequestMapping("/api/ligas")
@RequiredArgsConstructor
@Tag(name = "liga-controller", description = "Operaciones relacionadas con ligas")
public class LigaController {

    private final LigaService ligaService;

    @GetMapping
    @Operation(summary = "Listar ligas")
    public ResponseEntity<List<Liga>> listarLigas() {
        return ResponseEntity.ok(ligaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una liga por ID")
    public ResponseEntity<Liga> obtenerLiga(@PathVariable Long id) {
        return ResponseEntity.ok(ligaService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear liga")
    public ResponseEntity<Liga> crearLiga(@RequestBody Liga liga) {
        return ResponseEntity.ok(ligaService.create(liga));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar liga")
    public ResponseEntity<Liga> actualizarLiga(@PathVariable Long id,
                                               @RequestBody Liga liga) {
        return ResponseEntity.ok(ligaService.update(id, liga));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar liga")
    public ResponseEntity<Void> eliminarLiga(@PathVariable Long id) {
        ligaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
