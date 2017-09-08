package com.pcsell.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pcsell.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
}
