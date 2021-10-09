package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Administrator;
import org.springframework.samples.peliculasOnline.model.Rating;



public interface RatingRepository extends Repository<Rating, Integer> {


	void save(Rating rating) throws DataAccessException;

	List<Rating> findByRatingId(Integer ratingId);

	Collection<Rating> findAll() throws DataAccessException;


}
