package com.candidjava.onlineshopping.dao;

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.candidjava.onlineshopping.model.OrderedProducts;

public interface OrderDao {
	
	Map placeOrder(String str) throws JSONException;
	

}
