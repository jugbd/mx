package com.bazlur.meetup.extended.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/26/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testIndex_shouldReturnHomePage() throws Exception {

		this.mockMvc.perform(get("/").with(user("user")))
			.andExpect(status().isOk());
	}
}
