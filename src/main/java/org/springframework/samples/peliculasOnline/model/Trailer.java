package org.springframework.samples.peliculasOnline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trailers")
public class Trailer extends BaseEntity {

	//Attributes
	
    @Column(nullable = false)
    private String title;
    
    //Constructor

	public Trailer(String title) {
		super();
		this.title = title;
	}
	
	public Trailer() {
		super();
	}
	
	//Getters and Setters

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    
}

