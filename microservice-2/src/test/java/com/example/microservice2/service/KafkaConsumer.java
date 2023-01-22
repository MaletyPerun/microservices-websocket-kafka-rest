package com.example.microservice2.service;//package com.example.microservice3;

import com.example.microservice2.dto.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private int id;

    @KafkaListener(topics = "kafka-service-demo")
    public void receive(MessageDto received) {
        id = received.getId();
    }

    public int getId() {
        return id;
    }
}
