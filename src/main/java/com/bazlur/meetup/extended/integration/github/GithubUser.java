package com.bazlur.meetup.extended.integration.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
@Getter
@Setter
public class GithubUser implements Serializable {
    private String email;
    private String name;
    private String company;
    private String blog;

    @JsonProperty("avatar_url")
    private String avatar;
}
