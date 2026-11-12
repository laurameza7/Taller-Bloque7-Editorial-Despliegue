package co.edu.ucc.editorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la API Editorial.
 * Las tres capas (Controller, Service, Repository) se ejecutan dentro del mismo proceso Java.
 */
@SpringBootApplication
public class EditorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(EditorialApplication.class, args);
    }
}
