package org.acme.quarkus.sample.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.acme.quarkus.sample.model.Product;

@ApplicationScoped
public class ProductRepository {

	@Inject
	EntityManager entityManager;

	@Transactional
	public void save(Product product) {
		entityManager.persist(product);
	}

}
