package com.bazlur.meetup.extended.web;

import com.bazlur.meetup.extended.domain.Track;
import com.bazlur.meetup.extended.domain.User;
import com.bazlur.meetup.extended.dto.SubmissionForm;
import com.bazlur.meetup.extended.service.SubmissionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
@Controller
@Navigation(Section.SUBMIT)
public class SubmissionConroller {

    private final SubmissionService submissionService;

    public SubmissionConroller(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @RequestMapping(path = "/submit", method = RequestMethod.GET)
    public String submitForm(Model model) {
        model.addAttribute("tracks", Track.values());

        model.addAttribute("submissionForm", new SubmissionForm());

        return "submit";
    }

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    public String submit(@Valid SubmissionForm submissionForm, BindingResult bindingResult,
                         @AuthenticationPrincipal User user,
                         RedirectAttributes attributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tracks", Track.values());
            model.addAttribute("submissionForm", submissionForm);

            return "submit";
        } else {
            this.submissionService.create(submissionForm, user);
            attributes.addFlashAttribute("successMessage", "Thanks! Your talk proposal has been submitted.");
            return "redirect:/submit";
        }
    }
}
