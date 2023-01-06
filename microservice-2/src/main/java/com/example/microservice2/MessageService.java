package com.example.microservice2;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
@Slf4j
public class MessageService {

    private String topic = "kafka-service-demo";

    @Autowired
    private KafkaTemplate<String, MessageDto> kafkaTemplate;

    // TODO: 06.01.2023 переделать под kafkatemplate

    public void sendMessage(MessageDto msg) {
        msg.setMC2_timestamp(TimeUtil.getDateTime());
//        LOGGER.info(String.format("\n ===== Producing message in JSON ===== \n"+msg));
        Message<MessageDto> message = MessageBuilder
                .withPayload(msg)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();
        ProducerRecord<String, MessageDto> received = new ProducerRecord<>(topic, msg);
//        this.kafkaTemplate.send(topic, msg);
        this.kafkaTemplate.send(received);
    }
}

