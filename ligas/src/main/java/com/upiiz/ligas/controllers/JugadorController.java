package com.upiiz.ligas.controllers;

import com.upiiz.ligas.entities.Jugador;
import com.upiiz.ligas.services.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operaciones relacionadas con Jugadores.
 */
@RestController
@RequestMapping("/api/jugadores")
@RequiredArgsConstructor
@Tag(name = "jugador-controller", description = "Operaciones relacionadas con jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    @GetMapping
    @Operation(summary = "Listar jugadores")
    public ResponseEntity<List<Jugador>> listarJugadores() {
        return ResponseEntity.ok(jugadorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un jugador por ID")
    public ResponseEntity<Jugador> obtenerJugador(@PathVariable Long id) {
        return ResponseEntity.ok(jugadorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Agregar un jugador")
    public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
        return ResponseEntity.ok(jugadorService.create(jugador));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar jugador")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable Long id,
                                                     @RequestBody Jugador jugador) {
        return ResponseEntity.ok(jugadorService.update(id, jugador));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar jugador")
    public ResponseEntity<Void> eliminarJugador(@PathVariable Long id) {
        jugadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
