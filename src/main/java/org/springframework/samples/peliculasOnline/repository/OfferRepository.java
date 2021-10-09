package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Offer;

public interface OfferRepository extends Repository<Offer, Integer>{

	Collection<Offer> findAll() throws DataAccessException;
	
	void save(Offer offer) throws DataAccessException;

	Offer findById(int id);


}