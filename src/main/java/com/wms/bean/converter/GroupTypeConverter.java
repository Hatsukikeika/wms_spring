package com.wms.bean.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.wms.bean.enu.GroupType;

@Converter(autoApply = true)
public class GroupTypeConverter implements AttributeConverter<GroupType, Integer> {

	@Override
	public Integer convertToDatabaseColumn(GroupType attribute) {
		if(attribute == null)
			throw new NullPointerException();
		return attribute.asNum();
	}

	@Override
	public GroupType convertToEntityAttribute(Integer dbData) {
		return GroupType.get(dbData);
	}
}
