package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Director;

public interface DirectorRepository extends Repository<Director, Integer> {


	Director findById(int id) throws DataAccessException;

	void save(Director director) throws DataAccessException;
	
	Collection<Director> findAll() throws DataAccessException;


}