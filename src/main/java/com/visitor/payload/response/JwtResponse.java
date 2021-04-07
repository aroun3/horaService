package com.visitor.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Integer id;
	private String fullName;
	private String username;
	private String email;
	private List<String> roles;
	private String statusCode;
	private String errorMessage;

	public JwtResponse(String accessToken, Integer id, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public JwtResponse(String token, Integer id, String username, String fullName, List<String> roles, String statusCode) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.roles = roles;
		this.statusCode = statusCode;
	}
	public JwtResponse(String token, Integer id, String username, String email, List<String> roles, String statusCode, String errorMessage) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}



	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
