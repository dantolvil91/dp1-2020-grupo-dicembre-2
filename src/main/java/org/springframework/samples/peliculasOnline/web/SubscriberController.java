package org.springframework.samples.peliculasOnline.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.peliculasOnline.model.Subscriber;
import org.springframework.samples.peliculasOnline.service.SubscriberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SubscriberController {

	private static final String VIEWS_SUBSCRIBER_CREATE_OR_UPDATE_FORM = "subscribers/createOrUpdatesubscriberForm";

	private final SubscriberService subscriberService;

	@Autowired
	public SubscriberController(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/subscribers/new")
	public String initCreationForm(Map<String, Object> model) {
		Subscriber subscriber = new Subscriber(null, null, null, null, null);
		model.put("subscriber", subscriber);
		return VIEWS_SUBSCRIBER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/subscribers/new")
	public String processCreationForm(@Valid Subscriber subscriber, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_SUBSCRIBER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating subscriber 
			this.subscriberService.saveSubscriber(subscriber);
			
			return "redirect:/subscribers/" + subscriber.getId();
		}
	}

	@GetMapping(value = "/subscribers/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("subscriber", new Subscriber(null, null, null, null, null));
		return "subscribers/findsubscribers";
	}

	@GetMapping(value = "/subscribers")
	public String processFindForm(Subscriber subscriber, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /subscribers to return all records
		if (subscriber.getSubscribed() == null) {
			subscriber.setSubscribed(); // empty string signifies broadest possible search
		}

		// find subscribers by surname
		Collection<Subscriber> results = this.subscriberService.findSubscriberByLastName(subscriber.getSubscribed());
		if (results.isEmpty()) {
			// no subscribers found
			result.rejectValue("subscribername", "notFound", "not found");
			return "subscribers/findsubscribers";
		}
		else if (results.size() == 1) {
			// 1 subscriber found
			subscriber = results.iterator().next();
			return "redirect:/subscribers/" + subscriber.getId();
		}
		else {
			// multiple subscribers found
			model.put("selections", results);
			return "subscribers/subscribersList";
		}
	}

	@GetMapping(value = "/subscribers/{subscriberId}/edit")
	public String initUpdateOwnerForm(@PathVariable("subscriberId") int subscriberId, Model model) {
		Subscriber subscriber = this.subscriberService.findSubscriberById(subscriberId);
		model.addAttribute(subscriber);
		return VIEWS_SUBSCRIBER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/subscribers/{subscriberId}/edit")
	public String processUpdateOwnerForm(@Valid Subscriber subscriber, BindingResult result,
			@PathVariable("subscriberId") int subscriberId) {
		if (result.hasErrors()) {
			return VIEWS_SUBSCRIBER_CREATE_OR_UPDATE_FORM;
		}
		else {
			subscriber.setId(subscriberId);
			this.subscriberService.saveSubscriber(subscriber);
			return "redirect:/subscribers/{subscriberId}";
		}
	}

	
	@GetMapping("/subscribers/subscriberId}")
	public ModelAndView showsubscriber(@PathVariable("subscriberId") int subscriberId) {
		ModelAndView mav = new ModelAndView("subscribers/subscriberDetails");
		mav.addObject(this.subscriberService.findSubscriberById(subscriberId));
		return mav;
	}

}
