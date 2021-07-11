package com.wms.service;

import java.util.Map;

import com.wms.bean.User;

public interface AccountService {
	void createRootAccount(Map<String, Object> signup);
	
	void createSubAccount(Long groupid, String email);
	
	void removeSubAccount(Long groupid, Long userid);
	
	void validateAccount(Long userid, Long vid, String key);
	
	void resendValidation(Long groupid, String email);
	
	void changePassword(Long groupid, String email, String oldpass, String newpass);
	
	void forgotPassword(Long groupid, String email);
	
	User getAccountDetail(Long userid);
	
}
