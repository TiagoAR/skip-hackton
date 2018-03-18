package br.com.sd.skipthedishes.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import br.com.sd.skipthedishes.model.Product;

@Service
public class ProductService extends GenericService<Product> {

	@PersistenceContext()
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Product> search(String searchText) {
		String[] keywords = searchText.trim().split(" ");
		
		if (keywords.length == 0) return new ArrayList<Product>();
		
		//TODO LAMBDA
		String sql = "SELECT p FROM Product p Where ";
		Integer pNumber = 0;
		for (String keyword : keywords) {
			if ( pNumber > 0 ) {
				sql += " AND (";
			}
			sql += " ( lower(name) like lower(:name" + pNumber + ") OR lower(description) like  lower(:description" + pNumber + ") )";
			if ( pNumber > 0 ) {
				sql += " ) ";
			}
			pNumber++;
		}
		
		TypedQuery<Product> query = entityManager.createQuery(sql, Product.class);
		
		pNumber = 0;
		for( String keyword: keywords) {
			query.setParameter("name" + pNumber, "%" + keyword + "%");
			query.setParameter("description" + pNumber, "%" + keyword + "%");
		}
		
		return (List<Product>) query.getResultList();
	}

	@Override
	public Class<Product> getEntityClass() {
		return Product.class;
	}
}
