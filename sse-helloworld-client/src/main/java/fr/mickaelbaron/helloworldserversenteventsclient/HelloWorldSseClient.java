package fr.mickaelbaron.helloworldserversenteventsclient;

import java.util.function.Consumer;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.sse.InboundSseEvent;
import jakarta.ws.rs.sse.SseEventSource;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 * 
 * Several parameter arguments: sse, sse-broadcast and sse-broadcast-json
 */
public class HelloWorldSseClient {

	private static final String url = "http://localhost:9992/api";

	public static void main(String... args) throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url).path(args.length == 1 ? args[0] : "sse");
		System.out.println(target.getUri());
		
		try (SseEventSource eventSource = SseEventSource.target(target).build()) {
			eventSource.register(onEvent, onError, onComplete);
			eventSource.open();
			System.out.println("Wainting for incoming event ...");
			// Consuming events for one hour
			Thread.sleep(60 * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		client.close();
		System.out.println("End");
	}

	// A new event is received
	private static Consumer<InboundSseEvent> onEvent = (inboundSseEvent) -> {
		String data = inboundSseEvent.readData();
		System.out.println(data);
	};

	// Error
	private static Consumer<Throwable> onError = (throwable) -> {
		throwable.printStackTrace();
	};

	// Connection close and there is nothing to receive
	private static Runnable onComplete = () -> {
		System.out.println("Done!");
	};
}
