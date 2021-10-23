package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Actor;
import org.springframework.samples.peliculasOnline.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ActorController {

	private static final String VIEWS_ACTOR_CREATE_OR_UPDATE_FORM = "actors/createOrUpdateActorForm";

	private final ActorService actorService;

	@Autowired
	public ActorController(ActorService actorService) {
		this.actorService = actorService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/actors/new")
	public String initCreationForm(Map<String, Object> model) {
		Actor actor = new Actor();
		model.put("actor", actor);
		return VIEWS_ACTOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/actors/new")
	public String processCreationForm(@Valid Actor actor, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ACTOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
			this.actorService.saveActor(actor);
			
			return "redirect:/actors/" + actor.getId();
		}
	}

	@GetMapping(value = "/actors/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("actor", new Actor());
		return "actors/findActors";
	}

	@GetMapping(value = "/actors")
	public String processFindForm(Actor actor, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /actors to return all records
		if (actor.getSurname() == null) {
			actor.setSurname(""); // empty string signifies broadest possible search
		}

		// find actors by surname
		Collection<Actor> results = this.actorService.findActorBySurName(actor.getSurname());
		if (results.isEmpty()) {
			// no actors found
			result.rejectValue("surname", "notFound", "not found");
			return "actors/findActors";
		}
		else if (results.size() == 1) {
			// 1 actor found
			actor = results.iterator().next();
			return "redirect:/actors/" + actor.getId();
		}
		else {
			// multiple actors found
			model.put("selections", results);
			return "actors/actorsList";
		}
	}

	@GetMapping(value = "/actors/{actorId}/edit")
	public String initUpdateActorForm(@PathVariable("actorId") int actorId, Model model) {
		Actor actor = this.actorService.findActorById(actorId);
		model.addAttribute(actor);
		return VIEWS_ACTOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/actors/{actorId}/edit")
	public String processUpdateActorForm(@Valid Actor actor, BindingResult result,
			@PathVariable("actorId") int actorId) {
		if (result.hasErrors()) {
			return VIEWS_ACTOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			actor.setId(actorId);
			this.actorService.saveActor(actor);
			return "redirect:/actors/{actorId}";
		}
	}

	
	@GetMapping("/actors/actorId}")
	public ModelAndView showActor(@PathVariable("actorId") int actorId) {
		ModelAndView mav = new ModelAndView("actors/actorDetails");
		mav.addObject(this.actorService.findActorById(actorId));
		return mav;
	}

}
