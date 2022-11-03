package com.binus.OnBoardingApp.controller.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binus.OnBoardingApp.dao.service.RoleService;
import com.binus.OnBoardingApp.dao.service.RoleUserService;
import com.binus.OnBoardingApp.dao.service.UserService;
import com.binus.OnBoardingApp.domain.db.Role;
import com.binus.OnBoardingApp.domain.db.RoleUser;
import com.binus.OnBoardingApp.domain.db.User;
import com.binus.OnBoardingApp.domain.response.GeneralResponse;
import com.binus.OnBoardingApp.domain.response.UserDataResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class UserDataControllerService {
	
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleUserService roleUserService;
	
	public UserDataControllerService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<User> getAllUser() {
		return userService.findAll();
	}
	
	public User save(User user) {
		user.setModifiedDate(getTimestamp());
		user = userService.save(user);
		return user;
	}
	
	public String saveUser(User user) throws JsonProcessingException {
		GeneralResponse genResp = new GeneralResponse();
		Optional<User> existingUser = userService.findByUsername(user.getUsername());
		if(existingUser.isPresent()) {
			genResp.setError("false");
			genResp.setMessage("User with username " + existingUser.get().getUsername() 
					+ " is already exist!");
		} else {
			user.setActive(true);
			user = userService.save(user);
			genResp.setError("false");
			genResp.setMessage("Successfully created user with username " + user.getUsername());
		}
		ObjectNode result = mapper.createObjectNode();
		result.set("user", mapper.convertValue(user, JsonNode.class));
		result.set("result", mapper.convertValue(genResp, JsonNode.class));
		return result.toPrettyString();
	}
	
	public String createUserWithRole(String req) throws JsonMappingException, JsonProcessingException {
		JsonNode node = mapper.readTree(req);
		String msg = "";
		User user = new User();
		GeneralResponse genResp = new GeneralResponse();
		
		if(node.has("username") && node.has("name") && node.has("password")) {
			Optional<User> existingUser = userService.findByUsername(node.get("username").asText());
			if(existingUser.isPresent()) {
				msg += "User " + existingUser.get().getUsername() + " is already exist, failed to create new user.";
				genResp.setError("true");
			} else {
				user.setActive(true);
				user.setUsername(node.get("username").asText());
				user.setName(node.get("name").asText());
				user.setPassword(node.get("password").asText());
				user.setCreatedDate(getTimestamp());
				user = userService.save(user);
				msg += "User has successfully created; ";
				genResp.setError("false");
				
				if(node.has("role")) {
					Optional<Role> existingRole = roleService.findByRole(node.get("role").asText());
					
					if(existingRole.isPresent()) {
						RoleUser userRole = new RoleUser();
						userRole.setCreatedDate(getTimestamp());
						userRole.setUserId(String.valueOf(user.getId()));
						userRole.setRoleId(String.valueOf(existingRole.get().getId()));
						roleUserService.save(userRole);
						msg += "Successfully assign role " + node.get("role").asText() + " to user : " + user.getUsername() + "; ";
						genResp.setError("false");
					} else {
						genResp.setError("true");
						msg += "Failed to assign role, role " + node.get("role").asText() + " is not exist; ";
					}
				}
				
			}
			
		}
		
		genResp.setMessage(msg);
		ObjectNode result = mapper.createObjectNode();
		result.set("user", mapper.convertValue(user, JsonNode.class));
		result.set("result", mapper.convertValue(genResp, JsonNode.class));
		
		
		return result.toPrettyString();
	}
	
	public Role saveRole(Role role) {
		Date dt = new Date();
		Timestamp tm = new Timestamp(dt.getTime());
		role.setModifiedDate(tm);
		Role result = roleService.saveRole(role);
		return result;
	}
	
	public List<Role> findAllRole() {
		List<Role> result = roleService.findAll();
		return result;
	}
	
	public UserDataResponse assignRole(String req) throws JsonMappingException, JsonProcessingException {
		UserDataResponse result = new UserDataResponse();
		GeneralResponse genResps = new GeneralResponse();
		JsonNode node = mapper.readTree(req);
		
		if(node.has("username")) {
			String username = node.get("username").asText();
			Optional<User> userExist = userService.findByUsername(username);
			if(userExist.isPresent()) {
				genResps.setCode(200);
				genResps.setError("true");
				
				if(node.has("role")) {
					String role = node.get("role").asText();
					Optional<Role> roleExist = roleService.findByRole(role);
					if(roleExist.isPresent()) {
						RoleUser userRole = new RoleUser();
						userRole.setUserId(String.valueOf(userExist.get().getId()));
						userRole.setRoleId(String.valueOf(roleExist.get().getId()));
						userRole.setCreatedDate(getTimestamp());
						userRole.setModifiedDate(getTimestamp());
						roleUserService.save(userRole);
						genResps.setMessage("Successfully assigning role " + role + " to user " + username);
						result.setName(userExist.get().getName());
						result.setRole(role);
						result.setUsername(username);
					} else {
						genResps.setCode(200);
						genResps.setError("true");
						genResps.setMessage("Role : " + role + " is not exist. Assigning role failed.");
					}
				}
				
			} else {
				genResps.setCode(200);
				genResps.setError("true");
				genResps.setMessage("User with username : " + username + " is not exist. Assigning role failed.");
			}
		}
		
		result.setStatus(genResps);
		
		return result;
	}
	
	public Timestamp getTimestamp() {
		Date dt = new Date();
		return new Timestamp(dt.getTime());
	}

}
