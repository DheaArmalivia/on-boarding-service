package com.binus.OnBoardingApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.binus.OnBoardingApp.controller.service.LoginControllerService;
import com.binus.OnBoardingApp.domain.db.User;
import com.binus.OnBoardingApp.domain.response.LoginResponse;

@RestController
@RequestMapping("/rest/api")
public class LoginController {

	@Autowired
	LoginControllerService loginService;
	
	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to Onboarding service";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponse login(@RequestBody User user) {
		return loginService.login(user.getUsername(), user.getPassword());
	}

}
