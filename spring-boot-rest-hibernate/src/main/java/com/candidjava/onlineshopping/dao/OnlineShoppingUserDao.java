package com.candidjava.onlineshopping.dao;

import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONException;

import com.candidjava.onlineshopping.model.User;

@Transactional
public interface OnlineShoppingUserDao {
	User getUserByUserId(long userId);
	Map login(String str) throws JSONException;
}
