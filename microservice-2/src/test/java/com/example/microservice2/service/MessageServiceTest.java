package com.example.microservice2.service;

import com.example.microservice2.dto.MessageDto;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka
class MessageServiceTest {

    @Autowired
    private KafkaConsumer consumer;

    private MessageService messageService;

    @Test
    void testSending() {
        MessageDto testMessage = new MessageDto();
        testMessage.setId(56);
        messageService.sendMessage(testMessage);
        int id = consumer.getId();
        assertEquals(56, id);
    }
}