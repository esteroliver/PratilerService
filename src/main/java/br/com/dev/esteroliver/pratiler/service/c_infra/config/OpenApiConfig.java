package br.com.dev.esteroliver.pratiler.service.c_infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    private static final String BEARER_AUTH_SCHEME = "bearerAuth";
    private static final String API_TITLE = "Pratiler API";
    private static final String API_VERSION = "1.0.0";

    @Bean
    public OpenAPI customOpenAPI(){
        Info apiInfo = 
            new Info()
                .title(API_TITLE)
                .version(API_VERSION)
                .description("API documentation for the Pratiler project");

        Components components = 
            new Components()
                .addSecuritySchemes(BEARER_AUTH_SCHEME, 
                    new SecurityScheme()
                        .name(BEARER_AUTH_SCHEME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                );

        return new OpenAPI()
            .info(apiInfo)
            .components(components)
            .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH_SCHEME));
    }
}
