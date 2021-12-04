package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.samples.peliculasOnline.model.Film;


public interface FilmRepository extends Repository<Film, Integer>{

	//Seleccionar todas las películas del sistema
	@Query("select f from Film f")
	Collection<Film> findAll();
	
	Film findById(int id) throws DataAccessException;

	//Seleccionar una película con tipo igual al que se pasa por parámetro
	@Query("select f from Film f where f.type like %?1")
	public Collection<Film> findFilmTypes(String Type);

	void save(Film film);

}
