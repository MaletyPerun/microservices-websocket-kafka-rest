package com.example.microservice3;

import com.example.microservice3.dto.MessageDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
    
    @Autowired
    private KafkaTemplate<String, MessageDto> kafkaTemplate;

    public void sendTestMessage(String topic, MessageDto received) {
        ProducerRecord<String, MessageDto> receivedProducerRecord = new ProducerRecord<>(topic, received);
        kafkaTemplate.send(receivedProducerRecord);
    }
}
