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
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.Id;


@MappedSuperclass
public class Cinema extends BaseEntity {


    public Cinema(String username, String web, String address) {

        this.username = username;
        this.web = web;
        this.address = address;
    }

    @Id
    @GeneratedValue
    private Integer id_cinema;

    @Column(nullable = false)
    private String username;

    @Column
    private String web;

    @Column
    private String address;
    
    //Getters and Setters
    

	public Integer getId_cinema() {
		return this.id_cinema;
	}

	public void setId_cinema(Integer id_cinema) {
		this.id_cinema = id_cinema;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
    

}
