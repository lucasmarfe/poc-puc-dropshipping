package com.ecommerce.getSalesKafka.get_sales_kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.store.DTO.ProductSoldMessageDTO;
import com.store.Kafka.KafkaJsonDeserializer;

public class GetSalesKafkaConsumer {
		
public static void main(String[] args) throws UnknownHostException {
		
		//config.put("key.deserializer", StringDeserializer.class.getName());
		//config.put("value.deserializer", StringDeserializer.class.getName());
		List<String> topicsList = processArguments(args);
		
		KafkaConsumer<String, ProductSoldMessageDTO> consumer = new KafkaConsumer<String, ProductSoldMessageDTO>(getConsumerProperties(false),new StringDeserializer(), new KafkaJsonDeserializer<ProductSoldMessageDTO>(ProductSoldMessageDTO.class));
		consumer.subscribe(topicsList);
		try {
			  while (true) {
			    ConsumerRecords<String, ProductSoldMessageDTO> records = consumer.poll(Duration.ofMinutes(10));
			    ArrayList<ProductSoldMessageDTO> listMessages = new ArrayList<ProductSoldMessageDTO>();
			    for (ConsumerRecord<String, ProductSoldMessageDTO> record : records) {
			    	System.out.println("Product Sold Message received:");
			    	System.out.println(record.value().toString());
			    }
			  }
			}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			  consumer.close();
			}
	}

public static Properties getConsumerProperties(Boolean sslEnable) throws UnknownHostException {
	Properties config = new Properties();
	config.put("client.id", InetAddress.getLocalHost().getHostName());
	config.put("group.id", "new");
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

private static List<String> processArguments(String[] args) {
	System.out.println("Consuming Topics:");
	List<String> topicsList = new ArrayList<String>();
    for (String arg : args) {
        System.out.println("\t" + arg);
        topicsList.add(arg);
        
    }
    return topicsList;
}
}
