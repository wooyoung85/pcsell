package com.pcsell.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcsell.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

	List<User> findFirst10ByName(String name);
}
