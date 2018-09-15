package com.candidjava.onlineshopping.dao;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONException;

import com.candidjava.onlineshopping.model.ContactAddress;

@Transactional
public interface OnlineShoppingContactAddressDao {
	public Map saveContactAddress(String str) throws JSONException;
	public List<ContactAddress> getContactAddreessList(String str) throws JSONException;
	public ContactAddress getContactAddreess(String str) throws JSONException;
	
}
