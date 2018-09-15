package com.candidjava.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.candidjava.customer.model.Employee;
import com.candidjava.onlineshopping.model.ContactAddress;
import com.candidjava.onlineshopping.model.ProductImages;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long cityId;
	String cityName;
	
	@ManyToOne
	State state;

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Employee> employee;
	
//	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	List<ContactAddress> contactAddress;
	
		
}
