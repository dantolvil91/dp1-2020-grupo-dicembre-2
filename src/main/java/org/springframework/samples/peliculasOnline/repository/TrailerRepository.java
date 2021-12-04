package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Trailer;

public interface TrailerRepository extends Repository<Trailer, Integer>{


	Collection<Trailer> findAll() throws DataAccessException;
	
	void save(Trailer trailer) throws DataAccessException;

	Trailer findById(int id);

	Collection<Trailer> findTrailerTypes();


}