package org.springframework.samples.peliculasOnline.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

	//Attributes
	
    @Column(nullable = false)
    private String name;

	//Constructor
    
	public Role(String name) {
		super();
		this.name = name;
	}
	
	//Getters and Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
   

	
	
	
}
