package com.bazlur.meetup.extended.dao;

import com.bazlur.meetup.extended.domain.Submission;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
public interface SubmissionRepository extends PagingAndSortingRepository<Submission, Long> {
}
