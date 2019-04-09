package com.store.Kafka;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class Producer {

	protected static Boolean sendMessage(Properties properties) {
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		try {
			/*final ProducerRecord<String, String> record = new ProducerRecord<String, String>("test",
					Integer.toString(i), Integer.toString(i));
			producer.send(record, new Callback() {
				public void onCompletion(RecordMetadata metadata, Exception e) {
					if (e != null)
						System.out.println(e);
				}
			});*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
		return false;
	}
}