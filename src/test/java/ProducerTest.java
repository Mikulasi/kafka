import com.epam.kafka.ProducerImpl;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ProducerTest {

    private MockProducer<Integer, Integer> producer;
    private Properties prop;
    private ProducerImpl impl;
    public static final String TOPIC = "fibonacci";

    @Before
    public void setUp() throws Exception {

        prop = new Properties();
        prop.put("bootstrap.servers", "localhost:9092");
        prop.put("acks", "all");
        prop.put("auto.commit.interval.ms", "1000");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        producer = new MockProducer<>(true, new IntegerSerializer(), new IntegerSerializer());

    }

    @Test
    public void test_producer() {
        impl = new ProducerImpl();
        impl.producer = producer;
        impl.publishTopic(8);

        List<ProducerRecord<Integer, Integer>> history = producer.history();
        List<ProducerRecord<Integer, Integer>> records = Arrays.asList(
                new ProducerRecord<Integer, Integer>(TOPIC, 1),
                new ProducerRecord<Integer, Integer>(TOPIC, 2),
                new ProducerRecord<Integer, Integer>(TOPIC, 3),
                new ProducerRecord<Integer, Integer>(TOPIC, 5),
                new ProducerRecord<Integer, Integer>(TOPIC, 8),
                new ProducerRecord<Integer, Integer>(TOPIC, 13),
                new ProducerRecord<Integer, Integer>(TOPIC, 21),
                new ProducerRecord<Integer, Integer>(TOPIC, 34));

        Assert.assertArrayEquals("Sent didn't match expected", new List[]{history}, new List[]{records});

    }

    @After
    public void tearDown() throws Exception {
        producer.close();

    }
}
