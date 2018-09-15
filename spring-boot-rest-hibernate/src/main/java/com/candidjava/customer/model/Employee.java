package com.candidjava.customer.model;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.candidjava.common.model.City;


@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long employeeId;
	String employeeName;
	String dob;
	String doj;
	String email;
	
	@ManyToOne
	City city;
	
	public Employee() {
		
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Employee(long employeeId, String employeeName, String dob, String doj, String email, City city) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.dob = dob;
		this.doj = doj;
		this.email = email;
		this.city = city;
	}
	
}
