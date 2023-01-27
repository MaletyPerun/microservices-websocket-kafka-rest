package com.example.microservice3;

import com.example.microservice3.config.KafkaConfig;
import com.example.microservice3.consumer.KafkaConsumer;
import com.example.microservice3.dto.MessageDto;
import com.example.microservice3.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://www.baeldung.com/spring-boot-kafka-testing
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class KafkaConsumerTest {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    @Test
    void consumeTest() {
        MessageDto testMessageDto = new MessageDto();
        testMessageDto.setId(56);
        testMessageDto.setSession(2);
        testMessageDto.setMc1Timestamp(TimeUtil.getDateTime());
        testMessageDto.setMc2Timestamp(TimeUtil.getDateTime().plus(5L, ChronoUnit.SECONDS));
        testMessageDto.setMc3Timestamp(TimeUtil.getDateTime().plus(10L, ChronoUnit.SECONDS));

        producer.sendTestMessage(topic, testMessageDto);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        int id = consumer.getReceived();
        assertEquals(testMessageDto.getId(), id);
    }
}