package com.bazlur.meetup.extended.dto;

import com.bazlur.meetup.extended.domain.Track;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
@Data
public class SubmissionForm {
	@NotNull(message = "Track should not be empty")
	private Track track;

	@NotEmpty(message = "Title should not be empty")
	@Size(max = 200)
	private String title;

	@NotEmpty(message = "Summary should not be empty")
	@Size(max = 1000)
	private String summary;
}
