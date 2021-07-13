package com.wms.bean.relations.mtm;
import java.io.Serializable;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wms.bean.Company;
import com.wms.bean.HasIdentity;

@Entity
@Table(name="mapper_wmsfriend")
public class FriendPair extends HasIdentity implements Serializable {

	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "friendpair_seller", nullable = false, referencedColumnName = "data_global_id",
		foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Company seller;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "friendpair_linker", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Company linker;
	
	public FriendPair() {
		super();
	}
	
	public FriendPair(Company seller, Company linker) {
		super();
		this.seller = seller;
		this.linker = linker;
	}

	public Company getSeller() {
		return seller;
	}

	public FriendPair setSeller(Company seller) {
		this.seller = seller;
		return this;
	}

	public Company getLinker() {
		return linker;
	}

	public FriendPair setLinker(Company linker) {
		this.linker = linker;
		return this;
	}
	
	
}
