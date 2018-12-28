package com.interview.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.interview.common.InputValidationException;
import com.interview.model.AvatarEntity;
import com.interview.model.AvatarRequest;
import com.interview.service.IAvatarDaoService;
import com.interview.service.IExclusionService;
import com.interview.service.util.AvatarValidator;

public class AvatarServiceImplTest {

	private AvatarRequest avatarRequest;

	@Mock
	private IAvatarDaoService avatarDaoService;

	@Mock
	private IExclusionService exclusionService;

	@Mock
	private AvatarValidator avatarValidator;

	@Mock
	private AvatarEntity avatarEntity;

	@InjectMocks
	private AvatarServiceImpl serviceImpl;

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
	public void test_createAvatar() {
		when(exclusionService.validate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(false);
		when(avatarDaoService.saveOrUpdate(ArgumentMatchers.any())).thenReturn(avatarEntity);

		String retVal = serviceImpl.createAvatar(avatarRequest);
		assertNotNull(retVal);
		assertFalse(retVal.isEmpty());
	}

	@Test (expected = InputValidationException.class)
	public void test_createAvatar_InvalidRequest() {
		when(avatarValidator.validateRequest(avatarRequest)).thenThrow(new InputValidationException("", "", ""));

		serviceImpl.createAvatar(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_createAvatar_Blacklisted() {
		when(exclusionService.validate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(true);

		serviceImpl.createAvatar(avatarRequest);
	}

	@Test (expected = InputValidationException.class)
	public void test_createAvatar_Duplicate() {
		when(exclusionService.validate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(false);
		when(avatarDaoService.getByName(ArgumentMatchers.anyString())).thenReturn(avatarEntity);

		serviceImpl.createAvatar(avatarRequest);
	}
}
