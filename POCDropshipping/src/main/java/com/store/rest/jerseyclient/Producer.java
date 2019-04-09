package com.store.rest.jerseyclient;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.store.DTO.ProductSoldMessageDTO;
import com.store.Kafka.KafkaJsonSerializer;

public class Producer {
	private static Scanner in;
	
	private static Properties getProducerSSLProperties() {
		Properties producerProperties = new Properties();
		producerProperties.put("bootstrap.servers", "localhost:29092");
		if (false) {
			//producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			//producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			producerProperties.put("ssl.endpoint.identification.algorithm", "");
			producerProperties.put("ssl.truststore.location",
					"/home/lucas/docker/cp-docker-images/examples/kafka-cluster-ssl/secrets/kafka.producer.truststore.jks");
			producerProperties.put("ssl.truststore.password", "confluent");
			producerProperties.put("ssl.keystore.location",
					"/home/lucas/docker/cp-docker-images/examples/kafka-cluster-ssl/secrets/kafka.producer.keystore.jks");
			producerProperties.put("ssl.keystore.password", "confluent");
			producerProperties.put("ssl.key.password", "confluent");
			producerProperties.put("security.protocol", "SSL");
		}
		return producerProperties;
	}
	
	private static Properties getProducerProperties() {
		Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:29092");
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return properties;
	}

	public static void main(String[] argv) throws Exception {
		Properties properties = getProducerSSLProperties();

		sendStringMessage(getProducerProperties());
	}

	private static void sendStringMessage(Properties properties) {
		KafkaProducer<String, String> producer = new KafkaProducer<String,String>(properties);
        try{
            for(int i = 0; i < 5; i++){
                System.out.println(i);
                
                final ProducerRecord<String, String> record = new ProducerRecord<String, String>("bar", Integer.toString(i), Integer.toString(i));
				producer.send(record, new Callback() {
                	  public void onCompletion(RecordMetadata metadata, Exception e) {
                		    if (e != null)
                		    	System.out.println(e);
                		  }
                		});
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        	producer.close();
        }
	}
	
	public static void sendProductSoldMessage() throws Exception{
		KafkaProducer<String, ProductSoldMessageDTO> kafkaProducer = new KafkaProducer<>(getProducerSSLProperties(),
				new StringSerializer(), new KafkaJsonSerializer());
		ProductSoldMessageDTO psold = new ProductSoldMessageDTO("Test", "Ordcod", new Date(), 2, (float) 0.3, "", "", "", "", "", "", "", "ultimo");
		try {
				// Send a message
				ProducerRecord<String, ProductSoldMessageDTO> record = new ProducerRecord<String, ProductSoldMessageDTO>(
						"bar", "0", psold);
				kafkaProducer.send(record, new Callback() {
					public void onCompletion(RecordMetadata metadata, Exception e) {
						if (e != null)
							System.out.println(e);
					}
				});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			kafkaProducer.close();
		}
	}

	
}