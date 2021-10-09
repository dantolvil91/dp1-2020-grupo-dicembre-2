package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Seat;
import org.springframework.samples.peliculasOnline.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeatService {

	private SeatRepository seatRepository;

	@Autowired
	public SeatService(SeatRepository seatRepository) {
		this.seatRepository = seatRepository;
	}

	@Transactional
	public void saveRole(Seat seat) throws DataAccessException {
		seatRepository.save(seat);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Seat> findSeats() throws DataAccessException {
		return seatRepository.findAll();
	}	
	
}