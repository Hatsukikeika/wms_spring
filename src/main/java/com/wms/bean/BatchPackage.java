package com.wms.bean;

import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="wmsbatch")
@DiscriminatorValue("batch")
public class BatchPackage extends Package {
	
	@ElementCollection
	private Map<Long, WmsService> services;

}
