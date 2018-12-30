package com.interview.rest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.interview.common.InputValidationException;
import com.interview.model.AvatarRequest;
import com.interview.service.IRegistrationService;
import com.interview.service.IExclusionService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AvatarController.class)
public class AvatarControllerTest {

	private AvatarRequest avatarRequest;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private IRegistrationService registrationService;

	@Mock
	private IExclusionService exclusionService;

	@MockBean
	private AvatarController controller;

	@InjectMocks
	private AvatarController controllerMock;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		avatarRequest = new AvatarRequest();
		avatarRequest.setDob("2018-12-24T15:15:45.453+05:30");
		avatarRequest.setUserName("johndoe1");
		avatarRequest.setSsn("123-45-6789");
		avatarRequest.setPassword("paSS1");
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
				"    \"ssn\": \"111-11-1234\"" + 
				"}";

		mockMvc.perform(post("/avatars/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(request)
				).andExpect(status().isOk());
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

	@Test
	public void test_register() {
		String response = "response";
		when(registrationService.createAvatar(avatarRequest)).thenReturn(response);

		ResponseEntity<?> retVal = controllerMock.register(avatarRequest);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.getReasonPhrase().equals(HttpStatus.CREATED.getReasonPhrase()));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
		assertTrue(entityBody.equals(response));
	}

	@Test
	public void test_register_InputValidationException() {
		when(registrationService.createAvatar(avatarRequest)).thenThrow(new InputValidationException("", "", ""));

		ResponseEntity<?> retVal = controllerMock.register(avatarRequest);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.equals(HttpStatus.BAD_REQUEST));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
	}

	@Test
	public void test_register_Exception() {
		when(registrationService.createAvatar(avatarRequest)).thenThrow(new RuntimeException(""));

		ResponseEntity<?> retVal = controllerMock.register(avatarRequest);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
	}

	@Test
	public void test_validate() {
        String ssn = "111-11-1111";
        String dob = "2006-12-01T10:10:40.000%2b05:30";
		when(exclusionService.validate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(true);

		ResponseEntity<?> retVal = controllerMock.validate(ssn, dob);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.getReasonPhrase().equals(HttpStatus.OK.getReasonPhrase()));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
	}

	@Test
	public void test_validate_InputValidationException() {
        String ssn = "111-11-1111";
        String dob = "2006-12-01T10:10:40.000%2b05:30";
		when(exclusionService.validate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenThrow(new InputValidationException("", "", ""));

		ResponseEntity<?> retVal = controllerMock.validate(ssn, dob);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.equals(HttpStatus.BAD_REQUEST));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
	}

	@Test
	public void test_validate_Exception() {
        String ssn = "111-11-1111";
        String dob = "2006-12-01T10:10:40.000%2b05:30";
		when(exclusionService.validate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenThrow(new RuntimeException(""));

		ResponseEntity<?> retVal = controllerMock.validate(ssn, dob);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
	}

	@Test
	public void test_blacklist_Map() {
        String ssn = "111-11-1111";
        String dob = "2006-12-01T10:10:40.000%2b05:30";
		Map<String, String> ssnDobMap = new HashMap<>();
		ssnDobMap.put(ssn, dob);

		when(exclusionService.addBlacklist(ssnDobMap)).thenReturn(ssnDobMap);

		ResponseEntity<?> retVal = controllerMock.addToBlacklist(ssnDobMap);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.getReasonPhrase().equals(HttpStatus.OK.getReasonPhrase()));

		@SuppressWarnings("unchecked")
		Map<String, String> entityBody = (Map<String, String>) retVal.getBody();
		assertNotNull(entityBody);
	}

	@Test
	public void test_blacklist_Map_InputValidationException() {
        String ssn = "111-11-1111";
        String dob = "2006-12-01T10:10:40.000%2b05:30";
		Map<String, String> ssnDobMap = new HashMap<>();
		ssnDobMap.put(ssn, dob);
		when(exclusionService.addBlacklist(ssnDobMap)).thenThrow(new InputValidationException("", "", ""));

		ResponseEntity<?> retVal = controllerMock.addToBlacklist(ssnDobMap);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.equals(HttpStatus.BAD_REQUEST));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
	}

	@Test
	public void test_blacklist_Map_Exception() {
        String ssn = "111-11-1111";
        String dob = "2006-12-01T10:10:40.000%2b05:30";
		Map<String, String> ssnDobMap = new HashMap<>();
		ssnDobMap.put(ssn, dob);
		when(exclusionService.addBlacklist(ssnDobMap)).thenThrow(new RuntimeException(""));

		ResponseEntity<?> retVal = controllerMock.addToBlacklist(ssnDobMap);
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.equals(HttpStatus.INTERNAL_SERVER_ERROR));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
	}

	@Test
	public void test_blacklist() {
		ResponseEntity<?> retVal = controllerMock.clearBlacklist();
		assertNotNull(retVal);

		HttpStatus httpStatus = retVal.getStatusCode();
		assertNotNull(httpStatus);
		assertTrue(httpStatus.getReasonPhrase().equals(HttpStatus.OK.getReasonPhrase()));

		String entityBody = (String) retVal.getBody();
		assertNotNull(entityBody);
	}
}
