package org.acme.quarkus.sample.service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.enterprise.context.ApplicationScoped;
import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.acme.quarkus.sample.model.Account;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

@ApplicationScoped
public class TxrServiceImpl implements TxrService {

//	@Gauge(name = "peakOfTxrs",unit = MetricUnits.SECONDS,description = "Highest number os txrs")
//	@Timed(name = "txr time check",description = "how much time it takes to dlo txr")
//	@Counted(absolute = true, description = "txr count")
//	@Timeout(250)
//	@Fallback(fallbackMethod = "doLocalTxr")
//	@Retry(maxRetries = 3,retryOn = {RuntimeException.class})
//	@CircuitBreaker(successThreshold = 5, requestVolumeThreshold = 4, failureRatio = 0.75, delay = 3000,failOn = {RuntimeException.class})

	@RolesAllowed(value = { "MANAGER", "USER" })
	@Transactional
	public void txr(double amount, String fromAccNumber, String toAccNumber) {

		System.out.println("txr");

		// randomSleep();
		// possibleFailure();

		Account fromAccount = Account.findById(fromAccNumber);
		Account toAccount = Account.findById(toAccNumber);

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		Account.update("balance=?1 where num=?2", fromAccount.getBalance(), fromAccount.getNum());
		Account.update("balance=?1 where num=?2", toAccount.getBalance(), toAccount.getNum());

		// writeSomeLogging();
	}

	@Asynchronous
	@Bulkhead(value = 5, waitingTaskQueue = 10)
	public Future<String> writeSomeLogging() {
		System.out.println("LOG");
		return CompletableFuture.completedFuture("OK");
	}

	public void doLocalTxr(double amount, String fromAccNumber, String toAccNumber) {
		System.out.println("Local Txr");
	}

	private void possibleFailure() {
		if (new Random().nextFloat() < 0.5f)
			throw new RuntimeException("resource failure");
	}

	private void randomSleep() {
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt(4));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
