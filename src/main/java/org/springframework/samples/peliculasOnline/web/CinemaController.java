package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Cinema;
import org.springframework.samples.peliculasOnline.service.CinemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CinemaController {

	private static final String VIEWS_CINEMA_CREATE_OR_UPDATE_FORM = "cinemas/createOrUpdatecinemaForm";

	private final CinemaService cinemaService;

	@Autowired
	public CinemaController(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/cinemas/new")
	public String initCreationForm(Map<String, Object> model) {
		Cinema cinema = new Cinema(null, null, null);
		model.put("cinema", cinema);
		return VIEWS_CINEMA_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/cinemas/new")
	public String processCreationForm(@Valid Cinema cinema, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CINEMA_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
		//	this.cinemaService.savecinema(cinema);
			
			return "redirect:/cinemas/" + cinema.getId();
		}
	}

	@GetMapping(value = "/cinemas/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("cinema", new Cinema(null, null, null));
		return "cinemas/findcinemas";
	}

	/*@GetMapping(value = "/cinemas")
	public String processFindForm(Cinema cinema, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /cinemas to return all records
		if (cinema.getUsername() == null) {
			cinema.setUsername(""); // empty string signifies broadest possible search
		}

		// find cinemas by surname
		Collection<Cinema> results = this.cinemaService.findcinemaByLastName(cinema.getUsername());
		if (results.isEmpty()) {
			// no cinemas found
			result.rejectValue("username", "notFound", "not found");
			return "cinemas/findcinemas";
		}
		else if (results.size() == 1) {
			// 1 cinema found
			cinema = results.iterator().next();
			return "redirect:/cinemas/" + cinema.getId();
		}
		else {
			// multiple cinemas found
			model.put("selections", results);
			return "cinemas/cinemasList";
		} 
	} */

	@GetMapping(value = "/cinemas/{cinemaId}/edit")
	public String initUpdateOwnerForm(@PathVariable("cinemaId") int cinemaId, Model model) {
	//	Cinema cinema = this.cinemaService.findAdminById(cinemaId);
	//	model.addAttribute(cinema);
		return VIEWS_CINEMA_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/cinemas/{cinemaId}/edit")
	public String processUpdateOwnerForm(@Valid Cinema cinema, BindingResult result,
			@PathVariable("cinemaId") int cinemaId) {
		if (result.hasErrors()) {
			return VIEWS_CINEMA_CREATE_OR_UPDATE_FORM;
		}
		else {
			cinema.setId(cinemaId);
		//	this.cinemaService.save(cinema);
			return "redirect:/cinemas/{cinemaId}";
		}
	}

	
	@GetMapping("/cinemas/cinemaId}")
	public ModelAndView showcinema(@PathVariable("cinemaId") int cinemaId) {
		ModelAndView mav = new ModelAndView("cinemas/cinemaDetails");
	//	mav.addObject(this.cinemaService.findCinemaById(cinemaId));
		return mav;
	}

}
