package com.clinic.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.clinic.ClinicManagmentApplication;

/**
 * Configuration de Swagger pour la documentation de l'API.
 */
@SpringBootApplication
public class SwaggerConfig {

    public static void main(String[] args) {
        SpringApplication.run(ClinicManagmentApplication.class, args);
    }

}
