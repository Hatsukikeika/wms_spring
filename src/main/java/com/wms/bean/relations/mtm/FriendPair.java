package com.wms.bean.relations.mtm;
import java.io.Serializable;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wms.bean.Group;
import com.wms.bean.HasIdentity;

@Entity
@Table(name="mapper_wmsfriend")
public class FriendPair extends HasIdentity implements Serializable {

	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "friendpair_seller", nullable = false, referencedColumnName = "data_global_id",
		foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Group seller;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "friendpair_linker", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Group linker;
	
	public FriendPair() {
		super();
	}
	
	public FriendPair(Group seller, Group linker) {
		super();
		this.seller = seller;
		this.linker = linker;
	}

	public Group getSeller() {
		return seller;
	}

	public FriendPair setSeller(Group seller) {
		this.seller = seller;
		return this;
	}

	public Group getLinker() {
		return linker;
	}

	public FriendPair setLinker(Group linker) {
		this.linker = linker;
		return this;
	}
	
	
}
