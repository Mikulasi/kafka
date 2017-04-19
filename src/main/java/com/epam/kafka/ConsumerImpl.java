package com.epam.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerImpl {

    private static final String TOPIC = "fibonacci";
    private final Properties prop = new Properties();
    private static Consumer<Integer, Integer> consumer;

    public ConsumerImpl() {
        prop.put("bootstrap.servers", "localhost:9092");
        prop.put("metadata.broker.list", "localhost:9092");
        prop.put("acks", "all");
        prop.put("auto.commit.interval.ms", "1000");
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        consumer = new KafkaConsumer<>(prop);
    }

    public void retrieveMessage() {
        consumer.subscribe(Arrays.asList(TOPIC));
        for (ConsumerRecord<Integer, Integer> record : consumer.poll(1000)) {
            int inputNumber = record.value();
            System.out.println(inputNumber);
        }
    }
}
