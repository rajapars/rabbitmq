package xyz.tamanmain.test.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
	
	private final static String QUEUE_NAME = "message_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		String[] messages = {"Message A", "Message B", "Message C", "Message D"};
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		for (String message : messages ) {
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}
		
		channel.close();
		connection.close();
	}

}
