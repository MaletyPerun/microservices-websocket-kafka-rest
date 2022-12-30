package com.example.microservice3;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/spring-boot-kafka-consumer-example/amp/
@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public ConsumerFactory<String, MessageDto> consumerFactory() {
        // Creating a Map of string-object pairs
        Map<String, Object> config = new HashMap<>();
        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "messageConfig");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }
    // Creating a Listener
    public ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
