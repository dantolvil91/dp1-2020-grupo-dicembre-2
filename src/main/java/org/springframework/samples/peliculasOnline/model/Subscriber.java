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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "subscribers")
public class Subscriber extends BaseEntity {
	
    public Subscriber(String username, Integer points) {

        this.username = username;
        this.points = points;
    }

    @Id
    @GeneratedValue
    private Integer id_subscriber;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer points;

    @Column(nullable = false)
    private Boolean subscribed;

    @Column(nullable = false)
    private String deadline;

    //Constructor
    
	public Subscriber(Integer id_subscriber, String username, Integer points, Boolean subscribed, String deadline) {
		super();
		this.id_subscriber = id_subscriber;
		this.username = username;
		this.points = points;
		this.subscribed = subscribed;
		this.deadline = deadline;
	}
	
	//Getters and Setters

	public Integer getId_subscriber() {
		return id_subscriber;
	}

	public void setId_subscriber(Integer id_subscriber) {
		this.id_subscriber = id_subscriber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Boolean getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
    
	
    

	

}
