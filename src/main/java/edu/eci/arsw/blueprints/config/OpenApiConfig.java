package edu.eci.arsw.blueprints.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info()
                .title("ARSW Blueprints API")
                .version("v1")
                .description("Blueprints Laboratory (Java 21 / Spring Boot 3.3.x)")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("La Ley del Corazon")
                        .email("alison.calderrama-m@mail.escuelaing.edu.co"))
                        
                .license(new io.swagger.v3.oas.models.info.License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}
