package com.candidjava.onlineshopping.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.candidjava.onlineshopping.model.Cart;
import com.candidjava.onlineshopping.model.OrderedProducts;
import com.candidjava.onlineshopping.model.Orders;

public class OrderDaoImpl implements OrderDao{
	
	JSONObject obj=null;
	
	Map map=null;
	
	@Autowired
	OnlineShoppingUserDao onlineShoppingUserDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Map placeOrder(String str) throws JSONException {
		obj=new JSONObject(str);
		
		System.out.println("products====>"+obj.toString());
		
		
		int userId=0;
		Orders order=new Orders();
		
		map=new HashMap();
		
		Session session = sessionFactory.getCurrentSession();
		
		List<OrderedProducts> listOrderProducts=new ArrayList<OrderedProducts>();
		
		if(!obj.isNull("userId")) {
				order.setUser(onlineShoppingUserDao.getUserByUserId(obj.getInt("userId")));
				userId=obj.getInt("userId");
		}
		
		order.setOrderDate(new Date());

		
		session.save(order);
		
		long orderId=order.getOrderId();

		
		List<Cart> cartProducts=productDao.getCartProducts(userId);
		
		
		for(Cart cart:cartProducts) {
			OrderedProducts orderProduct=new OrderedProducts();
			orderProduct.setOrderedProductId(cart.getProduct().getProductId());
			orderProduct.setOrders(order);
			listOrderProducts.add(orderProduct);
		
		}
		
		// adding cart products to order products
		session.save(listOrderProducts);
		
		// finally deleting cart products
		session.delete(cartProducts);
		map.put("msg", "order placed Successfully");
		
		return map;
	}



}
