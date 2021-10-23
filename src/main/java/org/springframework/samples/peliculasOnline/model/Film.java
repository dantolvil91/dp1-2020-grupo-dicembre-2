package org.springframework.samples.peliculasOnline.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "films")
public class Film extends BaseEntity{

    private static final long serialVersionUID = 5448518077813233172L;

    public Film(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue
    private Integer id_film;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private Integer year;
    
    //Constructor:
    
	public Film(Integer id_film, String title, String description, Integer year) {
		super();
		this.id_film = id_film;
		this.title = title;
		this.description = description;
		this.year = year;
	}
	
	public Film() {
		super();
	}

    
    
    //Getters and Setters:

	public Integer getId_film() {
		return id_film;
	}


	public void setId_film(Integer id_film) {
		this.id_film = id_film;
	}

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}


}
