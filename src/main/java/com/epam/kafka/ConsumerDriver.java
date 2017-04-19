package com.epam.kafka;

public class ConsumerDriver {
    public static void main(String[] args) {
        ConsumerImpl consumer = new ConsumerImpl();
        consumer.retrieveMessage();
    }
}
