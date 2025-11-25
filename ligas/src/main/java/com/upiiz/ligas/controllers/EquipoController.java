package com.upiiz.ligas.controllers;

import com.upiiz.ligas.entities.Equipo;
import com.upiiz.ligas.services.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operaciones relacionadas con Equipos.
 */
@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
@Tag(name = "equipo-controller", description = "Operaciones relacionadas con equipos")
@CrossOrigin(origins = "*")
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    @Operation(summary = "Listar equipos")
    public ResponseEntity<List<Equipo>> listarEquipos() {
        return ResponseEntity.ok(equipoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por ID")
    public ResponseEntity<Equipo> obtenerEquipo(@PathVariable Long id) {
        return ResponseEntity.ok(equipoService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear equipo")
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        return ResponseEntity.ok(equipoService.create(equipo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar equipo")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id,
                                                   @RequestBody Equipo equipo) {
        return ResponseEntity.ok(equipoService.update(id, equipo));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar equipo")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

