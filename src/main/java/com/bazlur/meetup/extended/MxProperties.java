package com.bazlur.meetup.extended;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
@ConfigurationProperties("mx")
public class MxProperties {
    private final Github github = new Github();

    private final Security security = new Security();

    public Github getGithub() {
        return this.github;
    }

    public Security getSecurity() {
        return this.security;
    }

    public static class Github {

        /**
         * Access token ("username:access_token") to query public github endpoints.
         */
        private String token;

        public String getToken() {
            return this.token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class Security {

        /**
         * Github users that have admin rights.
         */
        private List<String> admins = Arrays.asList("rokon12");

        public List<String> getAdmins() {
            return admins;
        }

        public void setAdmins(List<String> admins) {
            this.admins = admins;
        }

    }
}
