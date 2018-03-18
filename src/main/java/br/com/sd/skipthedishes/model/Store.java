package br.com.sd.skipthedishes.model;

import javax.persistence.Entity;

@Entity()
public class Store extends GenericEntity {

	private String name;
	private String address;
	private Long cousineId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getCousineId() {
		return cousineId;
	}

	public void setCousineId(Long cousineId) {
		this.cousineId = cousineId;
	}
	
}
