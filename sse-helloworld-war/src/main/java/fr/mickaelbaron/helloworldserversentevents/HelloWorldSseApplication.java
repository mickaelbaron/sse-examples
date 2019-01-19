package fr.mickaelbaron.helloworldserversentevents;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Mickael BARON (baron.mickael@gmail.com)
 */
@ApplicationPath("api")
public class HelloWorldSseApplication extends ResourceConfig {

	public HelloWorldSseApplication() {
		this.packages("fr.mickaelbaron.helloworldserversentevents");
	}
}
