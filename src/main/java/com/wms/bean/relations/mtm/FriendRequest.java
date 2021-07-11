package com.wms.bean.relations.mtm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wms.bean.Group;
import com.wms.bean.HasIdentity;
import com.wms.bean.enu.RequestStatus;

@Entity
@Table(name="request_wmsfriend")
public class FriendRequest extends HasIdentity implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "linkrequest_group_seller", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Group seller;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "linkrequest_group_linker", nullable = false, referencedColumnName = "data_global_id",
			foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Group linker;
	
	@Column(name = "linkrequest_status", nullable = false)
	private RequestStatus status;
	
	public FriendRequest() {
		super();
	}
	
	public FriendRequest(Group seller, Group linker) {
		super();
		this.seller = seller;
		this.linker = linker;
		this.status = RequestStatus.PENDING;
	}

	public Group getSeller() {
		return seller;
	}

	public FriendRequest setSeller(Group seller) {
		this.seller = seller;
		return this;
	}

	public Group getLinker() {
		return linker;
	}

	public FriendRequest setLinker(Group linker) {
		this.linker = linker;
		return this;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public FriendRequest setStatus(RequestStatus status) {
		this.status = status;
		return this;
	}
	
	
}
