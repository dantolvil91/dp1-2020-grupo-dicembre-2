package org.springframework.samples.peliculasOnline.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.samples.peliculasOnline.model.Rating;
import org.springframework.samples.peliculasOnline.repository.ActorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ActorService {

	private ActorRepository actorRepository;

	@Autowired
	public ActorService(ActorRepository actorRepository) {
		this.actorRepository = actorRepository;
	}

	@Transactional
	public void saveUser(Actor actor) throws DataAccessException {
		actorRepository.save(actor);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Actor> findRatings() throws DataAccessException {
		return actorRepository.findAll();
	}	
	
}
