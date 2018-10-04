package com.candidjava.onlineshopping.controller;

import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidjava.onlineshopping.dao.OrderDao;

@RestController
@CrossOrigin("*")
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	OrderDao orderDao;
	
	@RequestMapping("/place-order")
	public Map placeOrder(@RequestBody String str) throws JSONException {
		return orderDao.placeOrder(str);
	}

}
