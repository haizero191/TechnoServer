package com.vntechno.vntechno.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vntechno.vntechno.models.Role;


public interface RoleRepo extends MongoRepository< Role , String> {
    Role findByType(String type);
}