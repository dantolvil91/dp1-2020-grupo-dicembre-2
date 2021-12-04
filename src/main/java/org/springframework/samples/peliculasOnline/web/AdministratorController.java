package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.samples.peliculasOnline.model.Administrator;
import org.springframework.samples.peliculasOnline.service.AdministratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdministratorController {

	private static final String VIEWS_ADMINISTRATOR_CREATE_OR_UPDATE_FORM = "actors/createOrUpdateAdministratorForm";

	private final AdministratorService administratorService;

	@Autowired
	public AdministratorController(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/administrators/new")
	public String initCreationForm(Map<String, Object> model) {
		Actor actor = new Actor();
		model.put("actor", actor);
		return VIEWS_ADMINISTRATOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/administrators/new")
	public String processCreationForm(@Valid Administrator administrator, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ADMINISTRATOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
			this.administratorService.saveAdministrator(administrator);
			
			return "redirect:/administrators/" + administrator.getId();
		}
	}

	@GetMapping(value = "/administrators/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("administrator", new Administrator());
		return "administrators/findAdministrators";
	}

	@GetMapping(value = "/administrators")
	public String processFindForm(Administrator administrator, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /administrators to return all records
		if (administrator.getUsername() == null) {
			administrator.setUsername(""); // empty string signifies broadest possible search
		}

		// find actors by surname
		Collection<Administrator> results = this.administratorService.findAdministratorByLastName(administrator.getUsername());
		if (results.isEmpty()) {
			// no actors found
			result.rejectValue("username", "notFound", "not found");
			return "administrators/findAdministrators";
		}
		else if (results.size() == 1) {
			// 1 actor found
			administrator = results.iterator().next();
			return "redirect:/administrators/" + administrator.getId();
		}
		else {
			// multiple actors found
			model.put("selections", results);
			return "administrators/administratorsList";
		}
	}

	@GetMapping(value = "/actors/{administratorId}/edit")
	public String initUpdateOwnerForm(@PathVariable("administratorId") int administratorId, Model model) {
		Administrator administrator = this.administratorService.findAdminById(administratorId);
		model.addAttribute(administrator);
		return VIEWS_ADMINISTRATOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/actors/{actorId}/edit")
	public String processUpdateOwnerForm(@Valid Administrator administrator, BindingResult result,
			@PathVariable("administratorId") int administratorId) {
		if (result.hasErrors()) {
			return VIEWS_ADMINISTRATOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			administrator.setId(administratorId);
			this.administratorService.saveAdministrator(administrator);
			return "redirect:/actors/{actorId}";
		}
	}

	
	@GetMapping("/actors/actorId}")
	public ModelAndView showActor(@PathVariable("administratorId") int administratorId) {
		ModelAndView mav = new ModelAndView("actors/actorDetails");
		mav.addObject(this.administratorService.findAdminById(administratorId));
		return mav;
	}

}
