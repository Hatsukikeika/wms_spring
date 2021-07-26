package com.wms.bean.relations.mtm;
import java.io.Serializable;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wms.bean.Company;
import com.wms.bean.HasIdentity;
import com.wms.bean.PriceMenu;
import com.wms.bean.SellerCompany;
import com.wms.bean.WarehouseCompany;

@Entity
@Table(name="mapper_wmsfriend")
public class FriendPair extends HasIdentity implements Serializable {

	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "friendpair_seller", nullable = false, referencedColumnName = "data_global_id",
		foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private SellerCompany seller;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "friendpair_warehouse", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private WarehouseCompany warehouse;
	
	//@OneToOne(fetch = FetchType.LAZY, optional = true)
	//@JoinColumn(name = "friendpair_pricemenu", nullable = false, referencedColumnName = "data_global_id",
	//foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	//private PriceMenu pricemenu;
	
	public FriendPair() {
		super();
	}
	
	public FriendPair(SellerCompany seller, WarehouseCompany warehouse) {
		super();
		this.seller = seller;
		this.warehouse = warehouse;
	}

	public SellerCompany getSeller() {
		return seller;
	}

	public FriendPair setSeller(SellerCompany seller) {
		this.seller = seller;
		return this;
	}

	public WarehouseCompany getWarehouse() {
		return warehouse;
	}

	public FriendPair setWarehouse(WarehouseCompany warehouse) {
		this.warehouse = warehouse;
		return this;
	}
	
	
}
