package com.interview.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.interview.model.AvatarEntity;

public class AvatarDaoServiceTest {

	private final String USER_ID = "abc1";
	private final Long ROW_ID = 1L;

	@Mock
	private Map<String, Long> avatarMap;

	@Mock
	private IAvatarRepository avatarRepo;

	@Mock
	private AvatarEntity avatarEntity;

	@InjectMocks
	private AvatarDaoService daoService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		when(avatarMap.get(ArgumentMatchers.anyString())).thenReturn(ROW_ID);
		when(avatarRepo.getOne(ArgumentMatchers.anyLong())).thenReturn(avatarEntity);
		when(avatarRepo.save(ArgumentMatchers.any())).thenReturn(avatarEntity);
		when(avatarEntity.getUserName()).thenReturn(USER_ID);
		when(avatarEntity.getAvatarId()).thenReturn(ROW_ID);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_getByName() {
		AvatarEntity retVal = daoService.getByName(USER_ID);
		assertNotNull(retVal);
	}

	@Test
	public void test_getByName_Invalid() {
		AvatarEntity retVal = daoService.getByName(null);
		assertNull(retVal);
	}

	@Test
	public void test_saveOrUpdate() {
		AvatarEntity entitiy = new AvatarEntity();
		entitiy.setAvatarId(ROW_ID);
		entitiy.setDob("2018-12-30T15:15:45.453+05:30");
		entitiy.setSsn("111-222-5555");
		entitiy.setPassword("abC1");
		entitiy.setUserName(USER_ID);
		
		daoService.saveOrUpdate(entitiy);
		verify(avatarMap, times(1)).put(entitiy.getUserName(), entitiy.getAvatarId());
	}

	@Test
	public void test_delete() {
		daoService.delete(USER_ID);
		verify(avatarMap, times(1)).remove(USER_ID);
		verify(avatarRepo, times(1)).deleteById(ROW_ID);
	}

	@Test
	public void test_delete_NotFound() {
		when(avatarMap.get(ArgumentMatchers.anyString())).thenReturn(null);

		daoService.delete(USER_ID);
		verify(avatarMap, times(0)).remove(USER_ID, ROW_ID);
		verify(avatarRepo, times(0)).deleteById(ROW_ID);
	}
}
