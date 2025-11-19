package com.upiiz.ligas.controllers;

import com.upiiz.ligas.entities.Equipo;
import com.upiiz.ligas.services.EquipoService;
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
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
@Tag(name = "equipo-controller", description = "Operaciones relacionadas con equipos")
public class EquipoController {

    private final EquipoService equipoService;

    // ==========================
    // GET /api/equipos
    // ==========================
    @Operation(
            summary = "Listar equipos",
            description = "Devuelve el listado completo de equipos registrados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de equipos encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Equipo.class),
                            examples = @ExampleObject(
                                    value = """
                                            [
                                              {
                                                "id": 1,
                                                "nombre": "Tigres UPIIZ",
                                                "ciudad": "Zacatecas"
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
                                              "message": "Error al obtener los equipos"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<Equipo>> getAll() {
        return ResponseEntity.ok(equipoService.findAll());
    }

    // ==========================
    // POST /api/equipos
    // ==========================
    @Operation(
            summary = "Crear equipo",
            description = "Crea un nuevo equipo. Este endpoint requiere autenticación con JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Equipo creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Equipo.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Tigres UPIIZ",
                                              "ciudad": "Zacatecas"
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
                                              "message": "Los datos del equipo no son válidos"
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
                                              "message": "Error al crear el equipo"
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Equipo> create(@RequestBody Equipo equipo) {
        Equipo creado = equipoService.save(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // ==========================
    // DELETE /api/equipos/{id}
    // ==========================
    @Operation(
            summary = "Eliminar equipo",
            description = "Elimina el equipo correspondiente al ID especificado. Requiere token JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Equipo eliminado correctamente",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Equipo no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "status": "No encontrado",
                                              "message": "No se encontró un equipo con el ID proporcionado"
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
                                              "message": "Error al eliminar el equipo"
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
