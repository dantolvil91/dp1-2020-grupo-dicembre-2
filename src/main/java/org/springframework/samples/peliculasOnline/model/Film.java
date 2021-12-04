package org.springframework.samples.peliculasOnline.model;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "films")
public class Film extends BaseEntity{


    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private Date releaseDate;
    
    @Column
    private String category;
    
    //Constructor:
    
	public Film(String title, String description, Date releaseDate, String category) {
		super();
		
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.category = category;
	}
	
	public Film() {
		super();
	}
 
    
    //Getters and Setters:

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	//Relationships:
	
	private List<Cinema> cinemas;
	private List<Actor> actors;
	private Director director;
	private List<Trailer> trailers;

	public List<Cinema> getCinemas() {
		return cinemas;
	}

	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public List<Trailer> getTrailers() {
		return trailers;
	}

	public void setTrailers(List<Trailer> trailers) {
		this.trailers = trailers;
	}

}
