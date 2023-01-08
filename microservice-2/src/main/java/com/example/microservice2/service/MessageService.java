package com.example.microservice2.service;

import com.example.microservice2.dto.MessageDto;
import com.example.microservice2.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    private String topic = "kafka-service-demo";

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public void sendMessage(MessageDto received) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("error with sleep = {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        received.setMC2_timestamp(TimeUtil.getDateTime());
        ProducerRecord<String, MessageDto> receivedProducerRecord = new ProducerRecord<>(topic, received);
        log.info("send message via kafka = {}", receivedProducerRecord);
        this.kafkaTemplate.send(receivedProducerRecord);
    }
}

