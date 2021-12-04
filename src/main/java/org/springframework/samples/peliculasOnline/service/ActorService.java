package org.springframework.samples.peliculasOnline.service;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.samples.peliculasOnline.model.Administrator;
import org.springframework.samples.peliculasOnline.model.FilmClassification;
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
	public void saveActor(Actor actor) throws DataAccessException {
		actorRepository.save(actor);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Actor> findActors() throws DataAccessException {
		return actorRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Actor findActorById(int id) throws DataAccessException {
		return actorRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Collection<Actor> findActorBySurName(String surname) throws DataAccessException {
		return actorRepository.findBySurName(surname);
	}
	
}
