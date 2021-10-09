package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.peliculasOnline.model.Administrator;


public interface AdministratorRepository extends Repository<Administrator, Integer> {


	void save(Administrator administrator) throws DataAccessException;


	@Query("SELECT DISTINCT administrator FROM Administrator administrator left join fetch administrator.cinemas WHERE actor.lastName LIKE :lastName%")
	public Collection<Administrator> findByLastName(@Param("lastName") String lastName);


	@Query("SELECT Adminsitrator FROM Adminsitrator adminsitrator left join fetch administrator.cinemas WHERE adminsitrator.id =:id")
	public Administrator findById(@Param("id") int id);
	
	Collection<Administrator> findAll() throws DataAccessException;


}
