package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Trailer;
import org.springframework.samples.peliculasOnline.repository.TrailerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrailerService {

	private TrailerRepository trailerRepository;

	@Autowired
	public TrailerService(TrailerRepository trailerRepository) {
		this.trailerRepository = trailerRepository;
	}

	@Transactional
	public void saveRole(Trailer trailer) throws DataAccessException {
		trailerRepository.save(trailer);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Trailer> findTrailers() throws DataAccessException {
		return trailerRepository.findAll();
	}	
	
}