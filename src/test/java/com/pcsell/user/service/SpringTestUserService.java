package com.pcsell.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcsell.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
public class SpringTestUserService {

	@MockBean
	private UserService userService;

	private User listUser1;
	private User listUser2;
	private User findOneUser;

	@Before
	public void setUp() {
		listUser1 = new User("test1@sk.com", "1", "test1", "test1@sk.com");
		listUser2 = new User("test2@sk.com", "2", "test2", "test2@sk.com");
		findOneUser = new User("test@sk.com", "1", "wooyoung", "test@sk.com");
	}

	@Test
	public void isNotNull() {
		assertNotNull(userService);
	}

	@Test
	public void list() {
		List<User> userList = new ArrayList<User>();

		given(userService.list()).willReturn(userList);

		userList.add(listUser1);
		userList.add(listUser2);

		List<User> selectedUserList = userService.list();

		assertThat(selectedUserList.size()).isEqualTo(2);
	}

	@Test
	public void findOne() {
		given(userService.findOne(1L)).willReturn(findOneUser);

		User findedUser = userService.findOne(1L);
		assertThat(findedUser.getName()).isEqualTo("wooyoung");
	}
}
