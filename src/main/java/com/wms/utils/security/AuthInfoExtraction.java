package com.wms.utils.security;

import java.util.Map;

/**
 * @author Yi Qiu
 *
 * Initial created on 2020-12-6
 */
public interface AuthInfoExtraction {

	/**
	 * Get Identifier.
	 * @return A String of Identifier.
	 */
	public Object getId();
	
	/**
	 * Get authorization power
	 * @return String that represents the authorization power.
	 */
	public String getRole();
	
	/**
	 * Get additional payload info.
	 * @return Map that contains payload info
	 */
	public Map<String, Object> getOpts();
	
}
