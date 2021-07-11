package com.wms.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wms.DAO.GroupRepository;
import com.wms.DAO.UserRepository;
import com.wms.auth.entity.CustomAuthenticationException;
import com.wms.auth.entity.CustomUserDetails;
import com.wms.bean.Group;
import com.wms.bean.User;

@Service
public class CustomUserDetailService {
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsernameAndDomain(String email, Long groupid) throws AuthenticationException {
		
		Group group = groupRepository.findOne(groupid);
		if(group == null)
			throw new CustomAuthenticationException("Group: " + groupid + " not found");
		
		User user = userRepository.findByEmailAndGroupId(email, groupid);
		if(user == null)
			throw new UsernameNotFoundException(email + " is not in group: " + groupid);
		
		return new CustomUserDetails(user, group);
	}
}
