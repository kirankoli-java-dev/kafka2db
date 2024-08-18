package com.kafka;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author kiran koli
 *
 * 
 */

@Entity
public class Person {
	
	@Id
	private String id;

	@Column
	private String name;
	
	@Column
	private String birthdate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	
	
	

}
