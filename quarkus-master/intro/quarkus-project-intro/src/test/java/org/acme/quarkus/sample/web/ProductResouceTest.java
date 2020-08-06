package org.acme.quarkus.sample.web;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import javax.json.Json;
import javax.json.JsonObject;

@QuarkusTest
public class ProductResouceTest {

	@Test
	public void testPostProduct() {
		
		JsonObject obj = Json.createObjectBuilder().add("id", "1").add("name", "product-1").build();
		
		given()
		 .contentType("application/json")
		 .body(obj.toString())
		.when()
		 .post("/api/products")
		 .then()
		  .statusCode(201);
		
	}

}
