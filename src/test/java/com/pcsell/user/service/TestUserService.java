package com.pcsell.user.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcsell.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserService {
	@Autowired
	private UserService userService;

	@Test
	public void insert() {
		User createUser;

		createUser = new User("testUserService", "1", "테스터", "tester@sk.com");
		int size = userService.list().size();

		userService.create(createUser);
		assertThat(userService.list().size(), is(size + 1));
	}
}
