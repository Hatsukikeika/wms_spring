package com.wms.bean;

import java.util.List;

public class PriceMenu extends HasIdentity {

	private User creator;
	
	private String title;
	
	private Boolean stared;
	
	private List<WmsService> pricelist;
	
}
