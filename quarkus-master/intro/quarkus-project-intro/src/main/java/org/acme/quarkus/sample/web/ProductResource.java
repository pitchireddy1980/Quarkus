package org.acme.quarkus.sample.web;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.quarkus.sample.model.Product;
import org.acme.quarkus.sample.service.ProductService;

@Path("/api/products")
public class ProductResource {

	@Inject
	ProductService productService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(Product product) {
		productService.addNewProduct(product);
		return Response
				.ok(product)
				.status(201).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll(@QueryParam(value = "name") String name) {
		if(name!=null)
			return Product.list("name", name);
		return Product.listAll();
	}
	
	
	
}
