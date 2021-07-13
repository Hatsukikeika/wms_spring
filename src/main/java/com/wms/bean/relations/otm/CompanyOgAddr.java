package com.wms.bean.relations.otm;

import com.wms.bean.Address;
import com.wms.bean.Company;
import com.wms.bean.HasIdentity;

public class CompanyOgAddr extends HasIdentity {

	private Company company;
	
	private Address outgoing;
}
