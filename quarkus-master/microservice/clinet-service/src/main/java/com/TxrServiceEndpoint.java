package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Produces("application/json")
@Consumes("application/json")
@Path("/api/txr")
public interface TxrServiceEndpoint {

	@POST
	TxrResponse txr(TxrRequest request);

}
