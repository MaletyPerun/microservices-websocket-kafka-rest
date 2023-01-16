package com.example.microservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example")
public class Microservice1Application {
    // TODO: 11.01.2023 по каждому сервису собраться работающий образ
    // TODO: 11.01.2023 создать головной docker-compose и запустить все образы в одном контейнере

    // TODO: 13.01.2023 удалось запустить контейнер с образами: создать build-ы каждого сервиса
    // TODO: 13.01.2023 подготовить dockerfile на каждый микросервис
    // TODO: 13.01.2023 подготовить docker-compose

    // TODO: 13.01.2023 проблема: какой-то сервис отваливается, скорее всего второй, так как не логировался
    public static void main(String[] args) {
        SpringApplication.run(Microservice1Application.class, args);
    }

}
