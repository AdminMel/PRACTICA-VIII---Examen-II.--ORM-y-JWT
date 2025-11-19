package com.upiiz.ligas.controllers;

import com.upiiz.ligas.entities.Entrenador;
import com.upiiz.ligas.services.EntrenadorService;
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
@RequestMapping("/api/entrenadores")
@RequiredArgsConstructor
@Tag(name = "entrenador-controller", description = "Operaciones relacionadas con entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    // ==========================
    // GET /api/entrenadores
    // ==========================
    @Operation(
            summary = "Listar entrenadores",
            description = "Devuelve el listado completo de entrenadores registrados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de entrenadores encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Entrenador.class),
                            examples = @ExampleObject(
                                    value = """
                                            [
                                              {
                                                "id": 1,
                                                "nombre": "Pep Guardiola",
                                                "experiencia": 15,
                                                "nacionalidad": "España"
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
                                              "message": "Error al obtener los entrenadores"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<Entrenador>> getAll() {
        return ResponseEntity.ok(entrenadorService.findAll());
    }

    // ==========================
    // POST /api/entrenadores
    // ==========================
    @Operation(
            summary = "Registrar entrenador",
            description = "Crea un nuevo entrenador. Este endpoint requiere autenticación con JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Entrenador creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Entrenador.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Pep Guardiola",
                                              "experiencia": 15,
                                              "nacionalidad": "España"
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
                                              "message": "Los datos del entrenador no son válidos"
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
                                              "message": "Error al crear el entrenador"
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Entrenador> create(@RequestBody Entrenador entrenador) {
        Entrenador creado = entrenadorService.save(entrenador);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
