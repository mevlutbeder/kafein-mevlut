package com.kafeinmevlut.garage.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * @author mevlutbeder
 * @created 02/01/2023 04:33
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Garage API", version = "0.0.1", description = "Garage Application by mevlutbeder", termsOfService = "http://localhost:8080/"))
public class OpenApiConfig {
    public OpenApiConfig() {
    }
}
