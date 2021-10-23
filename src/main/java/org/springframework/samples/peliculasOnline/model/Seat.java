package org.springframework.samples.peliculasOnline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "seats")
public class Seat extends BaseEntity {
	
	//Attributes
	
    @Column(nullable = false)
    private Integer sessionNumber;
    
    @Column(nullable = false)
    private Integer seatNumber;
    
    //Constructor

	public Seat(Integer sessionNumber, Integer seatNumber) {
		super();
		this.sessionNumber = sessionNumber;
		this.seatNumber = seatNumber;
	
	}
	
	//Getters and Setters

	public Integer getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(Integer sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public Integer getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
    

}
