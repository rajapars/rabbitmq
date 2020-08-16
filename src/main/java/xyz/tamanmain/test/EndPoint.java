package xyz.tamanmain.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {
	
	protected Connection connection;
	protected Channel channel;
	protected String endPointName;
	
	public EndPoint(String endPointName) throws IOException, TimeoutException {
		this.endPointName = endPointName;
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		connection = factory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(endPointName, false, false, false, null);
	}
	
	public void close() throws IOException, TimeoutException {
		this.channel.close();
		this.connection.close();
	}
	
}
