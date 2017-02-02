package com.bazlur.meetup.extended.integration.meetup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/30/17.
 */
@RunWith(SpringRunner.class)
@RestClientTest(MeetupClient.class)
public class MeetupClientTest {


    @Autowired
    private MeetupClient meetupClient;

    @Autowired
    private MockRestServiceServer mockServer;


    @Test
    public void getRecentNews() {
        List<Meetup> recentNews = this.meetupClient.getRecentNews();
        //this.mockServer.verify();
        System.out.println(recentNews);
    }
}