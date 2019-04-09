package com.store.Kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.javatuples.Pair;

import com.store.DTO.DeliveryUpdateDTO;
import com.store.DTO.DeliveryUpdateMessageDTO;
import com.store.DTO.ProductSoldMessageDTO;
import com.store.Model.Client;
import com.store.Model.Provider;
import com.store.Model.Seller;

public class KafkaConnect {

	private static Properties getProducerProperties() {
		Properties producerProperties = new Properties();
		producerProperties.put("bootstrap.servers", "localhost:29092");
		producerProperties.put("acks", "all");
		boolean sslEnable = false;
		if (sslEnable ) {
			producerProperties.put("ssl.truststore.location","/etc/kafka/secrets/kafka.producer.truststore.jks");
			producerProperties.put("ssl.truststore.password","confluent");
			producerProperties.put("ssl.keystore.location","/etc/kafka/secrets/kafka.producer.keystore.jks");
			producerProperties.put("ssl.keystore.password","confluent");
			producerProperties.put("ssl.key.password","confluent");
			producerProperties.put("security.protocol","SSL");
		}
		// producerProperties.put("key.serializer",
		// "org.apache.kafka.common.serialization.StringSerializer");
		// producerProperties.put("value.serializer",
		// "org.apache.kafka.common.serialization.StringSerializer");
		return producerProperties;
	}

	private static Properties getConsumerProperties(String groupId) throws UnknownHostException {
		Properties config = new Properties();
		config.put("client.id", InetAddress.getLocalHost().getHostName());
		config.put("group.id", groupId);
		config.put("bootstrap.servers", "localhost:29092");
		config.put("request.timeout.ms", "1");
		boolean sslEnable = true;
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

	public void sendProductSoldMessage(String topic, List<ProductSoldMessageDTO> productSold) throws Exception{
		KafkaProducer<String, ProductSoldMessageDTO> kafkaProducer = new KafkaProducer<>(getProducerProperties(),
				new StringSerializer(), new KafkaJsonSerializer());
		try {
			for (ProductSoldMessageDTO product : productSold) {
				// Send a message
				ProducerRecord<String, ProductSoldMessageDTO> record = new ProducerRecord<String, ProductSoldMessageDTO>(
						topic, "0", product);
				kafkaProducer.send(record, new Callback() {
					public void onCompletion(RecordMetadata metadata, Exception e) {
						if (e != null)
							System.out.println(e);
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			kafkaProducer.close();
		}
	}

	public JsonNode getProductSoldMessage(String topicName_Orders) throws Exception {
		KafkaConsumer<String, ProductSoldMessageDTO> consumer = null;
		JsonNode valueToTree;
		try {
			// config.put("key.deserializer", StringDeserializer.class.getName());
			// config.put("value.deserializer", StringDeserializer.class.getName());
			consumer = new KafkaConsumer<String, ProductSoldMessageDTO>(getConsumerProperties("group"+topicName_Orders),
					new StringDeserializer(),
					new KafkaJsonDeserializer<ProductSoldMessageDTO>(ProductSoldMessageDTO.class));
			consumer.subscribe(Arrays.asList(topicName_Orders));
			ConsumerRecords<String, ProductSoldMessageDTO> records = consumer.poll(Duration.ofMinutes(1));
			ArrayList<ProductSoldMessageDTO> productSoldList = new ArrayList<ProductSoldMessageDTO>();
			for (ConsumerRecord<String, ProductSoldMessageDTO> record : records)
				productSoldList.add(record.value());
			ObjectMapper mapper = new ObjectMapper();
			valueToTree = mapper.valueToTree(productSoldList);
			
			//jsonInString = mapper.writeValueAsString(productSoldList);
		} catch (Exception e) {
			throw e;
		} finally {
			consumer.close();
		}
		return valueToTree;
	}

	public void sendDeliveryUpdateMessage(Pair<Client, Seller> clientSeller, DeliveryUpdateDTO saleDTO, Provider provider) throws Exception {
		KafkaProducer<String, DeliveryUpdateMessageDTO> kafkaProducer = new KafkaProducer<>(getProducerProperties(),
				new StringSerializer(), new KafkaJsonSerializer());
		try {
				// Send a message
				ProducerRecord<String, DeliveryUpdateMessageDTO> record = new ProducerRecord<String, DeliveryUpdateMessageDTO>(
						provider.getTopicName_Shipping(), "0", new DeliveryUpdateMessageDTO(saleDTO.getTrackNumber(),saleDTO.getStatus(), saleDTO.getMessage(),
								saleDTO.getStatusDateTime(), saleDTO.getOrderCode(), clientSeller.getValue0().getId(), saleDTO.getProductCode(),provider.getCNPJ()));
		
				kafkaProducer.send(record, new Callback() {
					public void onCompletion(RecordMetadata metadata, Exception e) {
						if (e != null)
							try {
								throw e;
							} catch (Exception e1) {
								e1.printStackTrace();
							}
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
