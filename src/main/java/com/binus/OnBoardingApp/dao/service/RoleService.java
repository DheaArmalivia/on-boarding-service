package com.binus.OnBoardingApp.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.domain.db.Role;

@Service
public interface RoleService {

	List<Role> findAll();
	
	Optional<Role> findByRole(String role);
	
	Role saveRole(Role role);
	
}
