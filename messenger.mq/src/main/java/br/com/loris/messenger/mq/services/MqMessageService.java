package br.com.loris.messenger.mq.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import br.com.loris.messenger.interfaces.domain.Message;
import br.com.loris.messenger.interfaces.services.MessengerService;

@Service
public class MqMessageService implements MessengerService {
	private static final String EXCHANGE_NAME = "messenger";
	
	@Override
	public Message NextMessage() {
		return null;
	}

	@Override
	public Message SaveMessage(Message message) {
		String json;
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			json = ow.writeValueAsString(message);

			
	        ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("localhost");
	        try (Connection connection = factory.newConnection();
	            Channel channel = connection.createChannel()) {
	        	
	            	channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
	           
	            	channel.basicPublish(EXCHANGE_NAME, "", null, json.getBytes(StandardCharsets.UTF_8));
	            System.out.println(" [x] Sent '" + message + "'");
	        } catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return message;
	}
}
