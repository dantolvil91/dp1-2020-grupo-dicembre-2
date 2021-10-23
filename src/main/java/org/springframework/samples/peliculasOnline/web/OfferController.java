package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Offer;
import org.springframework.samples.peliculasOnline.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class OfferController {

	private static final String VIEWS_OFFER_CREATE_OR_UPDATE_FORM = "offers/createOrUpdateOfferForm";

	private final OfferService offerService;

	@Autowired
	public OfferController(OfferService offerService) {
		this.offerService = offerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/offers/new")
	public String initCreationForm(Map<String, Object> model) {
		Offer actor = new Offer(null, null, null, null, null, null);
		model.put("actor", actor);
		return VIEWS_OFFER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/offers/new")
	public String processCreationForm(@Valid Offer offer, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OFFER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
			this.offerService.saveOffer(offer);
			
			return "redirect:/offers/" + offer.getId();
		}
	}

	@GetMapping(value = "/offers/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("offer", new Offer(null, null, null, null, null, null));
		return "offers/findoffers";
	}

	@GetMapping(value = "/offers")
	public String processFindForm(Offer offer, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /offers to return all records
		if (offer.getName() == null) {
			offer.setName(""); // empty string signifies broadest possible search
		}

		// find actors by surname
		Collection<Offer> results = this.offerService.findOfferByLastName(offer.getName());
		if (results.isEmpty()) {
			// no actors found
			result.rejectValue("username", "notFound", "not found");
			return "offers/findoffers";
		}
		else if (results.size() == 1) {
			// 1 actor found
			offer = results.iterator().next();
			return "redirect:/offers/" + offer.getId();
		}
		else {
			// multiple actors found
			model.put("selections", results);
			return "offers/offersList";
		}
	}

	@GetMapping(value = "/actors/{offerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("offerId") int offerId, Model model) {
		Offer offer = this.offerService.findOfferById(offerId);
		model.addAttribute(offer);
		return VIEWS_OFFER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/actors/{actorId}/edit")
	public String processUpdateOwnerForm(@Valid Offer offer, BindingResult result,
			@PathVariable("offerId") int offerId) {
		if (result.hasErrors()) {
			return VIEWS_OFFER_CREATE_OR_UPDATE_FORM;
		}
		else {
			offer.setId(offerId);
			this.offerService.saveOffer(offer);
			return "redirect:/actors/{actorId}";
		}
	}

	
	@GetMapping("/actors/actorId}")
	public ModelAndView showActor(@PathVariable("offerId") int offerId) {
		ModelAndView mav = new ModelAndView("actors/actorDetails");
		mav.addObject(this.offerService.findOfferById(offerId));
		return mav;
	}

}
