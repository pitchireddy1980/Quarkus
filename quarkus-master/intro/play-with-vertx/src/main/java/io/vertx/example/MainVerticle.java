package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {

        DeploymentOptions opts=new DeploymentOptions().setWorker(true).setInstances(8);
        vertx.deployVerticle("io.vertx.example.HelloVerticle",opts);
         
        //vertx.deployVerticle(new HelloVerticle());

        // #1
        // vertx.createHttpServer().requestHandler(req -> {
        // req.response().end("Hello vert.x world");
        // }).listen(8080);

        // #2
        // vertx.createHttpServer().requestHandler(req -> {
        // if (req.path().startsWith("/api/v1/hello"))
        // req.response().end("Hello vert.x world");
        // }).listen(8080);

        // #3
        Router router = Router.router(vertx);

        // router.get("/api/v1/hello").handler(ctx -> {
        // ctx.request().response().end("Hello vertx world!");
        // });

        // router.get("/api/v1/hello/:name").handler(ctx -> {
        // String name = ctx.pathParam("name");
        // ctx.request().response().end(String.format("hello %s", name));
        // });

        // #4

        router.get("/api/v1/hello").handler(this::helloVertx);
        router.get("/api/v1/hello/:name").handler(this::helloName);

        vertx.createHttpServer().requestHandler(router).listen(8080);

    }

    void helloVertx(RoutingContext ctx) {
        // ctx.request().response().end("Hello vertx world!");
        vertx.eventBus().request("hello.vertx.addr", "", reply -> {
            ctx.request().response().end((String) reply.result().body());
        });
    }

    void helloName(RoutingContext ctx) {
        String name = ctx.pathParam("name");
        // ctx.request().response().end(String.format("hello %s", name));
        vertx.eventBus().request("hello.named.addr", name, reply -> {
            ctx.request().response().end((String) reply.result().body());
        });
    }

}
