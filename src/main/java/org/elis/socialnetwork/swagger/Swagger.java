package org.elis.socialnetwork.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
//                .addSecurityItem(
//                        new SecurityRequirement().addList("Bearer Authentication")
//                )
//                .components(
//                        new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())
//                )
                .info(
                        new Info()
                                .title("SocialNetwork API")
                                .description("API per la gestione del SocialNetwork")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("Isidoro Schifano")
                                                .email("isidoroschifano98@gmail.com")
                                                .url("github.com/isidoroschifano45")
                                )
                                .extensions(Map.of(
                                        "x-contacts", List.of(
                                                Map.of(
                                                        "name", "Isidoro Schifano",
                                                        "email", "isidoroschifano98@gmail.com",
                                                        "url", "github.com/isidoroschifano45"
                                                ),
                                                Map.of(
                                                        "name", "Andrea Passabi",
                                                        "email", "andrepassa@gmail.com",
                                                        "url", "github.com/passa"
                                                ),
                                                Map.of(
                                                        "name", "Luigi Massa",
                                                        "email", "luigi.massa@edu.elis.org"
                                                )
                                        )
                                ))
                                .license(new License().name("Copiright (C) 2025 Social"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Production Server"),
                        new Server().url("http://localhost:8080").description("Development Server")
                ));
    }

//    private SecurityScheme createAPIKeyScheme() {
//        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                .bearerFormat("JWT")
//                .scheme("bearer");
//    }

}
