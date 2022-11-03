package com.binus.OnBoardingApp.domain.response;

public class UserDataResponse {

	private String username;
	private String name;
	private String role;
	private GeneralResponse status;
	
	public UserDataResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public GeneralResponse getStatus() {
		return status;
	}

	public void setStatus(GeneralResponse status) {
		this.status = status;
	}

}
