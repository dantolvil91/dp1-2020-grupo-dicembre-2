package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Rating;
import org.springframework.samples.peliculasOnline.repository.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RatingService {

	private RatingRepository ratingRepository;


	@Autowired
	public RatingService(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}		

	@Transactional(readOnly = true)	
	public Collection<Rating> findRatings() throws DataAccessException {
		return ratingRepository.findAll();
	}

	public Collection<Rating> findRatingByLastName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Rating findRatingById(int ratingId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveRating(@Valid Rating rating) {
		// TODO Auto-generated method stub
		
	}	

}
