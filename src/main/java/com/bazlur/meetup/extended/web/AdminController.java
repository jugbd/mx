package com.bazlur.meetup.extended.web;

import com.bazlur.meetup.extended.dao.SubmissionRepository;
import com.bazlur.meetup.extended.domain.SubmissionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
@Controller
@Navigation(Section.ADMIN)
@RequestMapping("admin")
public class AdminController {

    private final SubmissionRepository submissionRepository;

    public AdminController(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @GetMapping("")
    public String admin(Model model) {

        model.addAttribute("submissions", this.submissionRepository.findAll());
        model.addAttribute("viewHelper", new ViewRenderingHelper());

        return "admin/index";
    }

    private class ViewRenderingHelper {
        public String submissionTableClass(SubmissionStatus status) {
            if (SubmissionStatus.ACCEPTED.equals(status)) {
                return "success";
            } else if (SubmissionStatus.REJECTED.equals(status)) {
                return "error";
            }
            return "";
        }
    }
}
