package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Role;
import org.springframework.samples.peliculasOnline.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class RoleController {

	private static final String VIEWS_ROLE_CREATE_OR_UPDATE_FORM = "roles/createOrUpdateRoleForm";

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/roles/new")
	public String initCreationForm(Map<String, Object> model) {
		Role role = new Role(null);
		model.put("role", role);
		return VIEWS_ROLE_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/roles/new")
	public String processCreationForm(@Valid Role role, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ROLE_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating user 
			this.roleService.saveRole(role);
			
			return "redirect:/roles/" + role.getId();
		}
	}

	@GetMapping(value = "/roles/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("role", new Role(null));
		return "roles/findroles";
	}

	@GetMapping(value = "/roles")
	public String processFindForm(Role role, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /roles to return all records
		if (role.getName() == null) {
			role.setName(""); // empty string signifies broadest possible search
		}

		// find roles by surname
		Collection<Role> results = this.roleService.findRoleByLastName(role.getName());
		if (results.isEmpty()) {
			// no roles found
			result.rejectValue("username", "notFound", "not found");
			return "roles/findroles";
		}
		else if (results.size() == 1) {
			// 1 role found
			role = results.iterator().next();
			return "redirect:/roles/" + role.getId();
		}
		else {
			// multiple roles found
			model.put("selections", results);
			return "roles/rolesList";
		}
	}

	@GetMapping(value = "/roles/{roleId}/edit")
	public String initUpdateOwnerForm(@PathVariable("roleId") int roleId, Model model) {
		Role role = this.roleService.findRoleById(roleId);
		model.addAttribute(role);
		return VIEWS_ROLE_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/roles/{roleId}/edit")
	public String processUpdateOwnerForm(@Valid Role role, BindingResult result,
			@PathVariable("roleId") int roleId) {
		if (result.hasErrors()) {
			return VIEWS_ROLE_CREATE_OR_UPDATE_FORM;
		}
		else {
			role.setId(roleId);
			this.roleService.saveRole(role);
			return "redirect:/roles/{roleId}";
		}
	}

	
	@GetMapping("/roles/roleId}")
	public ModelAndView showrole(@PathVariable("roleId") int roleId) {
		ModelAndView mav = new ModelAndView("roles/roleDetails");
		mav.addObject(this.roleService.findRoleById(roleId));
		return mav;
	}

}
