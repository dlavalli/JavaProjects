package com.bharath.useradmin.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.bharath.useradmin.dto.User;
import com.bharath.useradmin.util.IDGenerator;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(IDGenerator.class)
public class UserDAOTest {

	// THIS DOES NOT WORK WITH ANYTHING PASS JDK 8

	@Test
	public void createShouldReturnAUserId() {

		UserDAO dao = new UserDAO();

		mockStatic(IDGenerator.class);
		when(IDGenerator.generateID()).thenReturn(1);
		int result = dao.create(new User());
		assertEquals(1, result);
		verifyStatic();
	}

}
