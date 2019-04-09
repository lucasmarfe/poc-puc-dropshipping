package com.store.rest.jerseyclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class Consumer {
	public static void main(String[] args) throws UnknownHostException {
		Properties config = new Properties();
		config.put("client.id", InetAddress.getLocalHost().getHostName());
		config.put("group.id", "foo");
		config.put("bootstrap.servers", "localhost:29092");
		config.put("key.deserializer", StringDeserializer.class.getName());
		config.put("value.deserializer", StringDeserializer.class.getName());
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(config);
		consumer.subscribe(Arrays.asList("bar"));
		try {
			  while (true) {
			    ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
			    for (ConsumerRecord<String, String> record : records)
			      System.out.println(record.offset() + ": " + record.value());
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
