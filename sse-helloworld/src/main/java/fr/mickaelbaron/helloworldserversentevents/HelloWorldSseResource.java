package fr.mickaelbaron.helloworldserversentevents;

import java.time.LocalTime;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.OutboundSseEvent;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@Path("sse")
public class HelloWorldSseResource {

	public HelloWorldSseResource() {
		System.out.println("HelloWorldSseResource.getHelloWorldWithSimpleSSE()");
	}
	
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void getHelloWorldWithSimpleSSE(@Context SseEventSink eventSink, @Context Sse sse) {		
		OutboundSseEvent event = sse.newEventBuilder()
				.name("add-message")
				.data("HelloWorld")
				.comment("This is a new HelloWorld message and continue the communication.")
				.id("123")
				.reconnectDelay(1000)
				.build();
		eventSink.send(event);
	}
	
	@Path("andstop")
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void getHelloWorldWithSimpleSSEAndClose(@Context SseEventSink eventSink, @Context Sse sse) {
		OutboundSseEvent event = sse.newEventBuilder()
				.name("add-message")
				.data("HelloWorld")
				.comment("This is a new HelloWorld message and terminate the communication.")
				.id("123")
				.reconnectDelay(5000)
				.build();
		eventSink.send(event);
		eventSink.close();
	}
	
	@Path("withstreaming")
	@GET
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void getHelloWorldAndTimeWithSimpleSSE(@Context SseEventSink eventSink, @Context Sse sse) {
		new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				try { Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				OutboundSseEvent event = sse.newEventBuilder()
						.name("add-message")
						.id(Integer.toString(i))
						.data("HelloWorld" + LocalTime.now())
						.comment("This is a new HelloWorld message published each 1 second.")
						.reconnectDelay(1000)
						.build();	
				if (!eventSink.isClosed()) {
					eventSink.send(event);					
				} else {
					return;
				}
			}
			eventSink.close();
		}).start();			
	}
}