package org.springframework.samples.peliculasOnline.web;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Film;
import org.springframework.samples.peliculasOnline.model.FilmClassification;
import org.springframework.samples.peliculasOnline.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmController<filmService> {

	private static final String VIEWS_FILM_CREATE_OR_UPDATE_FORM = "films/createOrUpdatefilmForm";

	private final FilmService filmService;

	@Autowired
	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	//HTTP(GET). Mediante GET se recuperan los datos de la entidad Film.
	@GetMapping(value = "/films/new")
	public String initCreationForm(Map<String, Object> model) {
		FilmClassification film = new FilmClassification(null, null);
		model.put("film", film);
		return VIEWS_FILM_CREATE_OR_UPDATE_FORM;
	}

	//HTTP(POST). Si no se producen errores en el modelo, se guardan los datos en el servicio usando el m�todo save(). 
	// Por el contrario, si se produce un error se nos redirecciona a la vista con el formulario para la creaci�n.
	@PostMapping(value = "/films/new")
	public String processCreationForm(@Valid Film film, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_FILM_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.filmService.save(film);
			
			return "redirect:/films/" + film.getId();
		}
	}

	@GetMapping(value = "/films/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("film", new FilmClassification(null, null));
		return "films/findfilms";
	}


	//Mediante el uso del servicio FilmService se obtiene la pel�cula que va a
	//ser modificada a trav�s de su Id y se redirecciona a la vista de edici�n.
	@GetMapping(value = "/films/{filmId}/edit")
	public String initUpdateOwnerForm(@PathVariable("filmId") int filmId, Model model) {
		Film film = this.filmService.findFilmById(filmId);
		model.addAttribute(film); 
		return VIEWS_FILM_CREATE_OR_UPDATE_FORM;
	}

	//Cuando se ha editado la pel�cula, se comprueba si presenta errores, 
	//si todo ha ido bien se redirecciona a la vista de los detalles de la pel�cula.
	//Por el contrario, si se encuentran errores se devolver� a la vista anterior. 
	@PostMapping(value = "/films/{filmId}/edit")
	public String processUpdateOwnerForm(@Valid Film film, BindingResult result,
			@PathVariable("filmId") int filmId) {
		if (result.hasErrors()) {
			return VIEWS_FILM_CREATE_OR_UPDATE_FORM;
		}
		else {
			film.setId(filmId);
			this.filmService.save(film);
			return "redirect:/films/{filmId}";
		}
	}
	
	//Se muestra la pel�cula que se obtiene por url a trav�s del filmId.
	@GetMapping("/films/filmId}")
	public ModelAndView showfilm(@PathVariable("filmId") int filmId) {
		ModelAndView mav = new ModelAndView("films/filmDetails");
		mav.addObject(this.filmService.findFilmById(filmId));
		return mav;
	}

}
