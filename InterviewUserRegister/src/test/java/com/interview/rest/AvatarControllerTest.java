package com.interview.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.interview.service.IAvatarService;
import com.interview.service.IExclusionService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AvatarController.class)
public class AvatarControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private IAvatarService avatarService;

	@Mock
	private IExclusionService exclusionService;

	@MockBean
	private AvatarController controller;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_post_register() throws Exception {
		String request = "{" + 
				"    \"userName\": \"abc1\"," + 
				"    \"password\": \"Abc1\"," + 
				"    \"dob\": \"2006-12-01T10:10:40.000+05:30\"," + 
				"    \"ssn\": \"111-11-1111\"" + 
				"}";

		mockMvc.perform(post("/avatars/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
				).andExpect(status().isCreated());
	}

	@Test
	public void test_get_validate() throws Exception {
		mockMvc.perform(get("/avatars/validate/")
                .param("ssn", "111-11-1111")
                .param("dob", "2006-12-01T10:10:40.000%2b05:30")
				).andExpect(status().isOk());
	}

	@Test
	public void test_post_blacklist() throws Exception {
		String blackList = "{ \"111-11-1111\": \"2006-12-01T10:10:40.000+05:30\" }";

		mockMvc.perform(post("/avatars/blacklist")
				.contentType(MediaType.APPLICATION_JSON)
				.content(blackList)
				).andExpect(status().isOk());
	}

	@Test
	public void test_delete_blacklist() throws Exception {
		mockMvc.perform(delete("/avatars/blacklist")).andExpect(status().isOk());
	}
}
