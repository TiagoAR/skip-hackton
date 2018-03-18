package br.com.sd.skipthedishes.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.sd.skipthedishes.model.Cousine;
import br.com.sd.skipthedishes.model.Product;

public class CousineService extends GenericService<Cousine> {

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
	public Class<Cousine> getEntityClass() {
		return Cousine.class;
	}

	//TODO LAMBDA
	public List<Cousine> search(String searchText) {
		String[] keywords = searchText.trim().split(" ");
		
		if (keywords.length == 0) return new ArrayList<Cousine>();
		
		
		String sql = "SELECT p FROM Cousine p Where ";
		Integer pNumber = 0;
		for (String keyword : keywords) {
			if ( pNumber > 0 ) {
				sql += " AND (";
			}
			sql += " ( lower(name) like lower(:name" + pNumber + ") )";
			if ( pNumber > 0 ) {
				sql += " ) ";
			}
			pNumber++;
		}
		
		TypedQuery<Cousine> query = entityManager.createQuery(sql, Cousine.class);
		
		pNumber = 0;
		for( String keyword: keywords) {
			query.setParameter("name" + pNumber, "%" + keyword + "%");
		}
		
		return (List<Cousine>) query.getResultList();
	}

	public List<Product> getProducts(Integer storeId) {
		return getEntityManager().
				createQuery("SELECT p FROM Product p where storeId = :storeId", Product.class).
				setParameter("storeId", storeId).
				getResultList();
	}

}
