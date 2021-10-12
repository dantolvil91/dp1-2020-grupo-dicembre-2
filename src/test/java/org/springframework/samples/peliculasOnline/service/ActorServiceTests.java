package org.springframework.samples.peliculasOnline.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class ActorServiceTests {                
    
	@Autowired
	protected ActorService actorService;

	@Test
	void shouldFindOwnersBySurName() {
		Collection<Actor> actors = null;
		assertThat(actors.size()).isEqualTo(2);

		//actors = this.ownerService.findOwnerByLastName("Daviss");
		assertThat(actors.isEmpty()).isTrue();
	}


	@Test
	@Transactional
	public void shouldInsertActor() {
		//Collection<Actor> actors = this.actorService.findOwnerByLastName("Schultz");
		Collection<Actor> actors = null;

		int found = actors.size();

		Actor actor = new Actor(null, null);
		/*owner.setFirstName("Sam");
		owner.setLastName("Schultz");
		owner.setAddress("4, Evans Street");
		owner.setCity("Wollongong");
		owner.setTelephone("4444444444");*/
                Actor actor1 = new Actor(null, null);
                /*user.setUsername("Sam");
                user.setPassword("supersecretpassword");
                user.setEnabled(true);
                owner.setUser(user);   */             
                
		this.actorService.saveUser(actor1);
		assertThat(actor1.getId().longValue()).isNotEqualTo(0);

		//owners = this.ownerService.findOwnerByLastName("Schultz");
		assertThat(actors.size()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	void shouldUpdateOwner() {
		Actor actor = this.actorService.findActorById(1);
		String oldName = actor.getName();
		String newName = oldName + "X";

		actor.getName();
		this.actorService.saveUser(actor);

		// retrieving new name from database
		actor = this.actorService.findActorById(1);
		assertThat(actor.getName()).isEqualTo(newName);
	}


}
