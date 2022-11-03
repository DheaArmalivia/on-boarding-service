package com.binus.OnBoardingApp.controller.service;

import java.util.Optional;

import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.dao.service.UserService;
import com.binus.OnBoardingApp.domain.db.User;
import com.binus.OnBoardingApp.domain.response.LoginResponse;

@Service
public class LoginControllerService {

	@Autowired
	UserService userService;
	
	public LoginControllerService() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginResponse login(String username, String password) {
		LoginResponse response = new LoginResponse();
		Optional<User> login = userService.findByUsernameAndPasswordAndActive(username, password, true);
		if(login.isPresent()) {
			User user = login.get();
			response.setError("false");
			response.setMessage("Successfully login");
			response.setUsername(user.getUsername());
			response.setName(user.getName());
			response.setRole("");
		} else {
			response.setError("false");
			response.setMessage("User is not exist");
		}
		return response;
	}

}
