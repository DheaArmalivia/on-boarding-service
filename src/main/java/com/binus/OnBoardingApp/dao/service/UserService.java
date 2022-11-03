package com.binus.OnBoardingApp.dao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.domain.db.User;

@Service
public interface UserService {

	Optional<User> findByUsernameAndPasswordAndActive(String username, String password, 
			Boolean active);
	
	Optional<User> findByUsername(String username);
	
	User save(User user);
	
	List<User> findAll();
	
}
