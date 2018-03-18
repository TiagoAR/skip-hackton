package br.com.sd.skipthedishes.model;

import javax.persistence.Entity;

@Entity()
public class Cousine extends GenericEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
