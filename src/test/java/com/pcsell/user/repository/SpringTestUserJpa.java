package com.pcsell.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcsell.user.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpringTestUserJpa {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository repository;

	@Test
	public void testExample() throws Exception {
		this.entityManager.persist(new User("wooyoung", "1111", "wooyoung", "wooyoung@test.com"));
		User user = this.repository.findByUserId("wooyoung");
		assertThat(user.getUserId()).isEqualTo("wooyoung");
		assertThat(user.getPassword()).isEqualTo("1111");
	}
}
