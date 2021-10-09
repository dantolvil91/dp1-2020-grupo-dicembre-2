package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Session;
import org.springframework.samples.peliculasOnline.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessionService {

	private SessionRepository sessionRepository;

	@Autowired
	public SessionService(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	@Transactional
	public void saveOffer(Session session) throws DataAccessException {
		sessionRepository.save(session);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Session> findSessions() throws DataAccessException {
		return sessionRepository.findAll();
	}	
	
}