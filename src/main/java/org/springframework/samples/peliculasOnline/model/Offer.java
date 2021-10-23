package org.springframework.samples.peliculasOnline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;


@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {
	
	 public Offer(String name, String description, String deadline, Integer addPoints, Integer subPoints, String cinema) {

	        this.name = name;
	        this.description = description;
	        this.deadline = deadline;
	        this.addPoints = addPoints;
	        this.subPoints = subPoints;
	        this.cinema = cinema;
	    }

	    @Id
	    @GeneratedValue
	    private Integer id_offer;

	    @Column(nullable = false)
	    private String name;

	    @Column(nullable = false)
	    private String description;

	    private String deadline;

	    private Integer addPoints;

	    private Integer subPoints;

	    @Column(nullable = false)
	    private String cinema;
	    
	    //Getters and Setters:

		public Integer getId_offer() {
			return id_offer;
		}

		public void setId_offer(Integer id_offer) {
			this.id_offer = id_offer;
		}

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

		public String getDeadline() {
			return deadline;
		}

		public void setDeadline(String deadline) {
			this.deadline = deadline;
		}

		public Integer getAddPoints() {
			return addPoints;
		}

		public void setAddPoints(Integer addPoints) {
			this.addPoints = addPoints;
		}

		public Integer getSubPoints() {
			return subPoints;
		}

		public void setSubPoints(Integer subPoints) {
			this.subPoints = subPoints;
		}

		public String getCinema() {
			return cinema;
		}

		public void setCinema(String cinema) {
			this.cinema = cinema;
		}    
	    

}
