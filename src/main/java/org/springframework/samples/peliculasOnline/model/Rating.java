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
@Table(name = "ratings")
public class Rating extends BaseEntity {

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;
    
    //Constructor

	public Rating(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		
	}

	//Getters and Setters
	
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
	
	
    
    
}
