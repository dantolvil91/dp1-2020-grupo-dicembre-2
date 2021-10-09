package org.springframework.samples.peliculasOnline.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.samples.peliculasOnline.model.Administrator;


public interface ActorRepository extends Repository<Actor, Integer> {


	void save(Actor actor) throws DataAccessException;


	@Query("SELECT DISTINCT actor FROM Actor actor left join fetch actor.films WHERE actor.lastName LIKE :lastName%")
	public Collection<Actor> findByLastName(@Param("lastName") String lastName);


	/*@Query("SELECT Actor FROM Owner owner left join fetch actor.films WHERE actor.id =:id")
	public Actor findById(@Param("id") String username);*/
	
	Actor findById(int id) throws DataAccessException;	

	List<Actor> findByActorId(Integer actorId);
	
	Collection<Actor> findAll() throws DataAccessException;


}
