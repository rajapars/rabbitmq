package xyz.tamanmain.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class App {
	
	private static final String[] MESSAGES = {"Message A", "Message B", "Message C", "Message D"};
	private static final String QUEUE_NAME = "message queue";
	
	public App() throws IOException, TimeoutException {
		
		Receiver receiver = new Receiver(QUEUE_NAME);
		Thread receiverThread = new Thread(receiver);
		receiverThread.start();
		
		Publisher publisher = new Publisher(QUEUE_NAME);
		for (String message : MESSAGES) {
			publisher.sendMessage(message);
			System.out.println("Sending: '" + message + "'");
		}
	}
	
    public static void main( String[] args ) throws IOException, TimeoutException {
        new App();
    }
    
}
