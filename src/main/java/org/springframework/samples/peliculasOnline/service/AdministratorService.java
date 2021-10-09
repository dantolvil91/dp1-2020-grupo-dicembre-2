package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Administrator;
import org.springframework.samples.peliculasOnline.repository.AdministratorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class AdministratorService {

	private AdministratorRepository administratorRepository;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public AdministratorService(AdministratorRepository administratorRepository) {
		this.administratorRepository = administratorRepository;
	}	

	@Transactional(readOnly = true)
	public Administrator findAdminById(int id) throws DataAccessException {
		return administratorRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Administrator> findOwnerByLastName(String lastName) throws DataAccessException {
		return administratorRepository.findByLastName(lastName);
	}

	@Transactional
	public void saveOwner(Administrator administrator) throws DataAccessException {
		//creating owner
		administratorRepository.save(administrator);		
		//creating user
		//userService.saveUser(administrator.getUser());
		//creating authorities
		//authoritiesService.saveAuthorities(administrator.getUser().getUsername(), "owner");
	}		

}
