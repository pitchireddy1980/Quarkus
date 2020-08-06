package org.acme.quarkus.sample.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@Liveness
@ApplicationScoped
public class MemoryHealthCheck implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("memoryhealth liveness check");

		long freeMemory = Runtime.getRuntime().freeMemory();
		if (freeMemory >= 1024000000)
			responseBuilder.up();
		else
			responseBuilder.down().withData("error", "Not enough free memory, please restart application");

		return responseBuilder.build();
	}

}
