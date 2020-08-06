package com;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/client")
public class ClientEndpoint {

	@Inject
	@RestClient
	TxrServiceEndpoint txrServiceEndpoint;

	@POST
	@Path("/payment")
	public String doPayment() {

		TxrRequest request = new TxrRequest();
		request.setAmount(1000);
		request.setFromAccountNum("1");
		request.setToAccountNum("2");

		TxrResponse response = txrServiceEndpoint.txr(request);

		System.out.println(response.getMessage());

		return response.getMessage();

	}

}
