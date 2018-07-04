package com.pcsell.user.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcsell.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void insert() {
		User createUser = new User("testUserRepository", "1", "테스트트트트", "tester@sk.com");		
		int size = userRepository.findAll().size();
		
		userRepository.save(createUser);
		assertThat(userRepository.findAll().size(), is(size + 1));
		
		userRepository.delete(createUser);
		assertThat(userRepository.findAll().size(), is(size));
	}

}
