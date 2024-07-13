package com.vntechno.vntechno.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vntechno.vntechno.models.Role;
import com.vntechno.vntechno.models.User;
import com.vntechno.vntechno.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
    private RoleService roleService;
	
	public User login(User userlogin) {
		User user = userRepo.findByEmail(userlogin.getEmail());
		return user;
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public User createUser(User user) {

		// Set default role for user 
		Role defaultRole = roleService.getRole("USER");
		user.setRole(defaultRole);

		// Encoded password before save
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepo.save(user);
	}
}
