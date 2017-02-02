package com.bazlur.meetup.extended.domain;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
public enum SubmissionStatus {
	DRAFT("Draft"),
	SUBMITTED("Submitted"),
	ACCEPTED("Accepted"),
	REJECTED("Rejected");

	private String displayName;

	SubmissionStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public String getId() {
		return name();
	}
}
