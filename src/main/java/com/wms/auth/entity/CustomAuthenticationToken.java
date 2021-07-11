package com.wms.auth.entity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	public CustomAuthenticationToken(CustomUserDetails principal, String credentials) {
		super(principal, credentials);
	}

	
}
