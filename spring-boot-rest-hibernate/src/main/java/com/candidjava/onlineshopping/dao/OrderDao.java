package com.candidjava.onlineshopping.dao;


import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONException;


@Transactional
public interface OrderDao {
	
	Map placeOrder(String str) throws JSONException;
	

}
