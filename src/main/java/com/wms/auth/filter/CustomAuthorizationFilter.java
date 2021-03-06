package com.wms.auth.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.wms.auth.entity.JwtUserInfo;
import com.wms.bean.enu.GroupType;
import com.wms.utils.security.JwtUtil;

public class CustomAuthorizationFilter extends BasicAuthenticationFilter {

	public CustomAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String tokenHeader = request.getHeader(JwtUtil.TOKEN_HEADER);
		UsernamePasswordAuthenticationToken upat = null;
		if (tokenHeader == null || !tokenHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		String token = tokenHeader.replace(JwtUtil.TOKEN_PREFIX, "");
		boolean expiration = !JwtUtil.isNonExpired(token);
		if (expiration) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
			return;
		} else {
			String role = JwtUtil.getRole(token);
			String username = (String) JwtUtil.getValueByKey(token, JwtUserInfo.OPTKEY_USRNAME);
			String useruuid = (String) JwtUtil.getValueByKey(token, JwtUserInfo.OPTKEY_USRUID);
			Number grouptype = (Number) JwtUtil.getValueByKey(token, JwtUserInfo.OPTKEY_GROUPTYPE);
			Number groupid = (Number) JwtUtil.getValueByKey(token, JwtUserInfo.OPTKEY_GROUPID);
			Number userid = (Number) JwtUtil.getIdentifier(token);
			if (role != null && username != null && useruuid != null && groupid != null && userid != null && grouptype != null) {
				request.setAttribute("$USERNAME", username);
				request.setAttribute("$GROUPID", groupid.longValue());
				request.setAttribute("$GROUPTYPE", GroupType.get(grouptype.intValue()));
				request.setAttribute("$USERUUID", useruuid);
				request.setAttribute("$USERID", userid.longValue());
				upat = new UsernamePasswordAuthenticationToken(null, null,
						Collections.singleton(new SimpleGrantedAuthority(role))
				);
			}
		}

		SecurityContextHolder.getContext().setAuthentication(upat);
		super.doFilterInternal(request, response, chain);
	}

}