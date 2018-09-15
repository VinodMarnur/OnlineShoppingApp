package com.candidjava.onlineshopping.controller;

import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidjava.onlineshopping.dao.OnlineShoppingUserDao;

@RestController
@CrossOrigin("*")
@RequestMapping("user")
public class OnlineShoppingUserController {
	
	@Autowired
	OnlineShoppingUserDao onlineShoppingUserDao;
	
	@PostMapping("/login")
	public Map login(@RequestBody String str) throws JSONException {
		return onlineShoppingUserDao.login(str);
	}
}
