package com.candidjava.customer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "app_customer_contact_address")
public class CustomerContactAddress {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long contactId;
	private String contactName;
	private String email;
	private String mobile1;
	private String mobile2;
	private String address;
	private String pincode;
	private String cityId;
	
	@ManyToOne
	Customer customer;

	public CustomerContactAddress() {
		
	}
	
	public CustomerContactAddress(long contactId, String contactName, String email, String mobile1, String mobile2,
			String address, String pincode, String cityId, Customer customer) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.email = email;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.address = address;
		this.pincode = pincode;
		this.cityId = cityId;
		this.customer = customer;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	
	
	
	
	
	
	
}
