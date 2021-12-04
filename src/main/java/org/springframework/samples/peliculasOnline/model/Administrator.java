package org.springframework.samples.peliculasOnline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Table;

import org.springframework.data.annotation.Id;


@Entity
@Table(name = "administrators")
public class Administrator extends BaseEntity {

    public Administrator() {

    }

    @Id
    @GeneratedValue
    private Integer id_admin;

    @Column(nullable = false)
    private String username;

	public Integer getId_admin() {
		return this.id_admin;
	}

	public void setId_admin(Integer id_admin) {
		this.id_admin = id_admin;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    //Getters and Setters
    
    
    
}
