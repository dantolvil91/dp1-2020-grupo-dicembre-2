package org.springframework.samples.peliculasOnline.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.peliculasOnline.model.Authorities;

public interface AuthoritiesRepository extends  CrudRepository<Authorities, String>{
	
}
