package com.wms.bean.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserCreationRequest {
	
	@NotBlank
	@Email
	@Size(min=6, max=36)
	private String email;
	
	@NotBlank
	@Size(min=6, max=20)
	private String password;
	
	@NotBlank
	@Size(min=1, max=30)
	private String cname;
	
	@NotBlank
	@Size(min=1, max=30)
	private String firstname;
	
	@NotBlank
	@Size(min=1, max=30)
	private String lastname;
	
	@NotBlank
	@Size(min=1, max=30)
	private String main_street;
	
	@NotBlank
	@Size(min=1, max=30)
	private String sub_street;
	
	@NotBlank
	@Size(min=1, max=30)
	private String city;
	
	@NotBlank
	@Size(min=1, max=30)
	private String state;
	
	@NotBlank
	@Size(min=1, max=30)
	private String country;
	
	@Email
	private String invcode;
	
	@NotNull
	@Min(1)
	@Max(2)
	private Integer grouptype;
	
	@NotNull
	@Min(10000)
	@Max(999999)
	private Integer zipcode;
	
	@NotNull
	private Long contact;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMain_street() {
		return main_street;
	}

	public void setMain_street(String main_street) {
		this.main_street = main_street;
	}

	public String getSub_street() {
		return sub_street;
	}

	public void setSub_street(String sub_street) {
		this.sub_street = sub_street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getInvcode() {
		return invcode;
	}

	public void setInvcode(String invcode) {
		this.invcode = invcode;
	}

	public Integer getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(Integer grouptype) {
		this.grouptype = grouptype;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}
	
	
	
}
