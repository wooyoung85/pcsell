package com.pcsell.user.service;

import java.util.List;

import com.pcsell.user.domain.User;

public interface UserService {
	public abstract List<User> list();

	public abstract boolean delete(Long id);

	public abstract void update(Long id, User updatedUser);

	public abstract void create(User user);
	
	public abstract User findOne(Long id);
	
	public abstract User findByUserId(String userId);
}
