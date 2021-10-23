package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Director;
import org.springframework.samples.peliculasOnline.service.DirectorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DirectorController {

	private static final String VIEWS_DIRECTOR_CREATE_OR_UPDATE_FORM = "directors/createOrUpdateDirectorForm";

	private final DirectorService directorService;

	@Autowired
	public DirectorController(DirectorService directorService) {
		this.directorService = directorService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/directors/new")
	public String initCreationForm(Map<String, Object> model) {
		Director director = new Director();
		model.put("director", director);
		return VIEWS_DIRECTOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/directors/new")
	public String processCreationForm(@Valid Director director, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_DIRECTOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
			this.directorService.saveDirector(director);
			
			return "redirect:/directors/" + director.getId();
		}
	}

	@GetMapping(value = "/directors/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("director", new Director());
		return "directors/finddirectors";
	}

	@GetMapping(value = "/directors")
	public String processFindForm(Director director, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /directors to return all records
		if (director.getName() == null) {
			director.setName(""); // empty string signifies broadest possible search
		}

		// find actors by surname
		Collection<Director> results = this.directorService.findDirectorByLastName(director.getName());
		if (results.isEmpty()) {
			// no actors found
			result.rejectValue("username", "notFound", "not found");
			return "directors/finddirectors";
		}
		else if (results.size() == 1) {
			// 1 actor found
			director = results.iterator().next();
			return "redirect:/directors/" + director.getId();
		}
		else {
			// multiple actors found
			model.put("selections", results);
			return "directors/directorsList";
		}
	}

	@GetMapping(value = "/actors/{directorId}/edit")
	public String initUpdateOwnerForm(@PathVariable("directorId") int directorId, Model model) {
		Director director = this.directorService.findDirectorById(directorId);
		model.addAttribute(director);
		return VIEWS_DIRECTOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/actors/{actorId}/edit")
	public String processUpdateOwnerForm(@Valid Director director, BindingResult result,
			@PathVariable("directorId") int directorId) {
		if (result.hasErrors()) {
			return VIEWS_DIRECTOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			director.setId(directorId);
			this.directorService.saveDirector(director);
			return "redirect:/actors/{actorId}";
		}
	}

	
	@GetMapping("/actors/actorId}")
	public ModelAndView showActor(@PathVariable("directorId") int directorId) {
		ModelAndView mav = new ModelAndView("actors/actorDetails");
		mav.addObject(this.directorService.findDirectorById(directorId));
		return mav;
	}

}
