package com.wms.bean.relations.mtm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wms.bean.Company;
import com.wms.bean.HasIdentity;
import com.wms.bean.User;

@Entity
@Table(name="wmscompany_member")
public class CompanyMember extends HasIdentity {

	@Column(name="wmscompany")
	private Company company;
	
	@Column(name="wmsmember")
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
