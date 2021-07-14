package com.wms.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.auth.entity.CustomAuthenticationException;
import com.wms.auth.entity.JwtUserInfo;
import com.wms.auth.entity.LoginUser;
import com.wms.model.ResponseBodyWrapper;
import com.wms.utils.security.JwtUtil;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static long DEFAULT_LIFETIME = 30;
	private static long REMEMBERME_LIFETIME = 60 * 24 * 7;
	
	private ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
	private AuthenticationManager authenticationManager;
	
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setFilterProcessesUrl("/api/account/login");
		super.setPostOnly(true);
	}

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            rememberMe.set(loginUser.isRememberMe());
            
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser, null)
            );
        } catch (IOException e) {
        	System.out.println(e);
        	throw new CustomAuthenticationException("Incorrect Request Format");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        JwtUserInfo jwtUserInfo = (JwtUserInfo) authResult.getPrincipal();
       
        ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper();
        
        String token = JwtUtil.makeToken(jwtUserInfo, (rememberMe.get())?REMEMBERME_LIFETIME:DEFAULT_LIFETIME);
        
        responseBodyWrapper.putOption("token", JwtUtil.TOKEN_PREFIX + token);
        responseBodyWrapper.putOption("token_expire", JwtUtil.getExpire(token));
        
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(responseBodyWrapper));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    	ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper()
    			.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
    			.setMessage(failed.getMessage());
    	
    	response.setContentType("application/json");
    	response.getWriter().write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(responseBodyWrapper));
    	
    }
	
}
