package com.upiiz.ligas.controllers;

import com.upiiz.ligas.entities.Liga;
import com.upiiz.ligas.services.LigaService;
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
@RequestMapping("/api/ligas")
@RequiredArgsConstructor
@Tag(name = "liga-controller", description = "Operaciones relacionadas con ligas")
public class LigaController {

    private final LigaService ligaService;

    // ==========================
    // GET /api/ligas
    // ==========================
    @Operation(
            summary = "Listar ligas",
            description = "Devuelve el listado completo de ligas registradas."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de ligas encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Liga.class),
                            examples = @ExampleObject(
                                    value = """
                                            [
                                              {
                                                "id": 1,
                                                "nombre": "Liga MX",
                                                "pais": "México",
                                                "temporadaActual": "2024-2025"
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
                                              "message": "Error al obtener las ligas"
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<Liga>> getAll() {
        return ResponseEntity.ok(ligaService.findAll());
    }

    // ==========================
    // POST /api/ligas
    // ==========================
    @Operation(
            summary = "Crear liga",
            description = "Crea una nueva liga. Este endpoint requiere autenticación con JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Liga creada correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Liga.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "id": 1,
                                              "nombre": "Liga MX",
                                              "pais": "México",
                                              "temporadaActual": "2024-2025"
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
                                              "message": "Los datos de la liga no son válidos"
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
                                              "message": "Error al crear la liga"
                                            }
                                            """
                            )
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Liga> create(@RequestBody Liga liga) {
        Liga creada = ligaService.save(liga);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
}
