package edu.eci.arsw.blueprints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación Spring Boot Blueprints.
 */
@SpringBootApplication
public class BlueprintsApplication {
    /**
     * Arranca la aplicación.
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(BlueprintsApplication.class, args);
    }
}
