package com.interview.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.interview.common.InputValidationException;
import com.interview.service.util.AvatarValidator;

public class ExclusionServiceImplTest {

	@Mock
	private AvatarValidator avatarValidator;

	@Mock
	private Map<String, String> ssnDobMap;

	@InjectMocks
	private ExclusionServiceImpl serviceImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(avatarValidator.validateRequest(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(true);
	}

	@After
	public void tearDown() throws Exception {
		ssnDobMap.clear();
	}

	@Test
	public void test_validate() {
		boolean retVal = serviceImpl.validate("2018-12-24T15:15:45.453+05:30", "111-222-3333");
		assertFalse(retVal);
	}

	@Test
	public void test_validate_Blacklisted() {
		ssnDobMap.put("111-222-3333", "2018-12-24T15:15:45.453+05:30");
		
		when(ssnDobMap.get(ArgumentMatchers.anyString())).thenReturn("2018-12-24T15:15:45.453+05:30");

		boolean retVal = serviceImpl.validate("2018-12-24T15:15:45.453+05:30", "111-222-3333");
		assertTrue(retVal);
	}

	@Test (expected = InputValidationException.class)
	public void test_validate_InvalidInput() {
		when(avatarValidator.validateRequest(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenThrow(new InputValidationException("",  "",  ""));

		serviceImpl.validate("2018-12-24T15:15:45.453+05:30", "111-222-3333");
	}

	@Test
	public void test_addBlacklist() {
		Map<String, String> blackListMap = new HashMap<>();
		blackListMap.put("111-222-3333", "2018-12-24T15:15:45.453+05:30");
		blackListMap.put("111-222-4444", "2018-12-24T15:15:45.453+05:30");
		blackListMap.put("111-222-5555", "2018-12-30T15:15:45.453+05:30");

		Map<String, String> retVal = serviceImpl.addBlacklist(blackListMap);
		assertNotNull(retVal);
		verify(ssnDobMap, times(1)).put("111-222-3333", "2018-12-24T15:15:45.453+05:30");
		verify(ssnDobMap, times(1)).put("111-222-4444", "2018-12-24T15:15:45.453+05:30");
		verify(ssnDobMap, times(1)).put("111-222-5555", "2018-12-30T15:15:45.453+05:30");
	}

	@Test (expected = InputValidationException.class)
	public void test_addBlacklist_EmptyInput() {
		Map<String, String> blackListMap = new HashMap<>();
		serviceImpl.addBlacklist(blackListMap);
	}

	@Test (expected = InputValidationException.class)
	public void test_addBlacklist_NullInput() {
		serviceImpl.addBlacklist(null);
	}

	@Test
	public void test_clearBlacklist() {
		serviceImpl.clearBlacklist();
		verify(ssnDobMap, times(1)).clear();
	}

}
