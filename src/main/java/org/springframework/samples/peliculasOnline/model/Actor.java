package org.springframework.samples.peliculasOnline.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "actors")
public class Actor extends BaseEntity {
	
	
	
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)  
    private String surname;


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Actor(String name, String surname) {

        this.name = name;
        this.surname = surname;

    }

	public Actor() {

   
    }

}
