package com.example.microservice3.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyCommonErrorHandler implements CommonErrorHandler {

    @Override
    public void handleBatch(Exception thrownException, ConsumerRecords<?, ?> data,
                            Consumer<?, ?> consumer, MessageListenerContainer container, Runnable invokeListener) {
        log.warn("Kafka consumer error handler for message: {}", data.toString());
    }
}
