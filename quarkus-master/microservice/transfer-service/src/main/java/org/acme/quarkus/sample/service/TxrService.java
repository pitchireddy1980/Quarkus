package org.acme.quarkus.sample.service;

public interface TxrService {

	void txr(double amount, String fromAccNumber, String toAccNumber);

}
