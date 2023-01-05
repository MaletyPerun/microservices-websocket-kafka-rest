//package com.example.microservice2;
//
//import com.fasterxml.jackson.databind.JsonSerializable;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//import org.springframework.stereotype.Component;
//
//import java.io.Closeable;
//import java.util.Properties;
//
//@AllArgsConstructor
//@Component
//@Slf4j
//public class ProducerMessage implements Closeable {
//
//    private String topic = "kafka-service-demo";
//    private KafkaProducer<String, MessageDto> producer;
//
//    public ProducerMessage() {
//        this.producer = getProducer();
////        this.topic = topic;
//    }
//
//    public KafkaProducer<String, MessageDto> getProducer() {
//        Properties properties = new Properties();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "messageConfig");
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
////        properties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.microservice2.MessageDto");
//
//
//        return new KafkaProducer<>(properties);
//    }
//
//    public void sendMessage(MessageDto messageDto) {
//        producer.send(new ProducerRecord<>(topic, messageDto));
//        log.info("Send message in Kafka with id = {}", messageDto.getId());
////        producer.close();   //всегда явно закрывать, так как это ресурс/поток
//    }
//
//    @Override
//    public void close() {
//        producer.close();
//    }
//}