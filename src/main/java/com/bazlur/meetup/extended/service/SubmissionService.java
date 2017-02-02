package com.bazlur.meetup.extended.service;

import com.bazlur.meetup.extended.dao.SubmissionRepository;
import com.bazlur.meetup.extended.domain.Submission;
import com.bazlur.meetup.extended.domain.User;
import com.bazlur.meetup.extended.dto.SubmissionForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Transactional
    public Submission create(SubmissionForm submissionForm, User user) {
        Submission submission = new Submission();
        submission.setTitle(submissionForm.getTitle());
        submission.setSummary(submissionForm.getSummary());
        submission.setTrack(submissionForm.getTrack());
        submission.setSpeaker(user);

        return this.submissionRepository.save(submission);
    }
}
