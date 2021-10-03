/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;


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
