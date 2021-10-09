package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Cinema;

public interface CinemaRepository extends Repository<Cinema, Integer> {


	Cinema findById(int id) throws DataAccessException;

	void save(Cinema cinema) throws DataAccessException;
	
	Collection<Cinema> findAll() throws DataAccessException;


}