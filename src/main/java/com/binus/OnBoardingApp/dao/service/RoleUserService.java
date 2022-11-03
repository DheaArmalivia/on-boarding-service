package com.binus.OnBoardingApp.dao.service;

import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.domain.db.RoleUser;

@Service
public interface RoleUserService {
	
	void save(RoleUser role);
	
	void deleteById(Long id);

}
