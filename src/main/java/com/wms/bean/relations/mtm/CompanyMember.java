package com.wms.bean.relations.mtm;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wms.bean.Company;
import com.wms.bean.HasIdentity;
import com.wms.bean.User;

@Entity
@Table(name="wmscompany_member")
public class CompanyMember extends HasIdentity {

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "compmember_company", nullable = false, referencedColumnName = "data_global_id",
		foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private Company company;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "compmember_member", nullable = false, referencedColumnName = "data_global_id",
		foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
	private User member;

	public Company getCompany() {
		return company;
	}

	public CompanyMember setCompany(Company company) {
		this.company = company;
		return this;
	}

	public User getMember() {
		return member;
	}

	public CompanyMember setMember(User member) {
		this.member = member;
		return this;
	}
	
}
