package fr.mickaelbaron.helloworldserversentevents;

import java.time.LocalTime;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@Singleton
@Path("sse-broadcast")
public class HelloWorldSseBroadcastResource {

	private Sse sse;

	private SseBroadcaster broadcaster;

	public HelloWorldSseBroadcastResource(@Context final Sse sse) {
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
		OutboundSseEvent event = sse.newEventBuilder().name("add-message").data(message)
				.comment("This is a new message.").id(LocalTime.now().toString()).build();
		broadcaster.broadcast(event);

		return "Message '" + message + "' has been broadcast.";
	}
}
