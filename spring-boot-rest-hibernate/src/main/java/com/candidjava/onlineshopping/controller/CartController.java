package com.candidjava.onlineshopping.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidjava.onlineshopping.model.Cart;
import com.candidjava.onlineshopping.model.Product;
import com.candidjava.onlineshopping.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("cart-count")
	public Map getCartCount(@RequestBody String str) throws JSONException {
		return productService.getCartCount(str);
	}
		
	@RequestMapping("cart-products")
	public List<Cart> getCartProducts(@RequestBody String str) throws JSONException {
		return productService.getCartProducts(str);
	}
	
	@RequestMapping("add-to-cart")
	public Map addToCart(@RequestBody String str) throws JSONException {
		return productService.addToCart(str);
	}

	
	@RequestMapping("increment-decrement-product-count")
	public Map decrementProductCount(@RequestBody String str) throws JSONException {
		return productService.incrementDecrementProductCount(str);
	}
	
	
	@RequestMapping("delete-product-from-cart")
	public Map deletePrdouctFromCart(@RequestBody String str) throws JSONException {
		return productService.deletePrdouctFromCart(str);
	}
}
