package com.wms.bean.enu;

import java.util.HashMap;
import java.util.Map;

public enum UserRole {
	ADMIN(10000, "ROLE_ADMIN_ULTI"),
	SELLER(0, "ROLE_ROOT_SELLER"),
	SELLER_SUB(1, "ROLE_SUB_SELLER"),
	STORAGE(2, "ROLE_ROOT_STORAGE"),
	STORAGE_SUB(3, "ROLE_SUB_STORAGE");

    private final Integer numForm;

    private final String strForm;

    private static final Map<Integer, UserRole> lookup = new HashMap<Integer, UserRole>();

    static {
        for (UserRole d : UserRole.values()) {
            lookup.put(d.numForm, d);
        }
    }

    private UserRole(Integer numForm, String strForm) {
        this.numForm = numForm;
        this.strForm = strForm;
    }

    public Integer asNum() {
        return numForm;
    }
    
    public String asStr() {
    	return strForm;
    }

    public String asAuth() {
    	return strForm.replaceAll("ROLE_", "");
    }
    
    public boolean is(Integer key) {
    	return numForm.equals(key);
    }
    
    public static UserRole get(Integer key) {
    	if(!lookup.containsKey(key))
    		throw new RuntimeException(key + "is not a valid UserRole.");
        return lookup.get(key);
    }
    
    public static String parseInt(Integer key) {
    	if(!lookup.containsKey(key))
    		throw new RuntimeException(key + "is not a valid UserRole.");
        return lookup.get(key).strForm;
    }
}
