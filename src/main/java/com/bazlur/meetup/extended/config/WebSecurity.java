package com.bazlur.meetup.extended.config;

import com.bazlur.meetup.extended.MxProperties;
import com.bazlur.meetup.extended.dao.UserRepository;
import com.bazlur.meetup.extended.domain.User;
import com.bazlur.meetup.extended.integration.github.GithubClient;
import com.bazlur.meetup.extended.integration.github.GithubUser;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/26/17.
 */
@Configuration
@EnableOAuth2Sso
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = getLogger(WebSecurity.class);

    private final MxProperties mxProperties;

    public WebSecurity(MxProperties mxProperties) {
        this.mxProperties = mxProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/", "home", "/news").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .ignoringAntMatchers("/admin/h2-console/*")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .headers()
                .frameOptions().sameOrigin();
    }

    @Bean
    public AuthoritiesExtractor authoritiesExtractor() {
        return map -> {
            String username = (String) map.get("login");
            if (this.mxProperties.getSecurity().getAdmins().contains(username)) {
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN");
            } else {
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            }
        };
    }


    @Bean
    public PrincipalExtractor principalExtractor(GithubClient githubClient, UserRepository userRepository) {

        return map -> {
            String githubLogin = (String) map.get("login");
            LOGGER.info("githubLogin:{}", githubLogin);

            return userRepository.findByGithub(githubLogin)
                    .orElseGet(() -> {
                        GithubUser user = githubClient.getUser(githubLogin);
                        User speaker = new User();
                        speaker.setEmail(user.getEmail());
                        speaker.setName(user.getName());
                        speaker.setGithub(githubLogin);
                        speaker.setAvatarUrl(user.getAvatar());

                        return userRepository.save(speaker);
                    });

        };
    }
}
