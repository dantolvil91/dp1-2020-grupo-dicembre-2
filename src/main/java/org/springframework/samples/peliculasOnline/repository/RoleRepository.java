package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.peliculasOnline.model.Role;

public interface RoleRepository extends Repository<Role, Integer>{


	Collection<Role> findAll() throws DataAccessException;
	
	void save(Role role) throws DataAccessException;

	Role findById(int id);

	Collection<Role> findRoleTypes();


}