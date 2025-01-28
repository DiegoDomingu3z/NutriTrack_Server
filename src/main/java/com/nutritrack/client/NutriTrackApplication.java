package com.nutritrack.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NutriTrackApplication {

    public static void main(String[] args) {
        System.out.println("Starting the server on port 8080...");
        System.out.println("In a browser, open the URL:");
        System.out.println("http://localhost:8080/");

        SpringApplication.run(NutriTrackApplication.class, args);
    }

}
