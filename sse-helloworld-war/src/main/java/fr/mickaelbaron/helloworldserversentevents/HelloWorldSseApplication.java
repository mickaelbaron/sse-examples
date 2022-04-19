package fr.mickaelbaron.helloworldserversentevents;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@ApplicationPath("api")
public class HelloWorldSseApplication extends ResourceConfig {

	public HelloWorldSseApplication() {
		this.packages("fr.mickaelbaron.helloworldserversentevents");
	}
}
