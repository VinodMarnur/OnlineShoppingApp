package com.candidjava.common.model;

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
@Table(name="country")
public class Country {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long countryId;
	String countryName;
	
	Country(){
		
	}
	

	public Country(long countryId, String countryName) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}


	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
//
//
//	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	List<State> state;
}
