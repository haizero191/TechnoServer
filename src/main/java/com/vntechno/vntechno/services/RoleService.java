package com.vntechno.vntechno.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vntechno.vntechno.models.Role;
import com.vntechno.vntechno.repository.RoleRepo;

@Service
public class RoleService {
    @Autowired
	private RoleRepo roleRepo;

    public Role getRole(String roleType) {
		Role role = roleRepo.findByType(roleType);
		return role;
	}
}
