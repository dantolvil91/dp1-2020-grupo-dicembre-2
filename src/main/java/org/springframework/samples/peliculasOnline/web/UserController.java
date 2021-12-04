package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.User;
import org.springframework.samples.peliculasOnline.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

	private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateuserForm";

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User(null, null, false, null);
		model.put("user", user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
			this.userService.saveUser(user);
			
			return "redirect:/users/" + user.getId();
		}
	}

	@GetMapping(value = "/users/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("user", new User(null, null, false, null));
		return "users/findusers";
	}

	@GetMapping(value = "/users")
	public String processFindForm(User user, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /users to return all records
		if (user.getUsername() == null) {
			user.setUsername(""); // empty string signifies broadest possible search
		}

		// find users by surname
		Collection<User> results = this.userService.findUserByLastName(user.getUsername());
		if (results.isEmpty()) {
			// no users found
			result.rejectValue("username", "notFound", "not found");
			return "users/findusers";
		}
		else if (results.size() == 1) {
			// 1 user found
			user = results.iterator().next();
			return "redirect:/users/" + user.getId();
		}
		else {
			// multiple users found
			model.put("selections", results);
			return "users/usersList";
		}
	}

	@GetMapping(value = "/users/{userId}/edit")
	public String initUpdateOwnerForm(@PathVariable("userId") int userId, Model model) {
		User user = this.userService.findUserById(userId);
		model.addAttribute(user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/users/{userId}/edit")
	public String processUpdateOwnerForm(@Valid User user, BindingResult result,
			@PathVariable("userId") int userId) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}
		else {
			user.setId(userId);
			this.userService.saveUser(user);
			return "redirect:/users/{userId}";
		}
	}

	
	@GetMapping("/users/userId}")
	public ModelAndView showuser(@PathVariable("userId") int userId) {
		ModelAndView mav = new ModelAndView("users/userDetails");
		mav.addObject(this.userService.findUserById(userId));
		return mav;
	}

}
