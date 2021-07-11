package com.wms.bean.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.wms.bean.enu.RequestStatus;

@Converter(autoApply = true)
public class RequestStatusConverter implements AttributeConverter<RequestStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(RequestStatus attribute) {
		if(attribute == null)
			throw new NullPointerException();
		return attribute.asNum();
	}

	@Override
	public RequestStatus convertToEntityAttribute(Integer dbData) {
		return RequestStatus.get(dbData);
	}

}
