package br.com.sd.skipthedishes.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.sd.skipthedishes.model.Customer;

@Service
public class CustomerService extends GenericService<Customer> {

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
	public Class<Customer> getEntityClass() {
		return Customer.class;
	}

	public Customer findUser(String email, String password) {
		List<Customer> customers = this.entityManager.
										createQuery("SELECT c FROM Customer WHERE email like :email and password like :password ", getEntityClass()).
										getResultList();
		if (customers.size() == 0 ) return null;
		
		return customers.get(0);
	}
	
}
