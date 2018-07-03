package com.pcsell.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcsell.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRepository.class)
public class SpringTestUserRepository {
	
	@MockBean
	private UserRepository userRepository;

	@Test
	public void isNotNull() {
		assertNotNull(userRepository);
	}

	@Test
	public void getOne() {
		User user = new User("test@sk.com", "1", "wooyoung", "test@sk.com");
		given(this.userRepository.getOne(1L)).willReturn(user);

		User findedUser = userRepository.getOne(1L);
		assertThat(findedUser.getName()).isEqualTo("wooyoung");
	}

}
