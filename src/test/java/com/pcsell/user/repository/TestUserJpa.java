package com.pcsell.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcsell.user.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestUserJpa {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void test() {
		User articleByKwseo = new User("kwseo", "good", "hello", "kwseo@sk.com");
		User articleByKim = new User("kim", "good", "hello", "kim@sk.com");
		entityManager.persist(articleByKwseo);

		List<User> users = userRepository.findFirst10ByName("hello");
		assertThat(users).isNotEmpty().hasSize(1).contains(articleByKwseo).doesNotContain(articleByKim);
	}
}
