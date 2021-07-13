package com.wms.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wms.DAO.CompMemberRepository;
import com.wms.DAO.UserRepository;
import com.wms.auth.entity.CustomAuthenticationException;
import com.wms.auth.entity.CustomUserDetails;
import com.wms.bean.Company;
import com.wms.bean.User;
import com.wms.bean.relations.mtm.CompanyMember;

@Service
public class CustomUserDetailService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CompMemberRepository compMemberRepository;

	public UserDetails loadUserByUsernameAndDomain(String email, String cname) throws AuthenticationException {
		
		CompanyMember cm = compMemberRepository.findByCompanyNameAndMemberEmail(cname, email);
		
		if(cm == null)
			throw new CustomAuthenticationException("Email or company infomation provided is not valid.");
		
		
		return new CustomUserDetails(cm.getMember(), cm.getCompany());
	}
}
