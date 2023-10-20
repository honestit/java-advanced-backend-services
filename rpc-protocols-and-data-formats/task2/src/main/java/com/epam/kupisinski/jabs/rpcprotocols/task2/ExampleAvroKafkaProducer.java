package com.epam.kupisinski.jabs.rpcprotocols.task2;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ExampleAvroKafkaProducer {

  public static void main(String[] args) {
    ExampleAvroKafkaProducer producer = new ExampleAvroKafkaProducer();
    producer.sendMessageToPrintInNumOfTimes("test", 10);
  }

  public void sendMessageToPrintInNumOfTimes(String msg, int num) {
    Properties config = createAvroKafkaProperties();
    ExampleRecord record = createRecord(msg, num);
    Producer<String, SpecificRecord> producer = createProducer(config);
    producer.send(new ProducerRecord<>("avro-topic", null, record));
    producer.flush();
    producer.close();
  }

  private ExampleRecord createRecord(String msg, int num) {
    ExampleRecord record = new ExampleRecord();
    record.setMessage(msg);
    record.setPrintNum(num);
    return record;
  }

  private static Producer<String, SpecificRecord> createProducer(Properties config) {
    Producer<String, SpecificRecord> producer = new KafkaProducer<>(config);
    return producer;
  }

  private Properties createAvroKafkaProperties() {
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
    return properties;
  }
}
