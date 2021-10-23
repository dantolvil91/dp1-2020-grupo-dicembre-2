package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Cinema;
import org.springframework.samples.peliculasOnline.repository.CinemaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CinemaService {

	private CinemaRepository cinemaRepository;

	@Autowired
	public CinemaService(CinemaRepository cinemaRepository) {
		this.cinemaRepository = cinemaRepository;
	}

	@Transactional
	public void saveUser(Cinema cinema) throws DataAccessException {
		cinemaRepository.save(cinema);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Cinema> findCinemas() throws DataAccessException {
		return cinemaRepository.findAll();
	}	
	
}
