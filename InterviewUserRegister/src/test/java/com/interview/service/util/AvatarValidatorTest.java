package com.interview.service.util;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.interview.common.AppProperties;
import com.interview.common.InputValidationException;
import com.interview.model.AvatarRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:app.properties")
public class AvatarValidatorTest {

	@Value("${USER_NAME_MIN_LENGTH}")
	private int userNameMinLength;

	@Value("${USER_NAME_MAX_LENGTH}")
	private int userNameMaxLength;

	@Value("${PASSWORD_MIN_LENGTH}")
	private int passwordMinLength;

	@Value("${PASSWORD_MAX_LENGTH}")
	private int passwordMaxLength;

	@Value("${DOB_FORMAT}")
	private String dobFormat;

	@Value("${SSN_REGEX}")
	private String ssnRegex;

	@Mock
	private AppProperties appProps;

	@InjectMocks
	private AvatarValidator avatarValidator;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		when(appProps.getUserNameMinLength()).thenReturn(userNameMinLength);
		when(appProps.getUserNameMaxLength()).thenReturn(userNameMaxLength);
		when(appProps.getPasswordMinLength()).thenReturn(passwordMinLength);
		when(appProps.getPasswordMaxLength()).thenReturn(passwordMaxLength);
		when(appProps.getDobFormat()).thenReturn(dobFormat);
		when(appProps.getSsnRegex()).thenReturn(ssnRegex);
	}

	@After
	public void tearDown() throws Exception {
	}

	private AvatarRequest createAvatarRequest() {
		AvatarRequest avatarRequest = new AvatarRequest();
		avatarRequest.setDob("2018-12-24T15:15:45.453+05:30");
		avatarRequest.setUserName("johndoe1");
		avatarRequest.setSsn("123-45-6789");
		avatarRequest.setPassword("paSS1");
		return avatarRequest;
	}

	@Test
	public void test_validateRequest_OK() {
		AvatarRequest avatarRequest = createAvatarRequest();

		boolean retVal = avatarValidator.validateRequest(avatarRequest);
		assertTrue(retVal);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_UserName_1() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setUserName(null);
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_UserName_2() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setUserName("");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_UserName_3() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setUserName("aa1");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_UserName_4() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setUserName("912345678A");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_UserName_5() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setUserName("['{@@@@@");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_Password_1() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setUserName(null);
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_Password_2() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setUserName("");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_Password_3() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setPassword("aA1");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_Password_4() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setPassword("12345678Aa");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_Password_5() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setPassword("123456A");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_Password_6() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setPassword("123456789");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_Password_7() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setPassword("@$%['{");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_SSN_1() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setSsn("123456789");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_SSN_2() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setSsn("123-456-789");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_SSN_3() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setSsn("1234-56-7890");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_SSN_4() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setSsn("666-12-9999");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_SSN_5() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setSsn("666-45-9999");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_SSN_6() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setSsn("");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_SSN_7() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setSsn(null);
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_DOB_0() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setDob(null);
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_DOB_1() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setDob("");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_DOB_2() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setDob("2006-12-24T10:10:40.00005:30");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_DOB_3() {
		AvatarRequest avatarRequest = createAvatarRequest();
		avatarRequest.setDob("2006-12-2410:10:40.0000+5:30");
		avatarValidator.validateRequest(avatarRequest);
	}

	@Test
	public void test_validateRequest_OK2() {
		String ssn = "123-45-6789";
		String dob = "2006-12-24T10:10:40.000+05:30";
		avatarValidator.validateRequest(ssn, dob);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_SSN() {
		String ssn = "123-56-789";
		String dob = "2006-12-24T10:10:40.000+05:30";
		avatarValidator.validateRequest(ssn, dob);
	}

	@Test (expected = InputValidationException.class)
	public void test_validateRequest_Invalid_DOB() {
		String ssn = "123-45-6789";
		String dob = "2006-12-2410:10:40.0000+5:30";
		avatarValidator.validateRequest(ssn, dob);
	}

}
