package br.com.sd.skipthedishes.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sd.skipthedishes.model.Customer;

public abstract class GenericService<E> {

	public List<E> getAll() {
		return getEntityManager().
				createQuery("SELECT e FROM "+ this.getEntityClass().getSimpleName()+ " e ", getEntityClass()).
				setMaxResults(100).
				getResultList();
	}

	public E getById(Integer entityId) {
		return getEntityManager().find(getEntityClass(), entityId.longValue());
	}
	
	public Boolean insert(E entity) {
		try {
			this.getEntityManager().getTransaction().begin();
			this.getEntityManager().persist(entity);
			this.getEntityManager().getTransaction().commit();
			return true;
		} catch (Exception e ) {
			this.getEntityManager().getTransaction().rollback();
			return false;
		}
				
	}
	
	public Boolean update(E entity) {
		try {
			this.getEntityManager().getTransaction().begin();
			this.getEntityManager().merge(entity);
			this.getEntityManager().getTransaction().commit();
			return true;
		} catch (Exception e ) {
			this.getEntityManager().getTransaction().rollback();
			return false;
		}	
	}
	
	abstract public EntityManager getEntityManager();
	
	abstract public Class<E> getEntityClass();
	
}
