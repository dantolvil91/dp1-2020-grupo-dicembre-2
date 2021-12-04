package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Trailer;
import org.springframework.samples.peliculasOnline.service.TrailerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TrailerController {

	private static final String VIEWS_TRAILER_CREATE_OR_UPDATE_FORM = "trailers/createOrUpdatetrailerForm";

	private final TrailerService trailerService;

	@Autowired
	public TrailerController(TrailerService trailerService) {
		this.trailerService = trailerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/trailers/new")
	public String initCreationForm(Map<String, Object> model) {
		Trailer trailer = new Trailer();
		model.put("trailer", trailer);
		return VIEWS_TRAILER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/trailers/new")
	public String processCreationForm(@Valid Trailer trailer, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_TRAILER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating trailer 
			this.trailerService.saveTrailer(trailer);
			
			return "redirect:/trailers/" + trailer.getId();
		}
	}

	@GetMapping(value = "/trailers/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("trailer", new Trailer());
		return "trailers/findtrailers";
	}

	@GetMapping(value = "/trailers")
	public String processFindForm(Trailer trailer, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /trailers to return all records
	/*	if (trailer.gettrailername() == null) {
			trailer.settrailername(""); // empty string signifies broadest possible search
		}*/

		// find trailers by surname
		Collection<Trailer> results =  null; //this.trailerService.findtrailerByLastName(trailer.gettrailername());
		if (results.isEmpty()) {
			// no trailers found
			result.rejectValue("trailername", "notFound", "not found");
			return "trailers/findtrailers";
		}
		else if (results.size() == 1) {
			// 1 trailer found
			trailer = results.iterator().next();
			return "redirect:/trailers/" + trailer.getId();
		}
		else {
			// multiple trailers found
			model.put("selections", results);
			return "trailers/trailersList";
		}
	}

	@GetMapping(value = "/trailers/{trailerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("trailerId") int trailerId, Model model) {
		Trailer trailer = this.trailerService.findTrailerById(trailerId);
		model.addAttribute(trailer);
		return VIEWS_TRAILER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/trailers/{trailerId}/edit")
	public String processUpdateOwnerForm(@Valid Trailer trailer, BindingResult result,
			@PathVariable("trailerId") int trailerId) {
		if (result.hasErrors()) {
			return VIEWS_TRAILER_CREATE_OR_UPDATE_FORM;
		}
		else {
			trailer.setId(trailerId);
			this.trailerService.saveTrailer(trailer);
			return "redirect:/trailers/{trailerId}";
		}
	}

	
	@GetMapping("/trailers/trailerId}")
	public ModelAndView showtrailer(@PathVariable("trailerId") int trailerId) {
		ModelAndView mav = new ModelAndView("trailers/trailerDetails");
		mav.addObject(this.trailerService.findTrailerById(trailerId));
		return mav;
	}

}
