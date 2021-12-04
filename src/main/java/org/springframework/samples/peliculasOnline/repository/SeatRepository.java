package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Seat;

public interface SeatRepository extends Repository<Seat, Integer>{


	Collection<Seat> findAll() throws DataAccessException;
	
	void save(Seat seat) throws DataAccessException;

	Seat findById(int id);

	Collection<Seat> findSeatTypes();


}