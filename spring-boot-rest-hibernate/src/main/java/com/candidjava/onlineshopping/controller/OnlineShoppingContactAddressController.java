package com.candidjava.onlineshopping.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidjava.onlineshopping.dao.OnlineShoppingContactAddressDao;
import com.candidjava.onlineshopping.model.ContactAddress;



@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class OnlineShoppingContactAddressController {
	
	@Autowired
	OnlineShoppingContactAddressDao onlineShoppingContactAddressDao;
	
	
	@RequestMapping("/save-contact-address")
	public Map createContactAddress(@RequestBody String str) throws JSONException {
		return onlineShoppingContactAddressDao.saveContactAddress(str);
	}
	
	@RequestMapping("/get-contact-address-list")
	public List<ContactAddress> getContactAddreessList(@RequestBody String str) throws JSONException {
		return onlineShoppingContactAddressDao.getContactAddreessList(str);
	}
	
	@RequestMapping("/get-contact-address")
	public ContactAddress getContactAddreess(@RequestBody String str) throws JSONException {
		return onlineShoppingContactAddressDao.getContactAddreess(str);
	}

}
