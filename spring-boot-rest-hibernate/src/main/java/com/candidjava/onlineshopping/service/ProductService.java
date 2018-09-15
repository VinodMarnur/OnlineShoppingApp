package com.candidjava.onlineshopping.service;

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.candidjava.onlineshopping.model.Cart;
import com.candidjava.onlineshopping.model.Product;

public interface ProductService {
	public List<Product> getAllProducts();
	
	public List<Product> getSearchProducts(String serchProduct);
	
	public Product getProductById(long productId);
	
	public List<Map> getAllProductsFiter(String str) throws JSONException;
	
	
	public Map addProduct(String str)  throws JSONException;
	
	public Map getCartCount(String str) throws JSONException;
	
	List<Cart> getCartProducts(String str) throws JSONException;
	Map incrementDecrementProductCount(String str) throws JSONException;
	Map addToCart(String str) throws JSONException;
	Map deletePrdouctFromCart(String str) throws JSONException;

}
