package com.candidjava.onlineshopping.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidjava.onlineshopping.model.Cart;
import com.candidjava.onlineshopping.model.OrderedProducts;
import com.candidjava.onlineshopping.model.Orders;

@Service
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
		
		
		long userId=0;
		Orders order=new Orders();
		
		map=new HashMap();
		
		Session session = sessionFactory.getCurrentSession();
		
		List<OrderedProducts> listOrderProducts=new ArrayList<OrderedProducts>();
		
		if(!obj.isNull("userId")) {
				order.setUser(onlineShoppingUserDao.getUserByUserId(obj.getLong("userId")));
				userId=obj.getInt("userId");
		}
		
		order.setOrderDate(new Date());

		
		session.save(order);
		
		long orderId=order.getOrderId();

		
		List<Cart> cartProducts=productDao.getCartProducts(userId);
		
		// adding cart products to order products
		
		for(Cart cart:cartProducts) {
			OrderedProducts orderProduct=new OrderedProducts();
			orderProduct.setProductId(cart.getProduct().getProductId());
			orderProduct.setOrders(order);
			session.save(orderProduct);
		
		}
		
		
		// finally deleting cart products
		for(Cart cart:cartProducts) {
			session.delete(cart);
		}
		map.put("msg", "order placed Successfully");
		
		return map;
	}

	@Override
	public List<OrderedProducts> orderList(String str) throws JSONException {
		obj=new JSONObject(str);
		System.out.println("input"+obj.toString());
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(OrderedProducts.class);
		
		Criteria ordersCriteria = criteria.createCriteria("orders",JoinType.INNER_JOIN);
		Criteria userCriteria = ordersCriteria.createCriteria("user",JoinType.INNER_JOIN);
		userCriteria.add(Restrictions.eq("userId", obj.getLong("userId")));
		criteria.uniqueResult();
		return criteria.list();
	}

}
