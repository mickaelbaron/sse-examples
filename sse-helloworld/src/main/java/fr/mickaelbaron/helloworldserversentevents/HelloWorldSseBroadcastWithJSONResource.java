package fr.mickaelbaron.helloworldserversentevents;

import java.time.LocalTime;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@Singleton
@Path("sse-broadcast-json")
public class HelloWorldSseBroadcastWithJSONResource {

	private Sse sse;

	private SseBroadcaster broadcaster;

	public HelloWorldSseBroadcastWithJSONResource(@Context final Sse sse) {
		System.out.println("HelloWorldSseBroadcastResource.HelloWorldSseBroadcastResource()");

		this.sse = sse;
		this.broadcaster = sse.newBroadcaster();
	}

	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void subscribeToBroadcast(@Context SseEventSink eventSink) {
		this.broadcaster.register(eventSink);
	}

	@POST
	public String broadcastMessage(String message) {
		Message newMessage = new Message();
		newMessage.setContent(message);
		newMessage.setTime(LocalTime.now().toString());

		OutboundSseEvent event = sse.newEventBuilder().name("add-message").mediaType(MediaType.APPLICATION_JSON_TYPE)
				.data(Message.class, newMessage).comment("This is a new message.").id(LocalTime.now().toString())
				.build();

		broadcaster.broadcast(event);

		return "Message '" + message + "' has been broadcast.";
	}
}
