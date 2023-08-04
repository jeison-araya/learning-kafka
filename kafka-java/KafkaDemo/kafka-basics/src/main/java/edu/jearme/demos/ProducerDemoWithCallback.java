package edu.jearme.demos;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithCallback.class.getSimpleName());

    private static Properties buildProperties() {
        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty("batch.size", "400");

        return properties;
    }

    private static String buildStringFromMetadata(RecordMetadata metadata) {
        return "Received new metadata \n" +
                "Topic: " + metadata.topic() + "\n" +
                "Partition: " + metadata.partition() + "\n" +
                "Offset: " + metadata.offset() + "\n" +
                "Timestamp: " + metadata.timestamp();
    }

    public static void main(String[] args) {
        KafkaProducer<String, String> producer = new KafkaProducer<>(buildProperties());

        for(int j=0; j<10; j++) {
            for (int i = 0; i<30; i++) {
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test", "Hello world " + i);
                producer.send(producerRecord, (metadata, exception) -> {
                    if (exception == null) {
                        log.info(buildStringFromMetadata(metadata));
                    } else {
                        log.error("Error while producing", exception);
                    }
                });
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        producer.flush();
        producer.close();
    }
}
