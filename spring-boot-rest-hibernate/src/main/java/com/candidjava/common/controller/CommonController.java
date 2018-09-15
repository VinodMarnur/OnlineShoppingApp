package com.candidjava.common.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidjava.common.dao.CommonDao;
import com.candidjava.common.model.City;
import com.candidjava.common.model.Country;
import com.candidjava.common.model.State;

@RequestMapping("common")
@RestController
@CrossOrigin("*")
public class CommonController {

	@Autowired
	CommonDao commonDao;
	
	@RequestMapping("/contry-list")
	public List<Country> getCountry(){
		return commonDao.getContry();
	}
	
	@PostMapping("/state-list")
	public List<State> getStates(@RequestBody String str)  throws JSONException{
		return commonDao.getStates(str);
	}
	
	
	@PostMapping("/city-list")
	public List<City> getCities(@RequestBody String str)  throws JSONException{
		return commonDao.getCitites(str);
	}
}
