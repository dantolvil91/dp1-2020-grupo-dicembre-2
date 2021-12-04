package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.CinemaRoom;
import org.springframework.samples.peliculasOnline.repository.CinemaRoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CinemaRoomService {

	private CinemaRoomRepository offerRepository;

	@Autowired
	public CinemaRoomService(CinemaRoomRepository offerRepository) {
		this.offerRepository = offerRepository;
	}

	@Transactional
	public void saveOffer(CinemaRoom offer) throws DataAccessException {
		offerRepository.save(offer);
	}
	
	@Transactional(readOnly = true)	
	public Collection<CinemaRoom> findOffers() throws DataAccessException {
		return offerRepository.findAll();
	}

	public Collection<CinemaRoom> findOfferByLastName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public CinemaRoom findOfferById(int offerId) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}