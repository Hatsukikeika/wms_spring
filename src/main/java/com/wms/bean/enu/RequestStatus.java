package com.wms.bean.enu;

import java.util.HashMap;
import java.util.Map;

public enum RequestStatus {

	PENDING(0, "PENDING"),DELAYED(1, "DELAYED"),ACHIEVED(2, "ACHIEVED"),CANCELLED(3, "CANCELLED");

    private final Integer numForm;

    private final String strForm;

    private static final Map<Integer, RequestStatus> lookup = new HashMap<Integer, RequestStatus>();

    static {
        for (RequestStatus d : RequestStatus.values()) {
            lookup.put(d.numForm, d);
        }
    }

    private RequestStatus(Integer numForm, String strForm) {
        this.numForm = numForm;
        this.strForm = strForm;
    }

    public Integer asNum() {
        return numForm;
    }

    public String asStr() {
    	return strForm;
    }
    
    public boolean is(Integer key) {
    	return numForm.equals(key);
    }
    
    public static RequestStatus get(Integer key) {
    	if(!lookup.containsKey(key))
    		throw new RuntimeException(key + "is not a valid RequestStatus.");
        return lookup.get(key);
    }
    
    public static String parseInt(Integer key) {
    	if(!lookup.containsKey(key))
    		throw new RuntimeException(key + "is not a valid RequestStatus.");
        return lookup.get(key).strForm;
    }
}
