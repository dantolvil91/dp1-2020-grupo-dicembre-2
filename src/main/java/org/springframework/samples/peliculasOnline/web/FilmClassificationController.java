package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.FilmClassification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FilmClassificationController<RatingService> {

	private static final String VIEWS_RATING_CREATE_OR_UPDATE_FORM = "ratings/createOrUpdateRatingForm";

	private final RatingService ratingService;

	@Autowired
	public FilmClassificationController(RatingService ratingService) {
		this.ratingService = ratingService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/ratings/new")
	public String initCreationForm(Map<String, Object> model) {
		FilmClassification rating = new FilmClassification(null, null);
		model.put("rating", rating);
		return VIEWS_RATING_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/ratings/new")
	public String processCreationForm(@Valid FilmClassification rating, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_RATING_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
		//	this.ratingService.save(rating);
			
			return "redirect:/ratings/" + rating.getId();
		}
	}

	@GetMapping(value = "/ratings/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("rating", new FilmClassification(null, null));
		return "ratings/findratings";
	}

	@GetMapping(value = "/ratings")
	public String processFindForm(FilmClassification rating, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /ratings to return all records
		if (rating.getName() == null) {
			rating.setName(""); // empty string signifies broadest possible search
		}

		// find ratings by surname
	/*	Collection<FilmClassification> results = this.ratingService.findRatingByLastName(rating.getName());
		if (results.isEmpty()) {
			// no ratings found
			result.rejectValue("username", "notFound", "not found");
			return "ratings/findratings";
		}
		else if (results.size() == 1) {
			// 1 rating found
			rating = results.iterator().next();
			return "redirect:/ratings/" + rating.getId();
		}
		else {
			// multiple ratings found
			model.put("selections", results);*/
			return "ratings/ratingsList";
		//}
	}

	@GetMapping(value = "/ratings/{ratingId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ratingId") int ratingId, Model model) {
/*		FilmClassification rating = this.ratingService.findRatingById(ratingId);
		model.addAttribute(rating); */
		return VIEWS_RATING_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/ratings/{ratingId}/edit")
	public String processUpdateOwnerForm(@Valid FilmClassification rating, BindingResult result,
			@PathVariable("ratingId") int ratingId) {
		if (result.hasErrors()) {
			return VIEWS_RATING_CREATE_OR_UPDATE_FORM;
		}
		else {
			rating.setId(ratingId);
		//	this.ratingService.saveRating(rating);
			return "redirect:/ratings/{ratingId}";
		}
	}

	
	@GetMapping("/ratings/ratingId}")
	public ModelAndView showrating(@PathVariable("ratingId") int ratingId) {
		ModelAndView mav = new ModelAndView("ratings/ratingDetails");
	//	mav.addObject(this.ratingService.findRatingById(ratingId));
		return mav;
	}

}
