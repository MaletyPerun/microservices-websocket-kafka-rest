package com.example.microservice3.consumer;

import com.example.microservice3.dto.MessageDto;
import com.example.microservice3.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private MessageDto received;
    private final MessageService messageService;
    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "messageConfig",
            containerFactory = "messageListener")
    public void consume(MessageDto received) {
        log.info("take dto from kafka-producer via kafka-consumer = {}", received.toString());
        this.received = received;
        messageService.sendDtoToMS1(received);
    }

    public int getReceived() {
        return received.getId();
    }
}

