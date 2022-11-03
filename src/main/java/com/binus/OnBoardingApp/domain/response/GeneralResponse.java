package com.binus.OnBoardingApp.domain.response;

public class GeneralResponse {

	private Integer code;
	private String error;
	private String message;
	

	public GeneralResponse() {
		// TODO Auto-generated constructor stub
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

}
