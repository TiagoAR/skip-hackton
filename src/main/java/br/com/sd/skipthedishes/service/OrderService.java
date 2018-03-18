package br.com.sd.skipthedishes.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.sd.skipthedishes.model.Order;

@Service
public class OrderService extends GenericService<Order> {

	@PersistenceContext()
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Class<Order> getEntityClass() {
		return Order.class;
	}
}
