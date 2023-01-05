package com.example.microservice3;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/spring-boot-kafka-consumer-example/amp/
@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public ConsumerFactory<String, MessageDto> consumerFactory() {

        // TODO: 03.01.2023 https://docs.spring.io/spring-kafka/reference/html/#json-serde
        // читать и испытать

//        https://stackoverflow.com/questions/51688924/spring-kafka-the-class-is-not-in-the-trusted-packages
        JsonDeserializer<MessageDto> deserializer = new JsonDeserializer<>(MessageDto.class);
//        deserializer.addTrustedPackages("com.example.microservice3.MessageDto");
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("com.example.microservice2.MessageDto");
//        deserializer.setUseTypeMapperForKey(true);

        // Creating a Map of string-object pairs
        Map<String, Object> config = new HashMap<>();
        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "messageConfig");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

//        https://stackoverflow.com/questions/51023281/kafka-consumer-error
//        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

//        https://stackoverflow.com/questions/70252047/this-error-handler-cannot-process-serializationexceptions-directly-please-con
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
//        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MessageDto.class);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.microservice3.MessageDto");

//        https://www.stackchief.com/blog/Spring%20Boot%20Kafka%20Consumer%20JSON%20Example
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
//        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, MessageDto.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
//        config.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.example.MyKey");
        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.microservice3.MessageDto");
//        config.put(JsonSerializer.TYPE_MAPPINGS, "MessageDto:com.example.microservice3.MessageDto");


//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return new DefaultKafkaConsumerFactory<>(props);



//        https://gitter.im/spring-projects/spring-kafka?at=5e63f4e9e203784a55926c35
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return new DefaultKafkaConsumerFactory<>(config);
        return new DefaultKafkaConsumerFactory<>(config);


//        return new DefaultKafkaConsumerFactory<>(config);
//        https://www.geeksforgeeks.org/spring-boot-consume-json-object-from-kafka-topics/?ref=rp
//        return new DefaultKafkaConsumerFactory<>(
//                config, new StringDeserializer(),
//                new JsonDeserializer<>(MessageDto.class));
////                deserializer);
//                https://www.skyer9.pe.kr/wordpress/?p=1614
//                new ErrorHandlingDeserializer(new JsonDeserializer<>(MessageDto.class)));
    }

    // Creating a Listener
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageDto> messageListener() {
        ConcurrentKafkaListenerContainerFactory<String, MessageDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
//        https://docs.spring.io/spring-kafka/reference/html/#messaging-message-conversion
//        factory.setMessageConverter(new JsonMessageConverter());
        return factory;
    }
}
