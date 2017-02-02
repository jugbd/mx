package com.bazlur.meetup.extended.integration.meetup;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/30/17.
 */
@Data
@ToString
public class Meetup implements Serializable {
	private String status;
	private Date time;
	private String name;
	private long yesRsvpCount;
	private String link;
	private String description;
}
