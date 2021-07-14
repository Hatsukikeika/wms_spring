package com.wms.auth.entity;

public class LoginUser {
	
	private String companyname;
	
	private String email;
	
	private String password;

	private Integer rememberMe;
	
	public LoginUser() {
		
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
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
	
	public boolean isRememberMe() {
		if(this.rememberMe == null || this.rememberMe != 1)
			return false;
		else
			return true;
	}
}
