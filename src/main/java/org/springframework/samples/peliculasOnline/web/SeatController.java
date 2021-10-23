package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Seat;
import org.springframework.samples.peliculasOnline.service.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SeatController {

	private static final String VIEWS_SEAT_CREATE_OR_UPDATE_FORM = "seats/createOrUpdateseatForm";

	private final SeatService seatService;

	@Autowired
	public SeatController(SeatService seatService) {
		this.seatService = seatService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/seats/new")
	public String initCreationForm(Map<String, Object> model) {
		Seat seat = new Seat(null, null);
		model.put("seat", seat);
		return VIEWS_SEAT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/seats/new")
	public String processCreationForm(@Valid Seat seat, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_SEAT_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
			this.seatService.saveSeat(seat);
			
			return "redirect:/seats/" + seat.getId();
		}
	}

	@GetMapping(value = "/seats/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("seat", new Seat(null, null));
		return "seats/findseats";
	}

/*	@GetMapping(value = "/seats")
	public String processFindForm(Seat seat, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /seats to return all records
		if (seat.getUsername() == null) {
			seat.setUsername(""); // empty string signifies broadest possible search
		}

		// find seats by surname
		Collection<seat> results = this.seatService.findseatByLastName(seat.getUsername());
		if (results.isEmpty()) {
			// no seats found
			result.rejectValue("username", "notFound", "not found");
			return "seats/findseats";
		}
		else if (results.size() == 1) {
			// 1 seat found
			seat = results.iterator().next();
			return "redirect:/seats/" + seat.getId();
		}
		else {
			// multiple seats found
			model.put("selections", results);
			return "seats/seatsList";
		}
	} */

	@GetMapping(value = "/seats/{seatId}/edit")
	public String initUpdateOwnerForm(@PathVariable("seatId") int seatId, Model model) {
		Seat seat = this.seatService.findSeatById(seatId);
		model.addAttribute(seat);
		return VIEWS_SEAT_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/seats/{seatId}/edit")
	public String processUpdateOwnerForm(@Valid Seat seat, BindingResult result,
			@PathVariable("seatId") int seatId) {
		if (result.hasErrors()) {
			return VIEWS_SEAT_CREATE_OR_UPDATE_FORM;
		}
		else {
			seat.setId(seatId);
			this.seatService.saveSeat(seat);
			return "redirect:/seats/{seatId}";
		}
	}

	
	@GetMapping("/seats/seatId}")
	public ModelAndView showseat(@PathVariable("seatId") int seatId) {
		ModelAndView mav = new ModelAndView("seats/seatDetails");
		mav.addObject(this.seatService.findSeatById(seatId));
		return mav;
	}

}
