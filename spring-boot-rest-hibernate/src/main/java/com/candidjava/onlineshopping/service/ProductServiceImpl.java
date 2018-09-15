package com.candidjava.onlineshopping.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.onlineshopping.dao.ProductDao;
import com.candidjava.onlineshopping.dao.ProductTypeDaoImpl;
import com.candidjava.onlineshopping.model.Cart;
import com.candidjava.onlineshopping.model.Product;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductTypeDaoImpl productOrderDaoImpl;
	
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public Product getProductById(long productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public List<Product> getSearchProducts(String serchProduct) {
		return productDao.getSearchProducts(serchProduct);
	}

	@Override
	public List<Map> getAllProductsFiter(String str) throws JSONException {
		return productDao.getAllProductsFiter(str);
	}

	@Override
	public Map addProduct(String str) throws JSONException {
		return productDao.addProduct(str);
	}

	@Override
	public Map getCartCount(String str) throws JSONException {
		Map map=new HashMap();
		map.put("cartCount", productDao.getCartCount(str));
		return map;
	}

	@Override
	public List<Cart> getCartProducts(String str) throws JSONException {
		return productDao.getCartProducts(str);
	}

	@Override
	public Map incrementDecrementProductCount(String str) throws JSONException {
		return productDao.incrementDecrementProductCount(str);
	}

	@Override
	public Map addToCart(String str) throws JSONException {
		return productDao.addToCart(str);
	}

	@Override
	public Map deletePrdouctFromCart(String str) throws JSONException {
		// TODO Auto-generated method stub
		return productDao.deletePrdouctFromCart(str);
	}

}
