package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Administrator;
import org.springframework.samples.peliculasOnline.model.FilmClassification;



public interface FilmClassificationRepository extends Repository<FilmClassification, Integer> {


	void save(FilmClassification rating) throws DataAccessException;

	List<FilmClassification> findByRatingId(Integer ratingId);

	Collection<FilmClassification> findAll() throws DataAccessException;


}
