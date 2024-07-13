package com.vntechno.vntechno.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vntechno.vntechno.models.PackageResponse;
import com.vntechno.vntechno.models.User;
import com.vntechno.vntechno.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/public/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    /* Handler login */
    @PostMapping("/login")
    public PackageResponse Login() {
        List<User> users = userService.getAllUsers();
        System.out.print(users);
        return new PackageResponse(true, new ResponseEntity<>(users, HttpStatus.OK), "Login success");
    }

    /* Handler register */
    @PostMapping("/register")
    public PackageResponse Register(@Valid @RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            return new PackageResponse(true, new ResponseEntity<>(newUser, HttpStatus.OK), "Register success");
        } catch (Exception e) {
            return new PackageResponse(false,
                    new ResponseEntity<>("Register failed" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR),
                    "Register failed" + e.getMessage());
        }
    }


    @GetMapping("/test")
    public String getMethodName() {
        return "hello";
    }
    
}
