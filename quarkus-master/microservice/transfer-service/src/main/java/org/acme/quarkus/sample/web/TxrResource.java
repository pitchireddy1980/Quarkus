package org.acme.quarkus.sample.web;

import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.quarkus.sample.service.TxrService;


@Path("/api/txr")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TxrResource {

	@Inject
	TxrService txrService;

	@POST
	public TxrResponse doTxr(TxrRequest request) {
		txrService.txr(request.getAmount(), request.getFromAccountNum(), request.getToAccountNum());
		TxrResponse response = new TxrResponse();
		response.setTxnId(UUID.randomUUID().toString());
		response.setMessage("successfull");
		return response;
	}

}
