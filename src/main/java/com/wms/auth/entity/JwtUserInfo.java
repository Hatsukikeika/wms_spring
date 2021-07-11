package com.wms.auth.entity;

import java.util.HashMap;
import java.util.Map;

import com.wms.utils.security.AuthInfoExtraction;

public class JwtUserInfo implements AuthInfoExtraction {

	public static final String OPTKEY_GROUPID = "groupid";
	public static final String OPTKEY_GROUPTYPE = "grouptype";
	public static final String OPTKEY_USRNAME = "username";
	public static final String OPTKEY_USRUID = "uuid";
	
	private CustomUserDetails customUserDetails;
	
	public JwtUserInfo(CustomUserDetails customUserDetails) {
		this.customUserDetails = customUserDetails;
	}

	@Override
	public Object getId() {
		return customUserDetails.getUser().getId();
	}

	@Override
	public Map<String, Object> getOpts() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(OPTKEY_GROUPID, customUserDetails.getBelongsTo().getId());
		map.put(OPTKEY_GROUPTYPE, customUserDetails.getBelongsTo().getType().asNum());
		map.put(OPTKEY_USRNAME, customUserDetails.getUsername());
		map.put(OPTKEY_USRUID, customUserDetails.getUser().getUuid());
		return map;
	}

	@Override
	public String getRole() {
		return customUserDetails.getUser().getRole().asStr();
	}

}
