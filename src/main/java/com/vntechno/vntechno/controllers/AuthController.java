package com.vntechno.vntechno.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vntechno.vntechno.models.PackageResponse;
import com.vntechno.vntechno.models.User;
import com.vntechno.vntechno.services.UserService;
import com.vntechno.vntechno.utils.jsonwebtoken.JWTUtil;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/public/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    /* Handler login */
    @PostMapping("/login")
    public PackageResponse Login(@RequestBody User user) {
        // Mã hóa mật khẩu và xác thực người dùng ở đây
        var userLogin = userService.login(user);
        if (userLogin == null) {
            return new PackageResponse(false, new ResponseEntity<>(null, HttpStatus.OK),
                    "Login failed. This email is not registered. Please register a new account to use");
        } else {
            boolean passwordMatch = passwordEncoder.matches(user.getPassword(), userLogin.getPassword());
            if (!passwordMatch) {
                return new PackageResponse(false, new ResponseEntity<>(null, HttpStatus.OK),
                        "Login failed. Password is incorrect, please try again or register a new account!");
            }

            Map<String, Object> dataObj = new HashMap<>();
            String jwt = jwtUtil.generateToken(userLogin.getId(), userLogin.getEmail(), userLogin.getRole().getType());

            dataObj.put("token", jwt);
            return new PackageResponse(true, new ResponseEntity<>(dataObj, HttpStatus.OK), "Login success.");
        }
    }

    /* Handler register */
    @PostMapping("/register")
    public PackageResponse Register(@Valid @RequestBody User user) {
        try {
            // Create new user
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
