package com.pcsell.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pcsell.user.domain.User;
import com.pcsell.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	@Override
	public List<User> list() {
		return userRepository.findAll();
	}

	@Override
	public boolean delete(Long id) {
		User user = userRepository.findOne(id);
		if(user == null){
			return false;
		}
		userRepository.delete(user);
		return true;
	}

	@Override
	public void update(Long id, User updateUser) {
		User user = userRepository.findOne(id);
		user.update(updateUser);
		userRepository.save(user);
	}

	@Override
	public void create(User user) {
		userRepository.save(user);		
	}

	@Override
	public User view(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
