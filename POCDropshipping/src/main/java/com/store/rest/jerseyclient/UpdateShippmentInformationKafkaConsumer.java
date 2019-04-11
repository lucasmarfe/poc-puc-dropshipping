package com.store.rest.jerseyclient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.store.DTO.DeliveryUpdateMessageDTO;
import com.store.Kafka.KafkaJsonDeserializer;

public class UpdateShippmentInformationKafkaConsumer {
	
	public static Properties getConsumerProperties(Boolean sslEnable) throws UnknownHostException {
		Properties config = new Properties();
		config.put("client.id", InetAddress.getLocalHost().getHostName());
		config.put("group.id", "foo");
		config.put("bootstrap.servers", "localhost:29092");
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
		return config;
	}
	
	public static void main(String[] args) throws UnknownHostException {
		
		//config.put("key.deserializer", StringDeserializer.class.getName());
		//config.put("value.deserializer", StringDeserializer.class.getName());
		KafkaConsumer<String, DeliveryUpdateMessageDTO> consumer = new KafkaConsumer<String, DeliveryUpdateMessageDTO>(getConsumerProperties(false),new StringDeserializer(), new KafkaJsonDeserializer<DeliveryUpdateMessageDTO>(DeliveryUpdateMessageDTO.class));
		consumer.subscribe(Arrays.asList("provider1_shipping","provider2_shipping","provider3_shipping","provider4_shipping"));
		try {
			  while (true) {
			    ConsumerRecords<String, DeliveryUpdateMessageDTO> records = consumer.poll(Duration.ofMinutes(60));
			    ArrayList<DeliveryUpdateMessageDTO> listMessages = new ArrayList<DeliveryUpdateMessageDTO>();
			    for (ConsumerRecord<String, DeliveryUpdateMessageDTO> record : records) {
			    	listMessages.add(record.value());
			    }
			    processAndUpdateEveryoneInvolved(listMessages);
			  }
			} finally {
			  consumer.close();
			}
	}

	private static void processAndUpdateEveryoneInvolved(ArrayList<DeliveryUpdateMessageDTO> listMessages) {
		// TODO Auto-generated method stub
		
	}
}
