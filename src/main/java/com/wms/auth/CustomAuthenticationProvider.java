package com.wms.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.wms.auth.entity.CustomAuthenticationException;
import com.wms.auth.entity.CustomUserDetails;
import com.wms.auth.entity.JwtUserInfo;
import com.wms.auth.entity.LoginUser;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailService.loadUserByUsernameAndDomain(loginUser.getEmail(),loginUser.getCompanyName());
		
		if(!customUserDetails.isGroupActivated())
			throw new CustomAuthenticationException("User group inactivated");
		
		if(!customUserDetails.isUserActivated())
			throw new CustomAuthenticationException("User account inactivated");
		
		if(customUserDetails.isUserBanned())
			throw new CustomAuthenticationException("User is banned");
		
		if(!bCryptPasswordEncoder.matches(loginUser.getPassword(), customUserDetails.getPassword()))
			throw new CustomAuthenticationException("Password incorrect");
		
		
		
		return new UsernamePasswordAuthenticationToken(new JwtUserInfo(customUserDetails), null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
