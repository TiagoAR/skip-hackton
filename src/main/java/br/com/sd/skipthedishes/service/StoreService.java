package br.com.sd.skipthedishes.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sd.skipthedishes.model.Store;

public class StoreService extends GenericService<Store> {

	@PersistenceContext()
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return null;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Class<Store> getEntityClass() {
		return Store.class;
	}

	//TODO LAMBDA
	public List<Store> search(String searchText) {
		String[] keywords = searchText.trim().split(" ");
		
		if (keywords.length == 0) return new ArrayList<Store>();
		
		
		String sql = "SELECT p FROM Store p Where ";
		Integer pNumber = 0;
		for (String keyword : keywords) {
			if ( pNumber > 0 ) {
				sql += " AND (";
			}
			sql += " ( lower(name) like lower(:name" + pNumber + ") OR lower(address) like  lower(:address" + pNumber + ") )";
			if ( pNumber > 0 ) {
				sql += " ) ";
			}
			pNumber++;
		}
		
		TypedQuery<Store> query = entityManager.createQuery(sql, Store.class);
		
		pNumber = 0;
		for( String keyword: keywords) {
			query.setParameter("name" + pNumber, "%" + keyword + "%");
			query.setParameter("address" + pNumber, "%" + keyword + "%");
		}
		
		return (List<Store>) query.getResultList();
	}

}
