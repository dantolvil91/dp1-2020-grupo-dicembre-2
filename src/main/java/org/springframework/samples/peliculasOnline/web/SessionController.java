package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Session;
import org.springframework.samples.peliculasOnline.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SessionController {

	private static final String VIEWS_SESSION_CREATE_OR_UPDATE_FORM = "sessions/createOrUpdatesessionForm";

	private final SessionService sessionService;

	@Autowired
	public SessionController(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/sessions/new")
	public String initCreationForm(Map<String, Object> model) {
		Session session = new Session(null);
		model.put("session", session);
		return VIEWS_SESSION_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/sessions/new")
	public String processCreationForm(@Valid Session session, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_SESSION_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
			this.sessionService.saveSession(session);
			
			return "redirect:/sessions/" + session.getId();
		}
	}

	@GetMapping(value = "/sessions/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("session", new Session(null));
		return "sessions/findsessions";
	}

	/* @GetMapping(value = "/sessions")
	public String processFindForm(Session session, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /sessions to return all records
		if (session.getUsername() == null) {
			session.setUsername(""); // empty string signifies broadest possible search
		}

		// find sessions by surname
		Collection<session> results = this.sessionService.findsessionByLastName(session.getUsername());
		if (results.isEmpty()) {
			// no sessions found
			result.rejectValue("username", "notFound", "not found");
			return "sessions/findsessions";
		}
		else if (results.size() == 1) {
			// 1 session found
			session = results.iterator().next();
			return "redirect:/sessions/" + session.getId();
		}
		else {
			// multiple sessions found
			model.put("selections", results);
			return "sessions/sessionsList";
		}
	} */

	@GetMapping(value = "/sessions/{sessionId}/edit")
	public String initUpdateOwnerForm(@PathVariable("sessionId") int sessionId, Model model) {
		Session session = this.sessionService.findSessionById(sessionId);
		model.addAttribute(session);
		return VIEWS_SESSION_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/sessions/{sessionId}/edit")
	public String processUpdateOwnerForm(@Valid Session session, BindingResult result,
			@PathVariable("sessionId") int sessionId) {
		if (result.hasErrors()) {
			return VIEWS_SESSION_CREATE_OR_UPDATE_FORM;
		}
		else {
			session.setId(sessionId);
			this.sessionService.saveSession(session);
			return "redirect:/sessions/{sessionId}";
		}
	}

	
	@GetMapping("/sessions/sessionId}")
	public ModelAndView showsession(@PathVariable("sessionId") int sessionId) {
		ModelAndView mav = new ModelAndView("sessions/sessionDetails");
		mav.addObject(this.sessionService.findSessionById(sessionId));
		return mav;
	}

}
