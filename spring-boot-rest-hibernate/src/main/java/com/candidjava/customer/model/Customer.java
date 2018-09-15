package com.candidjava.customer.model;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "app_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long customerId;
	private String customerName;
	private String email;
	private String mobileNo;
	private Date  dob;
	
	

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<CustomerContactAddress> customerContactAddress;
	
	public Customer() {
		
	}
	
	public Customer(long customerId, String customerName, String email, String mobileNo, Date dob,List<CustomerContactAddress> customerContactAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.dob = dob;
		this.customerContactAddress=customerContactAddress;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<CustomerContactAddress> getCustomerContactAddress() {
		return customerContactAddress;
	}

	public void setCustomerContactAddress(List<CustomerContactAddress> customerContactAddress) {
		this.customerContactAddress = customerContactAddress;
	}
	
	
	
	
	
	
	


}
