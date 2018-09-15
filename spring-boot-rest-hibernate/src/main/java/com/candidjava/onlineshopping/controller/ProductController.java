package com.candidjava.onlineshopping.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidjava.onlineshopping.model.Product;
import com.candidjava.onlineshopping.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("product-list")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@RequestMapping("product-list-filter")
	public ResponseEntity<?> getAllProductsFiter(@RequestBody String str) throws JSONException {
//		return productService.getAllProductsFiter(str);
		return   new ResponseEntity<>(productService.getAllProductsFiter(str), HttpStatus.OK);
	}
	
	@RequestMapping("product/{productId}")
	public Product getProductById(@PathVariable long productId) {
		return productService.getProductById(productId);
	}
	
	@PostMapping("search-product")
	public List<Product> getSearchProducts(@RequestBody String data){
		return productService.getSearchProducts(data);
				
	}
	
	@PostMapping("add-product")
	public ResponseEntity<?> addProduct(@RequestBody String data) throws JSONException{
		Map map=productService.addProduct(data);
		if(map.containsKey("errormsg")) {			
			return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(map,HttpStatus.OK);
		}
	}
	
	public String PadQuotes(String str) {
		if(str!=null) {
			return str;
		}
		return "";
	}
	
}
