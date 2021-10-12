package org.springframework.samples.peliculasOnline.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.samples.peliculasOnline.model.Film;
import org.springframework.samples.peliculasOnline.service.exceptions.DuplicatedFilmNameException;
import org.springframework.samples.peliculasOnline.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class FilmServiceTests {        
    
	@Autowired
	protected FilmService filmService;
        
    @Autowired
	protected ActorService actorService;	

	@Test
	void shouldFindFilmWithCorrectId() {
		Film film2 = this.filmService.findFilmById(2);
		assertThat(film2.getTitle()).startsWith("Star");
		assertThat(film2.getYear()).isEqualTo("1980");

	}

	@Test
	@Transactional
	public void shouldInsertFilmIntoDatabaseAndGenerateId() {
		Actor actor6 = this.actorService.findActorById(6);
		//int found = actor6.getFilms().size();

		Film film = new Film(null);
		film.setTitle("007"); 
		film.setYear(1995);

	   this.filmService.save(film);
		 
           /* try {
                this.filmService.save(film);
            } catch (DuplicatedFilmNameException ex) {
                Logger.getLogger(FilmServiceTests.class.getName()).log(Level.SEVERE, null, ex);
            }*/
		this.actorService.saveUser(actor6);

		actor6 = this.actorService.findActorById(6);
		//assertThat(actor6.getFilms().size()).isEqualTo(found + 1);
		// checks that id has been generated
		assertThat(film.getId()).isNotNull();
	}
	
	@Test
	@Transactional
	public void shouldThrowExceptionInsertingFilmsWithTheSameName() {
		//Actor actor6 = this.actorService.findActorById(6);
		Film film = new Film();
		film.setTitle("Indiana Jones");
		film.setYear(1998);
		
		filmService.save(film);		

		/*try {
			filmService.save(film);		
		} catch (DuplicatedFilmNameException e) {
			// The pet already exists!
			e.printStackTrace();
		}*/
		
		Film anotherFilmWithTheSameTitle = new Film();		
		anotherFilmWithTheSameTitle.setTitle("Indiana Jones");
		anotherFilmWithTheSameTitle.setYear(1998);
		Assertions.assertThrows(DuplicatedFilmNameException.class, () ->{
			//owner6.addPet(anotherPetWithTheSameName);
			filmService.save(anotherFilmWithTheSameTitle);
		});		
	}

	@Test
	@Transactional
	public void shouldUpdateFilmTitle() throws Exception {
		Film film7 = this.filmService.findFilmById(7);
		String oldTitle = film7.getTitle();

		String newTitle = oldTitle + "X";
		film7.setTitle(newTitle);
		this.filmService.save(film7);

		film7 = this.filmService.findFilmById(7);
		assertThat(film7.getTitle()).isEqualTo(newTitle);
	}
	
	@Test
	@Transactional
	public void shouldThrowExceptionUpdatingFilmWithTheSameTitle() {
		//Actor actor3 = this.actorService.findActorById(3);
		Film film = new Film();
		film.setTitle("Star Wars");
		film.setYear(1994);
		//actor3.addFilm(film);
		
		Film anotherFilm = new Film();		
		anotherFilm.setTitle("Indiana Jones");
		anotherFilm.setYear(1999);
		//actor3.addFilm(anotherFilm);
		
		filmService.save(film);
		filmService.save(anotherFilm);				
			
		Assertions.assertThrows(DuplicatedFilmNameException.class, () ->{
			anotherFilm.setTitle("Star Wars");
			filmService.save(anotherFilm);
		});		
	}


}
