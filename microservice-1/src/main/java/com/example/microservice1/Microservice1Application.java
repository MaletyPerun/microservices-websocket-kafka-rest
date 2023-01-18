package com.example.microservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Microservice1Application {
    // TODO: 11.01.2023 создать головной docker-compose и запустить все образы в одном контейнере

    public static void main(String[] args) {
        SpringApplication.run(Microservice1Application.class, args);
    }

}
