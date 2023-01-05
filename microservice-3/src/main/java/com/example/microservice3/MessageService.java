package com.example.microservice3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@AllArgsConstructor
@Slf4j
@AllArgsConstructor
public class MessageService {

    @Autowired
    private final MessageRestController controller;

    public void sendToMS1(MessageDto messageDto) {
        messageDto.setMC3_timestamp(TimeUtil.getDateTime());
        controller.sendToMS1(messageDto);
    }
}

