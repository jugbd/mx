package com.bazlur.meetup.extended.web;


import com.bazlur.meetup.extended.integration.meetup.MeetupClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/30/17.
 */
@Controller
@Navigation(Section.NEWS)
public class NewsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    private final MeetupClient meetupClient;

    public NewsController(MeetupClient meetupClient) {
        this.meetupClient = meetupClient;
    }

    @GetMapping("/news")
    public String news(Model model) {
        LOGGER.debug("serving news pages");

        model.addAttribute("meetups", meetupClient.getRecentNews());
        return "news";
    }
}
