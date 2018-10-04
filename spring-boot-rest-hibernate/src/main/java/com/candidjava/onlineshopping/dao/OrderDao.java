package com.candidjava.onlineshopping.dao;


import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONException;

import com.candidjava.onlineshopping.model.OrderedProducts;


@Transactional
public interface OrderDao {
	
	Map placeOrder(String str) throws JSONException;
	List<OrderedProducts> orderList(String str)  throws JSONException;

}
