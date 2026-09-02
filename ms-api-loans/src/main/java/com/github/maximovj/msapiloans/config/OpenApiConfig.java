package com.github.maximovj.msapiloans.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:ms-api-loans}")
    private String appName;

    @Bean
    @Profile({"dev", "test", "default"})
    public OpenAPI devOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Loans")
                        .description("Microservicio para la gestión de préstamos")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Víctor J.")
                                .email("maximovj@outlook.com")
                                .url("https://github.com/maximovj/crud-microservicio-multiplataforma"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8007")
                                .description("Servidor de Desarrollo")
                ));
    }

    @Bean
    @Profile("prod")
    public OpenAPI prodOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Loans - Producción")
                        .description("Microservicio para la gestión de préstamos en producción")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Víctor J.")
                                .email("maximovj@outlook.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server()
                                .url("https://api.produccion.com")
                                .description("Servidor de Producción")
                ));
    }
}