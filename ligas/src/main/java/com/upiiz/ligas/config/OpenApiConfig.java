package com.upiiz.ligas.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Ligas Deportivas",
                version = "1.0",
                description = "API REST segura para gestión de ligas, equipos, jugadores y entrenadores"
        )
)
public class OpenApiConfig {
}
