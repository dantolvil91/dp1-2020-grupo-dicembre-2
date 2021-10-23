package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Director;
import org.springframework.samples.peliculasOnline.repository.DirectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorService {

	private DirectorRepository directorRepository;

	@Autowired
	public DirectorService(DirectorRepository directorRepository) {
		this.directorRepository = directorRepository;
	}

	@Transactional
	public void saveDirector(Director director) throws DataAccessException {
		directorRepository.save(director);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Director> findDirectors() throws DataAccessException {
		return directorRepository.findAll();
	}

	public Collection<Director> findDirectorByLastName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Director findDirectorById(int directorId) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}