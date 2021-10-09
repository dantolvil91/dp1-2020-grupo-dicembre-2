package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Cinema;
import org.springframework.samples.peliculasOnline.model.User;


public interface UserRepository extends  CrudRepository<User, String>{
	
	Collection<User> findAll() throws DataAccessException;

	User findById(int id) throws DataAccessException;

	
}
