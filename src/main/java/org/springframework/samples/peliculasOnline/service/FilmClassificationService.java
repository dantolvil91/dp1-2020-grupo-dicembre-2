package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.FilmClassification;
import org.springframework.samples.peliculasOnline.repository.FilmClassificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FilmClassificationService {

	private FilmClassificationRepository ratingRepository;


	@Autowired
	public FilmClassificationService(FilmClassificationRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}		

	@Transactional(readOnly = true)	
	public Collection<FilmClassification> findRatings() throws DataAccessException {
		return ratingRepository.findAll();
	}

	public Collection<FilmClassification> findRatingByLastName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public FilmClassification findRatingById(int ratingId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveRating(@Valid FilmClassification rating) {
		// TODO Auto-generated method stub
		
	}	

}
