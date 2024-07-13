package com.vntechno.vntechno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vntechno.vntechno.models.User;
import com.vntechno.vntechno.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	/* Get All User - GET */
	@GetMapping("/list")
	public ResponseEntity<List<User>> Get() {
		List<User> users = userService.getAllUsers();
		
		System.out.print(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
	} 
	
	
	/* Create a new user - POST */
	@PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
		System.out.println("Received user data: " + user);
		
        User createdUser = userService.createUser(user);
     
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
