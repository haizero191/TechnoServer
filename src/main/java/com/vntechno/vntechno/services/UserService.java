package com.vntechno.vntechno.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vntechno.vntechno.models.User;
import com.vntechno.vntechno.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public User createUser(User user) {
		return userRepo.save(user);
	}
}
