package com.example.eurekaclient;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaProducerDemo {
    private final static String BOOTSTRAP_SERVERS = "kafka:9092";

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    public void sendMessage(String action, Ticket ticket) throws Exception {
        final Producer<Long, String> producer = createProducer();
        try {
            final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(action,
                    action + " " + ticket.toString());

            RecordMetadata metadata = producer.send(record).get();
            System.out.printf("sent record(key=%s value=%s) meta(partition=%d, offset=%d)",
                    record.key(), record.value(), metadata.partition(), metadata.offset());

        } finally {
            producer.flush();
            producer.close();
        }
    }
}

