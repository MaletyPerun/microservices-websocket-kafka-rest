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

//    private final ProducerMessage producerMessage;
//
//    public void updateMessage(MessageDto messageDto) {
////        Message updated = MessageUtil.dtoToMessage(messageDto);
//        messageDto.setMC2_timestamp(TimeUtil.getDateTime());
////        producerMessage.sendMessage(messageDto);
//    }
//
//    public MessageDto sendTestMessage() {
//        MessageDto testMessage = MessageDto.builder()
//                .id(156)
//                .session_id(256)
//                .MC2_timestamp(TimeUtil.getDateTime())
//                .build();
//        producerMessage.sendMessage(testMessage);
//        return testMessage;
//    }


//    https://stackoverflow.com/questions/51688924/spring-kafka-the-class-is-not-in-the-trusted-packages
//    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);
//    private static final String TOPIC = "final-topic";
    private String topic = "kafka-service-demo";

    @Autowired
    private KafkaTemplate<String, MessageDto> kafkaTemplate;

    public void sendTestMessage(MessageDto msg) {
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

