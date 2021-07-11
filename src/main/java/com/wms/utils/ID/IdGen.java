package com.wms.utils.ID;

import com.wms.JunkyFlake.JunkyFlake;

public class IdGen {
	
	private static JunkyFlake JF = new JunkyFlake();
	
	public static long nextId() {
		return JF.nextId();
	}
	
}
