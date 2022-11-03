package com.binus.OnBoardingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.binus.OnBoardingApp.controller.service.UserDataControllerService;
import com.binus.OnBoardingApp.domain.db.Role;
import com.binus.OnBoardingApp.domain.db.User;
import com.binus.OnBoardingApp.domain.response.UserDataResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/rest/api")
public class UserDataController {
	
	@Autowired
	UserDataControllerService service;

	public UserDataController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public List<User> getUser() {
		return service.getAllUser();
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@RequestBody User req) throws JsonProcessingException {
		req.setCreatedDate(service.getTimestamp());
		return service.saveUser(req);
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@RequestBody String req) throws JsonProcessingException {
		return service.createUserWithRole(req);
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		return service.save(user);
	}
	
	@RequestMapping(value = "/deactivateUser", method = RequestMethod.POST)
	public User deactivateUser(@RequestBody User req) {
		req.setActive(false);
		req = service.save(req);
		return req;
	}
	
	@RequestMapping(value = "/activateUser", method = RequestMethod.POST)
	public User activateUser(@RequestBody User req) {
		req.setActive(true);
		req = service.save(req);
		return req;
	}
	
	@RequestMapping(value = "/addNewRole", method = RequestMethod.POST)
	public Role addNewRole(@RequestBody Role req) {
		req.setCreatedDate(service.getTimestamp());
		return service.saveRole(req);
	}
	
	@RequestMapping(value = "/getRoles", method = RequestMethod.GET)
	public List<Role> getRoles() {
		return service.findAllRole();
	}
	
	@RequestMapping(value = "/assignRole", method = RequestMethod.POST)
	public UserDataResponse assignRoleToUser(@RequestBody String reqMsg) throws JsonMappingException, JsonProcessingException {
		return service.assignRole(reqMsg);
	}
	

}
