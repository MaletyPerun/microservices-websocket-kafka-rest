package com.example.microservice3;

import com.example.microservice3.consumer.KafkaConsumer;
import com.example.microservice3.dto.MessageDto;
import com.example.microservice3.util.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://www.baeldung.com/spring-boot-kafka-testing
@SpringBootTest
@DirtiesContext
@EmbeddedKafka
class KafkaConsumerTest {

    @Autowired
    private KafkaConsumer consumer;

    @Autowired
    private KafkaProducer producer;
    // FIXME: 20.01.2023 при использовании @TestConfiguration KafkaConfigProducer выпадает ошибка:
//    class com.example.microservice3.dto.MessageDto cannot be cast to class java.lang.String (com.example.microservice3.dto.MessageDto is in unnamed module of loader 'app'; java.lang.String is in module java.base of loader 'bootstrap')
    // FIXME: 20.01.2023 какую аннотацию использовать?

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
        
        int id = consumer.getReceived();
        
        assertEquals(testMessageDto.getId(), id);
    }
}