package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Offer;
import org.springframework.samples.peliculasOnline.repository.OfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OfferService {

	private OfferRepository offerRepository;

	@Autowired
	public OfferService(OfferRepository offerRepository) {
		this.offerRepository = offerRepository;
	}

	@Transactional
	public void saveOffer(Offer offer) throws DataAccessException {
		offerRepository.save(offer);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Offer> findOffers() throws DataAccessException {
		return offerRepository.findAll();
	}

	public Collection<Offer> findOfferByLastName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Offer findOfferById(int offerId) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}