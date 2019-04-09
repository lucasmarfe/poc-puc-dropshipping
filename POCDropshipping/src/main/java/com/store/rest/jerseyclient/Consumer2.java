package com.store.rest.jerseyclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.store.DTO.ProductSoldMessageDTO;
import com.store.Kafka.KafkaJsonDeserializer;
import com.store.Kafka.KafkaJsonSerializer;

public class Consumer2 {
	public static void main(String[] args) throws UnknownHostException {
		Properties config = new Properties();
		config.put("client.id", InetAddress.getLocalHost().getHostName());
		config.put("group.id", "foo");
		config.put("bootstrap.servers", "localhost:29092");
		boolean sslEnable = false;
		if (sslEnable ) {
			config.put("group.id", "ssl-host");
			config.put("ssl.truststore.location",
					"/home/lucas/docker/cp-docker-images/examples/kafka-cluster-ssl/secrets/kafka.consumer.truststore.jks");
			config.put("ssl.truststore.password", "confluent");
			config.put("ssl.keystore.location",
					"/home/lucas/docker/cp-docker-images/examples/kafka-cluster-ssl/secrets/kafka.consumer.keystore.jks");
			config.put("ssl.keystore.password", "confluent");
			config.put("ssl.key.password", "confluent");
			config.put("ssl.endpoint.identification.algorithm", "");
			config.put("security.protocol", "SSL");
		}
		//config.put("key.deserializer", StringDeserializer.class.getName());
		//config.put("value.deserializer", StringDeserializer.class.getName());
		KafkaConsumer<String, ProductSoldMessageDTO> consumer = new KafkaConsumer<String, ProductSoldMessageDTO>(config,new StringDeserializer(), new KafkaJsonDeserializer<ProductSoldMessageDTO>(ProductSoldMessageDTO.class));
		consumer.subscribe(Arrays.asList("bar"));
		try {
			  while (true) {
			    ConsumerRecords<String, ProductSoldMessageDTO> records = consumer.poll(Duration.ofMinutes(60));
			    for (ConsumerRecord<String, ProductSoldMessageDTO> record : records)
			      System.out.println(record.offset() + ": " + record.value().getProductCode());
			  }
			} finally {
			  consumer.close();
			}
		
		/*Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("group.id", "test");
		props.setProperty("enable.auto.commit", "true");
		props.setProperty("auto.commit.interval.ms", "1000");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("test"));
		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records)
					System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(),
							record.value());

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			consumer.close();
		}*/
	}
}
