package org.acme.quarkus.sample.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.quarkus.sample.model.Product;
import org.acme.quarkus.sample.repository.ProductRepository;

@ApplicationScoped
public class ProductService {

//	@Inject
//	ProductRepository productRepository;

	@Transactional
	public void addNewProduct(Product product) {
		product.persist(); // Active Record design pattern
	}

}
