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
	
	@Transactional(readOnly = true)
	public Collection<Film> findFilmTypes() throws DataAccessException {
		return filmRepository.findFilmTypes();
	}
	
	@Transactional
	public void save(Film film) throws DataAccessException {
		filmRepository.save(film);
	}

	@Transactional(readOnly = true)
	public Film findFilmById(int id) throws DataAccessException {
		return filmRepository.findById(id);
	}

	/* @Transactional(rollbackFor = DuplicatedFilmNameException.class)
	public void saveFilmAux(Film film) throws DataAccessException, DuplicatedFilmNameException {
			Film otherFilm=film.getFilm().getPetwithIdDifferent(film.getTitle(), film.getId());
            if (StringUtils.hasLength(pet.getName()) &&  (otherPet!= null && otherPet.getId()!=film.getId())) {            	
            	throw new DuplicatedPetNameException();
            }else
                filmRepository.save(film);              
	}*/ 


}
