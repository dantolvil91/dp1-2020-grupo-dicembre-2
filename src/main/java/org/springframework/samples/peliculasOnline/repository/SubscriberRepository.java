package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Subscriber;

public interface SubscriberRepository extends Repository<Subscriber, Integer>{


	Collection<Subscriber> findAll() throws DataAccessException;
	
	void save(Subscriber subscriber) throws DataAccessException;

	Subscriber findById(int id);

	Collection<Subscriber> findSubscriberTypes();


}