package org.springframework.samples.peliculasOnline.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	@Id
	String username;
	
	String password;
	
	boolean enabled;
	
	//Constructor
	public User(String username, String password, boolean enabled, Set<Authorities> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	//Gettes and Setters
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	//Relationships

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
	
	public Set<Authorities> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(int userId) {
		// TODO Auto-generated method stub
		
	}

	
	
}
