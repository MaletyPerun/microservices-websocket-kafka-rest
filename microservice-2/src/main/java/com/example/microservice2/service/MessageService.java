package com.example.microservice2.service;

import com.example.microservice2.dto.MessageDto;
import com.example.microservice2.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

    @Value("${spring.kafka.topic.name}")
    private String topic;

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public void sendMessage(MessageDto received) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("error with sleep = {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        received.setMc2Timestamp(TimeUtil.getDateTime());
        ProducerRecord<String, MessageDto> receivedProducerRecord = new ProducerRecord<>(topic, received);
        log.info("send message via kafka = {}", receivedProducerRecord);
        kafkaTemplate.send(receivedProducerRecord);
    }
}

