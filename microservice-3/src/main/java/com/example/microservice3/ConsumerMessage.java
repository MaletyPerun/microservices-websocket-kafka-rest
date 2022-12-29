package com.example.microservice3;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@AllArgsConstructor
@Component
public class ConsumerMessage implements Closeable {

    private String topic = "kafka-service-demo";
    private KafkaConsumer<String, MessageDto> consumer;

    public ConsumerMessage() {
        this.consumer = getConsumer();
//        this.topic = topic;
    }

    public KafkaConsumer<String, MessageDto> getConsumer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "messageConfig");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
        return new KafkaConsumer<>(properties);
    }

    public void resiveMessage() {
        consumer.subscribe(Collections.singleton(topic));
        while (true) {
            ConsumerRecords<String, MessageDto> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, MessageDto> record : records) {
                System.out.println(record.value().getId());
                System.out.println(record.value().getSession_id());
                System.out.println(record.value().getMC1_timestamp());
                System.out.println(record.value().getMC2_timestamp());
            }
        }
//        producer.send(new ProducerRecord<>(topic, messageDto));
//        producer.close();   //всегда явно закрывать, так как это ресурс/поток
    }

    @Override
    public void close() {
        consumer.close();
    }
}
