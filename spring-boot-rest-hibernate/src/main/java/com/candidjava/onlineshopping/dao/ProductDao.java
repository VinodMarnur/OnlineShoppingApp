package com.candidjava.onlineshopping.dao;

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.candidjava.onlineshopping.model.Cart;
import com.candidjava.onlineshopping.model.Product;

public interface ProductDao {
		List<Product>  getAllProducts();
		Product  getProductById(long productId);
		List<Product> getSearchProducts(String serchProduct);
		List<Map> getAllProductsFiter(String str) throws JSONException;
		Map addProduct(String str)  throws JSONException;
		long getCartCount(String str) throws JSONException;
		List<Cart> getCartProducts(String str) throws JSONException;
		Map incrementDecrementProductCount(String str) throws JSONException;
		Map addToCart(String str) throws JSONException;
		Map deletePrdouctFromCart(String str) throws JSONException;
}
