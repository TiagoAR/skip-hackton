package br.com.sd.skipthedishes.model;

import javax.persistence.Entity;

@Entity
public class Product extends GenericEntity {
	
	private Long storeId;
	private String name;
	private String description;
	private Double price;
	
	public Long getStoreId() {
		return storeId;
	}
	
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
