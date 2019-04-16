package com.ecommerce.updateUsersKafka.update_users_kafka;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.ecommerce.Db.MySQL;
import com.store.DTO.DeliveryUpdateMessageDTO;
import com.store.Kafka.KafkaJsonDeserializer;

public class UpdateShippmentInformationKafkaConsumer {
	
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
	
	public static void main(String[] args) throws UnknownHostException {
		
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
			}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			  consumer.close();
			}
	}

	private static void processAndUpdateEveryoneInvolved(ArrayList<DeliveryUpdateMessageDTO> listMessages) throws SQLException {
		for(DeliveryUpdateMessageDTO msg : listMessages) {
			ArrayList<String> emails = MySQL.getEmails(msg.getClientId());
			sendEmailClient(emails.get(0),msg);
			if(emails.get(1) != null) {
				sendEmailSeller(emails.get(1),msg);
			}
		}
		
	}

	private static void sendEmailSeller(String toEmail, DeliveryUpdateMessageDTO msg) {
		String from = "ecommerce@gmail.com";

	      String host = "localhost";

	      Properties properties = System.getProperties();

	      properties.setProperty("mail.smtp.host", host);
	      
	      Session session = Session.getDefaultInstance(properties);
	      
	      try {
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

	          // Set Subject: header field
	          message.setSubject("Seller - Update shippment of order: " + msg.getOrderCode());

	          // Now set the actual message
	          message.setText("The provider updated the status of your order.\nNew status: " + msg.getStatus() + " at: " + msg.getStatusDateTime() + "\n Track number: " + msg.getTrackNumber());

	          // Send message
	          Transport.send(message);
	          System.out.println("Message sent successfully....");
	       } catch (MessagingException mex) {
	          mex.printStackTrace();
	       }
		
	}

	private static void sendEmailClient(String toEmail, DeliveryUpdateMessageDTO msg) {
	      String from = "ecommerce@gmail.com";

	      String host = "localhost";

	      Properties properties = System.getProperties();

	      properties.setProperty("mail.smtp.host", host);
	      
	      Session session = Session.getDefaultInstance(properties);
	      
	      try {
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

	          // Set Subject: header field
	          message.setSubject("Update shippment of order: " + msg.getOrderCode());

	          // Now set the actual message
	          message.setText("The provider updated the status of your order.\nNew status: " + msg.getStatus() + " at: " + msg.getStatusDateTime() + "\n Track number: " + msg.getTrackNumber());

	          // Send message
	          Transport.send(message);
	          System.out.println("Message sent successfully....");
	       } catch (MessagingException mex) {
	          mex.printStackTrace();
	       }
		
	}
}
