package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Subscriber;
import org.springframework.samples.peliculasOnline.repository.SubscriberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscriberService {

	private SubscriberRepository subscriberRepository;

	@Autowired
	public SubscriberService(SubscriberRepository subscriberRepository) {
		this.subscriberRepository = subscriberRepository;
	}

	@Transactional
	public void saveSubscriber(Subscriber subscriber) throws DataAccessException {
		subscriberRepository.save(subscriber);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Subscriber> findSubscribers() throws DataAccessException {
		return subscriberRepository.findAll();
	}

	public Collection<Subscriber> findSubscriberByLastName(Boolean subscribed) {
		// TODO Auto-generated method stub
		return null;
	}

	public Subscriber findSubscriberById(int subscriberId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}