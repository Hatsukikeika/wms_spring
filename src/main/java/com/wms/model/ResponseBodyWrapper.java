package com.wms.model;

import java.util.HashMap;
import java.util.Map;

public class ResponseBodyWrapper {
	private Object data;
	private Map<String, Object> payload = new HashMap<>();
	
	public ResponseBodyWrapper() {
		payload.put("status", 10000);
		payload.put("message", "OK");
	}

	public Object getData() {
		return data;
	}

	public Map<String, Object> getPayload() {
		return payload;
	}
	
	public ResponseBodyWrapper putData(Object _data) {
		this.data = _data;
		return this;
	}
	
	public ResponseBodyWrapper setStatus(Integer _code) {
		payload.replace("status", _code);
		return this;
	}
	
	public ResponseBodyWrapper setMessage(String _msg) {
		payload.replace("message", _msg);
		return this;
	}
	
	public ResponseBodyWrapper putOption(String _key, Object _val) {
		payload.put(_key, _val);
		return this;
	}
}
