package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Film;
import org.springframework.samples.peliculasOnline.repository.FilmRepository;
import org.springframework.samples.peliculasOnline.service.exceptions.DuplicatedFilmNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class FilmService {

	private FilmRepository filmRepository;
	
	@Autowired
	public FilmService(FilmRepository filmRepository) {
	this.filmRepository = filmRepository;
	}

	//Encontrar una pel�cula seg�n su tipo
	@Transactional(readOnly = true)
	public Collection<Film> findFilmTypes(String type) throws DataAccessException {
		return filmRepository.findFilmTypes(type);
	}
	
	//Guardar una pel�cula @Transactional
	@Transactional
	public void save(Film film) throws DataAccessException {
		filmRepository.save(film);
	}

	@Transactional(readOnly = true)
	public Film findFilmById(int id) throws DataAccessException {
		return filmRepository.findById(id);
	}



}
