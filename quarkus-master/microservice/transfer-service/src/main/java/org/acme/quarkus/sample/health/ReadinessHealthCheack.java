package org.acme.quarkus.sample.health;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadinessHealthCheack implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("FileSystem readiness check");

		boolean tmpFileExists = Files.exists(Paths.get("/tmp/tmp.lck"));
		if (!tmpFileExists)
			responseBuilder.up();
		else
			responseBuilder.down().withData("error", "Lock file detected");

		return responseBuilder.build();
	}

}
