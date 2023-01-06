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
        log.info("received mes id = {}", messageDto.getId());
        log.info("received mes session_id = {}", messageDto.getSession_id());
        log.info("received mes time MS1 = {}", messageDto.getMC1_timestamp());
        log.info("received mes time MS2 = {}", messageDto.getMC2_timestamp());
        log.info("received mes time MS3 = {}", messageDto.getMC3_timestamp());
        log.info("received mes time end = {}", messageDto.getEnd_timestamp());
        controller.sendToMS1(messageDto);
    }
}

