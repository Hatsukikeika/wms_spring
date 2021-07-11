package com.wms.bean.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.wms.bean.enu.UserRole;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {

	@Override
	public Integer convertToDatabaseColumn(UserRole attribute) {
		if(attribute == null)
			throw new NullPointerException();
		return attribute.asNum();
	}

	@Override
	public UserRole convertToEntityAttribute(Integer dbData) {
		return UserRole.get(dbData);
	}

}
