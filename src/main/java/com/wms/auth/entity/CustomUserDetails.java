package com.wms.auth.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wms.bean.Company;
import com.wms.bean.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;
	private Company belongsTo;
	
	public CustomUserDetails(User user, Company belongsTo) {
		this.user = user;
		this.belongsTo = belongsTo;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(Company belongsTo) {
		this.belongsTo = belongsTo;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(user.getRole().asStr()));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
	public boolean isGroupActivated() {
		return this.belongsTo.getActivated();
	}

	public boolean isUserBanned() {
		return this.user.getIsban();
	}
	
	public boolean isUserActivated() {
		return this.user.getActivated();
	}
	
}
