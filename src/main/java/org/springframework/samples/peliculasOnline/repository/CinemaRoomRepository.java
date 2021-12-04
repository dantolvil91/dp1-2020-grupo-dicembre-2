package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.CinemaRoom;

public interface CinemaRoomRepository extends Repository<CinemaRoom, Integer>{

	Collection<CinemaRoom> findAll() throws DataAccessException;
	
	void save(CinemaRoom offer) throws DataAccessException;

	CinemaRoom findById(int id);


}