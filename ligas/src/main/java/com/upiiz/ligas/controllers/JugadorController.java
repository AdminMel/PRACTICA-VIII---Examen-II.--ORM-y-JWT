package com.upiiz.ligas.controllers;

import com.upiiz.ligas.entities.Jugador;
import com.upiiz.ligas.services.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@RequiredArgsConstructor
@Tag(name = "jugador-controller", description = "Operaciones relacionadas con jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    // ==========================
    // GET /api/jugadores
    // ==========================
    @Operation(
            summary = "Listar jugadores",
            description = "Devuelve el listado completo de jugadores registrados en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de jugadores encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Jugador.class),
                            examples = @ExampleObject(
                                    value = """
                                            [
                                              {
                                                "id": 1,
                                                "nombre": "Lionel Messi",
                                                "posicion": "Delantero",
                                                "numero": 10,
                                                "equipo": {
                                                  "id": 1,
                                                  "nombre": "Inter Miami",
                                                  "ciudad": "Miami"
                                                }
                                              }
                                            ]
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "status": "Error interno del servidor",
                                              "message": "Error al obtener los jugadores"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<Jugador>> getAll() {
        return ResponseEntity.ok(jugadorService.findAll());
    }

    // ==========================
    // POST /api/jugadores
    // ==========================
    @Operation(
            summary = "Agregar un jugador",
            description = "Registra un nuevo jugador en el sistema. Este endpoint requiere autenticación con JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Jugador creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Jugador.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Kylian Mbappé",
                                              "posicion": "Delantero",
                                              "numero": 7,
                                              "equipo": {
                                                "id": 2,
                                                "nombre": "PSG",
                                                "ciudad": "París"
                                              }
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos en la solicitud",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "status": "Bad Request",
                                              "message": "Los datos del jugador no son válidos"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado (token faltante o inválido)",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "status": "No autorizado",
                                              "message": "Token JWT inválido o ausente"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "status": "Error interno del servidor",
                                              "message": "Error al crear el jugador"
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Jugador> create(
            @RequestBody Jugador jugador
    ) {
        Jugador creado = jugadorService.save(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // ==========================
    // DELETE /api/jugadores/{id}
    // ==========================
    @Operation(
            summary = "Eliminar jugador",
            description = "Elimina el jugador correspondiente al ID especificado. Requiere token JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Jugador eliminado correctamente",
                    content = @Content // sin body
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Jugador no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "status": "No encontrado",
                                              "message": "No se encontró un jugador con el ID proporcionado"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "No autorizado (token faltante o inválido)",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "status": "No autorizado",
                                              "message": "Token JWT inválido o ausente"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "status": "Error interno del servidor",
                                              "message": "Error al eliminar el jugador"
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jugadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
