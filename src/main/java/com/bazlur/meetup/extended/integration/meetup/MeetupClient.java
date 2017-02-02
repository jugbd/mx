package com.bazlur.meetup.extended.integration.meetup;

import com.bazlur.meetup.extended.MxProperties;
import org.slf4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/30/17.
 */
@Service
public class MeetupClient {
    private static final Logger LOGGER = getLogger(MeetupClient.class);

    private final RestTemplate restTemplate;

    public MeetupClient() {
        this.restTemplate = new RestTemplate();
    }

    @Cacheable("meetup.schedules")
    public List<Meetup> getRecentNews() {
        ResponseEntity<Meetup[]> response = doGetRecentNews("jug-bd");

        Meetup[] body = response.getBody();
        return Arrays.asList(body);
    }

    private ResponseEntity<Meetup[]> doGetRecentNews(String groupName) {
        LOGGER.info("going to fetch Recent news from :{}", groupName);
        String url = String.format("https://api.meetup.com/%s/events", groupName);

        return invoke(createRequestEntity(url), Meetup[].class);
    }

    private <T> ResponseEntity<T> invoke(RequestEntity<?> request, Class<T> type) {
        return this.restTemplate.exchange(request, type);
    }

    private RequestEntity<?> createRequestEntity(String url) {
        try {
            return RequestEntity.get(new URI(url))
                    .accept(MediaType.APPLICATION_JSON).build();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("Invalid URL " + url, ex);
        }
    }
}
