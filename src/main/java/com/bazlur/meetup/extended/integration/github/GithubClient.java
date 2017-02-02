package com.bazlur.meetup.extended.integration.github;

import com.bazlur.meetup.extended.MxProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/31/17.
 */
@Service
public class GithubClient {

    private final RestTemplate restTemplate;

    public GithubClient(RestTemplateBuilder restTemplateBuilder,
                        MxProperties properties) {
        this.restTemplate = restTemplateBuilder.additionalCustomizers(rt ->
                rt.getInterceptors().add(new GithubAppTokenInterceptor(properties.getGithub().getToken()))).build();
    }

    @Cacheable("github.user")
    public GithubUser getUser(String githubId) {

        return invoke(createRequestEntity(
                String.format("https://api.github.com/users/%s", githubId)), GithubUser.class).getBody();
    }

    private RequestEntity<?> createRequestEntity(String url) {
        try {
            return RequestEntity.get(new URI(url))
                    .accept(MediaType.APPLICATION_JSON).build();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("Invalid URL " + url, ex);
        }
    }

    private <T> ResponseEntity<T> invoke(RequestEntity<?> request, Class<T> type) {
        try {
            return this.restTemplate.exchange(request, type);
        } catch (RestClientException ex) {
            throw ex;
        }
    }

    private class GithubAppTokenInterceptor implements ClientHttpRequestInterceptor {
        private final String token;

        GithubAppTokenInterceptor(String token) {
            this.token = token;
        }

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            if (StringUtils.hasText(this.token)) {
                byte[] basicAuthValue = this.token.getBytes(StandardCharsets.UTF_8);
                request.getHeaders().set(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString(basicAuthValue));
            }
            return execution.execute(request, body);
        }
    }
}
