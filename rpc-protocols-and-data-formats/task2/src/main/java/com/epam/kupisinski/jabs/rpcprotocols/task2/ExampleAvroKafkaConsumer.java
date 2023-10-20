package com.epam.kupisinski.jabs.rpcprotocols.task2;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ExampleAvroKafkaConsumer {

  public static void main(String[] args) {
    ExampleAvroKafkaConsumer consumer = new ExampleAvroKafkaConsumer();
    consumer.subscribeAndProceed();
  }

  public void subscribeAndProceed() {
    Properties config = createAvroKafkaProperties();
    Consumer<String, ExampleRecord> consumer = createConsumer(config);
    consumer.subscribe(Collections.singleton("avro-topic"));

    while (true) {
      ConsumerRecords<String, ExampleRecord> records = consumer.poll(Duration.ofSeconds(1));
      records.forEach(
          record -> {
            for (int i = 0; i < record.value().getPrintNum(); i++) {
              System.out.println(record.value().getMessage());
            }
          });
      consumer.commitAsync();
    }
  }

  private ExampleRecord createRecord(String msg, int num) {
    ExampleRecord record = new ExampleRecord();
    record.setMessage(msg);
    record.setPrintNum(num);
    return record;
  }

  private static Consumer<String, ExampleRecord> createConsumer(Properties config) {
    return new KafkaConsumer<>(config);
  }

  private Properties createAvroKafkaProperties() {
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, "avro-consumer-group");
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
    properties.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
    properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
    return properties;
  }
}
