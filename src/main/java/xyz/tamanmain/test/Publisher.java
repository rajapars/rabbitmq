package xyz.tamanmain.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher extends EndPoint {
	
	public Publisher(String endPointName) throws IOException, TimeoutException {
		super(endPointName);
	}
	
	public void sendMessage(String message) throws IOException {
		channel.basicPublish("", endPointName, null, message.getBytes());
	}
	
}
