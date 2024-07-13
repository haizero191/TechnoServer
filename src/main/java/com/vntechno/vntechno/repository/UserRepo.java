package com.vntechno.vntechno.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vntechno.vntechno.models.User;

public interface UserRepo extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
