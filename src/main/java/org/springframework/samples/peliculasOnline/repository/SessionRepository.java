package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Administrator;
import org.springframework.samples.peliculasOnline.model.Session;


public interface SessionRepository extends Repository<Session, Integer> {


	Session findById(int id) throws DataAccessException;

	void save(Session session) throws DataAccessException;
	
	Collection<Session> findAll() throws DataAccessException;


}
