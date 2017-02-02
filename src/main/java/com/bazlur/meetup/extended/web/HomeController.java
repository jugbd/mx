package com.bazlur.meetup.extended.web;

import com.bazlur.meetup.extended.integration.meetup.MeetupClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/26/17.
 */
@Controller
@Navigation(Section.HOME)
public class HomeController {

	private final MeetupClient meetupClient;

	public HomeController(MeetupClient meetupClient) {
		this.meetupClient = meetupClient;
	}

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String index(Model uiModel) {
		uiModel.addAttribute("meetups", meetupClient.getRecentNews());

		return "home";
	}
}
