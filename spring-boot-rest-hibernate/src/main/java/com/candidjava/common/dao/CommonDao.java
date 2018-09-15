package com.candidjava.common.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONException;

import com.candidjava.common.model.City;
import com.candidjava.common.model.Country;
import com.candidjava.common.model.State;

@Transactional
public interface CommonDao {
	public List<Country> getContry();
	public List<State> getStates(String str) throws JSONException;
	public List<City> getCitites(String str)  throws JSONException;
}
