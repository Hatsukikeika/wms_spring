package com.wms.auth.entity;

public class LoginUser {
	
	private Long groupspace;
	
	private String email;
	
	private String password;

	private Integer rememberMe;
	
	public LoginUser() {
		
	}

	public Long getGroupspace() {
		return groupspace;
	}

	public void setGroupspace(Long groupspace) {
		this.groupspace = groupspace;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRememberMe(Integer rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	public boolean isRememberMe() {
		if(this.rememberMe == null || this.rememberMe != 1)
			return false;
		else
			return true;
	}
}
