package com.bazlur.meetup.extended.domain;

import com.bazlur.meetup.extended.dao.SubmissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubmissionTest {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	public SubmissionRepository submissionRepository;

	@Test
	public void newSubmissionHasDraftStatus() {
		Submission submission = new Submission();
		Submission saved = this.submissionRepository.save(submission);
		assertThat(saved.getStatus()).isEqualTo(SubmissionStatus.DRAFT);
	}
}