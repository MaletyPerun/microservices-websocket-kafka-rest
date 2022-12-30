package com.example.microservice3;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "kafka-service-demo",
            groupId = "messageConfig")
    // Method
    public void consume(String message) {
        // Print statement
        System.out.println("message = " + message);
    }
}
