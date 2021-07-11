package com.wms.service.helper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

import com.wms.service.Exceptions.FieldMissingException;

@Component
public class ObjectHelper<T> {
	
	public T mergeObjects(T to, T from, String...ignores) throws IllegalArgumentException, IllegalAccessException{
		
		List<String> ignoreList = Arrays.asList(ignores);
		
		if(from.equals(null))
			return to;
		
		if(to.equals(null))
			throw new NullPointerException();
		
		for(Field field : to.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object obj = null;
			if(!ignoreList.contains(field.getName()))
				obj = field.get(from);
			if(obj != null)
				field.set(to, obj);
		}
		
		return to;
	}
	
	public void testNull(T object, String...ignores) throws IllegalArgumentException, IllegalAccessException {
		if(object.equals(null))
			throw new NullPointerException();
		
		List<String> ignoreList = Arrays.asList(ignores);
		
		for(Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if(field.get(object) == null && !ignoreList.contains(field.getName()))
				throw new FieldMissingException(field.getName());
		}
	}
}
