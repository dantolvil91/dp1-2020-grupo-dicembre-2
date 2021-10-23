package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Role;
import org.springframework.samples.peliculasOnline.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Transactional
	public void saveRole(Role role) throws DataAccessException {
		roleRepository.save(role);
	}
	
	@Transactional(readOnly = true)	
	public Collection<Role> findRoles() throws DataAccessException {
		return roleRepository.findAll();
	}

	public Collection<Role> findRoleByLastName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Role findRoleById(int roleId) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
	