package org.acme.quickstart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.Multi;

@Path("/hello")
public class ReactiveGreetingResource {

    @Inject
    ReactiveGreetingService reactiveGreetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public Uni<String> helloWithName(@PathParam(value="name")String name) {
        return reactiveGreetingService.greeting(name);
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
//    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{count}/{name}")
    public Multi<String> helloWithNameByCount(@PathParam(value="count")int count,@PathParam(value="name")String name) {
        return reactiveGreetingService.greetings(count, name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }
}