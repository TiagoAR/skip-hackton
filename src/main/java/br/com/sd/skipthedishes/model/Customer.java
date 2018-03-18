package br.com.sd.skipthedishes.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Customer extends GenericEntity {

	private String email;
	private String name;
	private String address;
	private Date creation;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

/*id	integer($int64)
email*	string
name*	string
address*	string
creation	string($date-time)
password*	string*/