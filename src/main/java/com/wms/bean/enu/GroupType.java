package com.wms.bean.enu;

import java.util.HashMap;
import java.util.Map;

public enum GroupType {

	TYPE_OFFICAL(0, "OFFICAL"), TYPE_SELLER(1, "SELLER"), TYPE_STORAGE(2, "STORAGE_PROVIDER");

	private final Integer numForm;

	private final String strForm;

	private static final Map<Integer, GroupType> lookup = new HashMap<Integer, GroupType>();

	static {
		for (GroupType d : GroupType.values()) {
			lookup.put(d.numForm, d);
		}
	}

	private GroupType(Integer numForm, String strForm) {
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

	public static GroupType get(Integer key) {
		if (!lookup.containsKey(key))
			throw new RuntimeException(key + "is not a valid GroupType.");
		return lookup.get(key);
	}

	public static String parseInt(Integer key) {
		if (!lookup.containsKey(key))
			throw new RuntimeException(key + "is not a valid GroupType.");
		return lookup.get(key).strForm;
	}
}
