package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.samples.peliculasOnline.model.Film;


public interface FilmRepository extends Repository<Film, Integer>{


	Collection<Film> findAll() throws DataAccessException;
	
	void save(Film film) throws DataAccessException;

	Film findById(int id);

	Collection<Film> findFilmTypes();


}
