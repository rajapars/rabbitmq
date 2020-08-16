package xyz.tamanmain.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver extends EndPoint implements Runnable, Consumer {
	
	public Receiver(String endPointName) throws IOException, TimeoutException {
		super(endPointName);
	}
	
	@Override
	public void run() {
		try {
			channel.basicConsume(endPointName, true, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException {
		String message = new String(body);
		System.out.println("Received: '" + message + "'");
	}
	
	@Override
	public void handleConsumeOk(String consumerTag) {
		System.out.println("Consumer " + consumerTag + " registered.");
	}

	@Override
	public void handleCancelOk(String consumerTag) {
		System.out.println("Consumer " + consumerTag + " canceled.");
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
		System.out.println("Consumer " + consumerTag + " trying to cancel.");
	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		System.out.println("Consumer " + consumerTag + " shutdown.");
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
		System.out.println("Consumer " + consumerTag + " recovered.");
	}

}
