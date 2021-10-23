package org.springframework.samples.peliculasOnline.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Simple JavaBean domain object adds a name property to <code>BaseEntity</code>. Used as
 * a base class for objects needing these properties.
 *
 */
@MappedSuperclass
public class Director extends BaseEntity {

    @Size(min = 3, max = 50)
	@Column(name = "name")
	private String name;
    
	@Column(name = "surName")
    private String surName;

	
	//Getters and Setters:
	
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getSurName() {
		return this.surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

}
