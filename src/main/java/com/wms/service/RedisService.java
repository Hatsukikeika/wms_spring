package com.wms.service;

import java.util.List;

public interface RedisService {

	/**
	 * 保存属性
	 */
	void set(String key, Object value, Long time);

	/**
	 * 保存属性
	 */
	void set(String key, Object value);

	/**
	 * 获取属性
	 */
	Object get(String key);

	/**
	 * 删除属性
	 */
	void del(String key);

	/**
	 * 批量删除属性
	 */
	void del(List<String> keys);

	/**
	 * 设置过期时间
	 */
	Boolean setExpire(String key, long time);

	/**
	 * 获取过期时间
	 */
	Long getExpire(String key);

	/**
	 * 判断是否有该属性
	 */
	Boolean hasKey(String key);
}
