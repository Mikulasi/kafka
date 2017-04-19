package com.epam.kafka;

public class ProducerDriver {
    public static void main(String[] args) {
        ProducerImpl impl = new ProducerImpl();
        impl.publishTopic(Integer.parseInt(args[0]));

    }
}
