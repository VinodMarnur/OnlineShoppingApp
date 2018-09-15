package com.candidjava.onlineshopping.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidjava.onlineshopping.model.User;
@Service
public class OnlineShoppingUserDaoImpl implements OnlineShoppingUserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	Map map=null;
	
	JSONObject obj=null;
	
	@Override
	public User getUserByUserId(long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user=(User) session.get(User.class,userId);
		return user;
	}

	@Override
	public Map login(String str) throws JSONException {
		
		String email="";
		String password="";
		map=new HashMap();
		Session session = sessionFactory.getCurrentSession();
		obj=new JSONObject(str);
				
		if(!obj.isNull("userName")) {
			email=obj.getString("userName");
		}
		
		if(!obj.isNull("password")) {
			password=obj.getString("password");
		}
		
		Criteria criteria=session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userEmail", email));
		criteria.add(Restrictions.eq("password", password));
		List<User> userlist=criteria.list();
		if(userlist.size()>0) {
			User user=userlist.get(0);
			if(user.getUserEmail().equals(email)) {
				map.put("msg","success");
				map.put("userId",user.getUserId());
				map.put("userName",user.getUserName());
			}else {
				map.put("msg","error");
			}
		}else {
			map.put("msg","error");
		}
				
		return map;
	}
}
