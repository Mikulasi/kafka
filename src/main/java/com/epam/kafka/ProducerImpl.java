package com.epam.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerImpl {

    private static final String TOPIC = "fibonacci";
    private final Properties prop = new Properties();
    public static Producer<Integer, Integer> producer;

    public ProducerImpl() {
        prop.put("bootstrap.servers", "localhost:9092");
        prop.put("acks", "all");
        prop.put("auto.commit.interval.ms", "1000");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        producer = new KafkaProducer<>(prop);
    }

    private int fibonacci(int n) {
        if (n == 0) {
            return 1;
        }
        int prev = 1;
        int curr = 1;
        for (int i = 2; i <= n; ++i) {
            int temp = curr;
            curr += prev;
            prev = temp;
        }
        return curr;
    }

    public void publishTopic(int number) {

        for (int i = 1; i <= number; i++) {
            System.out.println(fibonacci(i));
            producer.send(new ProducerRecord<>(TOPIC,fibonacci(number)));
            producer.close();
        }
    }
}
