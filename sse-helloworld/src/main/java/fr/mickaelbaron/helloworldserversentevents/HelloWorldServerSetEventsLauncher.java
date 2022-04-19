package fr.mickaelbaron.helloworldserversentevents;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.core.UriBuilder;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
public class HelloWorldServerSetEventsLauncher {

	public static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/api").port(9992).build();
	}

	public static void main(String[] args) throws IOException {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.registerClasses(HelloWorldSseResource.class, HelloWorldSseBroadcastResource.class,
				HelloWorldSseBroadcastWithJSONResource.class);
		StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./static");

		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);
		server.getServerConfiguration().addHttpHandler(staticHttpHandler);
		server.start();

		System.out.println(String.format("Jersey app started available at %s\nHit enter to stop it...", BASE_URI));
		System.in.read();
		server.shutdownNow();
	}
}

// curl http://localhost:9992/api/sse
// curl http://localhost:9992/api/sse/andstop
// curl http://localhost:9992/api/sse/withstreaming
// curl http://localhost:9992/api/sse-broadcast
// curl --request POST --data 'My Data' http://localhost:9992/api/sse-broadcast -v
// curl http://localhost:9992/api/sse-broadcast-json
// curl --request POST --data 'My Data' http://localhost:9992/api/sse-broadcast-json -v
