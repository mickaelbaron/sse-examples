package fr.mickaelbaron.helloworldserversentevents;

import java.net.URI;
import java.time.LocalTime;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
public class HelloWorldSsePostMessageLauncher {

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:9992/api").build();
	}

	public void createTextPlainMessage() {
		Client client = ClientBuilder.newClient();
		Response create = client.target(getBaseURI()).path("sse-broadcast").request()
				.post(Entity.entity("Voici mon message", MediaType.TEXT_PLAIN_TYPE));
		System.out.println(create.getStatusInfo().getReasonPhrase());
	}

	public void createJSONMessage() {
		Message newMessage = new Message();
		newMessage.setContent("Voici mon message");
		newMessage.setTime(LocalTime.now().toString());

		Client client = ClientBuilder.newClient();
		Response create = client.target(getBaseURI()).path("sse-broadcast-json").request()
				.post(Entity.entity(newMessage, MediaType.APPLICATION_JSON_TYPE));
		System.out.println(create.getStatusInfo().getReasonPhrase());
	}

	public static void main(String... args) {
		new HelloWorldSsePostMessageLauncher().createJSONMessage();
		new HelloWorldSsePostMessageLauncher().createTextPlainMessage();
	}
}
