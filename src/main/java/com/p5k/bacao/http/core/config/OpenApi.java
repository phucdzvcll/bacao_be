package com.p5k.bacao.http.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Ba Cao",
                version = "${api.version}",
                description = "Ba Cao",
                license = @License(name = "Apache 2.0"),
                contact = @Contact(name = "Body Sugar", email = "txgiaphuc@gmail.com")))
@Configuration
public class OpenApi {

    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .openapi("3.0.1")
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .description("Provide the JWT token. JWT token can be obtained from the Login API.")
                                                .bearerFormat("JWT")));
    }

}