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

@Entity
@Table(name="state")
public class State {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long stateId;
	String stateName;
	
	@ManyToOne
	Country country;
	
	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}


	public Country getCountry() {
		return country;
	}



	public void setCountry(Country country) {
		this.country = country;
	}
//
//	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	List<City> city;

	
}
