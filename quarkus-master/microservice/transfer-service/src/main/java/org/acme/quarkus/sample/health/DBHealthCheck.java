package org.acme.quarkus.sample.health;

import java.io.IOException;
import java.net.Socket;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

//@Health
//@ApplicationScoped
public class DBHealthCheck implements HealthCheck {
	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("db connection health check");
		try {
			serverListening("localhost", 3306);
			responseBuilder.up();
		} catch (Exception e) {
			responseBuilder.down().withData("error", e.getMessage());
		}
		return responseBuilder.build();
	}

	private void serverListening(String host, int port) throws IOException {
		Socket s = new Socket(host, port);
		s.close();
	}

}
