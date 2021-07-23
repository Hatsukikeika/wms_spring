package com.wms.service;

import com.wms.bean.User;
import com.wms.bean.DTO.UserCreationRequest;

public interface AccountService {
	void createRootAccount(UserCreationRequest reg);
	
	void createSubAccount(Long groupid, String email);
	
	void removeSubAccount(Long groupid, Long userid);
	
	void validateAccount(Long userid, Long vid, String key);
	
	void resendValidation(Long groupid, String email);
	
	void changePassword(Long groupid, String email, String oldpass, String newpass);
	
	void forgotPassword(Long groupid, String email);
	
	User getAccountDetail(Long userid);
	
}
