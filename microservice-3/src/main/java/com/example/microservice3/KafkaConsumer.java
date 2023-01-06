package com.example.microservice3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    @Autowired
    private final MessageService messageService;
    @KafkaListener(topics = "kafka-service-demo",
            groupId = "messageConfig",
            containerFactory = "messageListener")
    // Method
//    public void consume(MessageDto messageDto) {
//    public void consume(ConsumerRecord<String, MessageDto> record) {
    public void consume(MessageDto messageDto) {
//    public void consume(@Payload MessageDto messageDto) {
//    public void consume(String messageDto) {
        // Print statement
//        MessageDto received = record.value();
//        log.info("message = {}", received);
//        System.out.println("message = " + received);

        messageService.sendToMS1(messageDto);
    }
}

//https://pradeek-mohandas.medium.com/quick-start-kafka-producer-consumer-springboot-with-local-kafka-instance-d2afa7552009
//@KafkaListener(topics = "kafka-service-demo",
//            groupId = "messageConfig",
//            containerFactory = "messageListener")
//@Slf4j
//@Component
//public class KafkaConsumer {
//
//    @KafkaHandler
//    public void consume(MessageDto messageDto, @Header(KafkaHeaders.OFFSET) String offset) {
//        log.info("offset:" + offset + "Incoming info: " + messageDto);
//    }
//}
