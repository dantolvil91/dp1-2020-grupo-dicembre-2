
package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
	
	//Getters and Setters

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    
}

