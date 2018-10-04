package com.candidjava.onlineshopping.dao;

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

import com.candidjava.common.model.City;
import com.candidjava.onlineshopping.model.ContactAddress;
import com.candidjava.onlineshopping.model.User;

@Service
public class OnlineShoppingContactAddressDaoImpl implements OnlineShoppingContactAddressDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	JSONObject obj=null;
	
	Map map;
	
	@Override
	public Map saveContactAddress(String str) throws JSONException {
		String msg="";
		
		obj=new JSONObject(str);
		map=new HashMap();
		System.out.println("obj=========input========"+obj.toString());
		ContactAddress contactAddress=new ContactAddress();
		Session session=sessionFactory.getCurrentSession();
		if(!obj.isNull("contactAddressId")) {
			contactAddress.setContactAddressId(obj.getLong("contactAddressId"));
		}
		
		if(!obj.isNull("firstName")) {
			contactAddress.setFirstName(obj.getString("firstName"));
		}
		
		if(!obj.isNull("lastName")) {
			contactAddress.setLastName(obj.getString("lastName"));
		}
		
		if(!obj.isNull("userId")) {
			contactAddress.setUser(session.get(User.class, obj.getLong("userId")));
		}
	
		if(!obj.isNull("email")) {
			contactAddress.setEmail(obj.getString("email"));
		}
		
		if(!obj.isNull("cityId")) {
			contactAddress.setCity(session.get(City.class, obj.getLong("cityId")));
		}
		
		if(!obj.isNull("addressLine")) {
			contactAddress.setAddressLine(obj.getString("addressLine"));
		}
		
		if(!obj.isNull("zipCode")) {
			contactAddress.setZipCode(obj.getString("zipCode"));
		}
		
		 System.out.println("first check contact Address Id"+contactAddress.getContactAddressId());
		
		 msg=validate(msg,contactAddress);
		 if(msg.equals(""))
		 {
			 
			 System.out.println("contact Address Id"+contactAddress.getContactAddressId());
			 
			 if(contactAddress.getContactAddressId()>0) {
				 session.update(contactAddress);
				 map.put("msg","Contact Address Updated" );
				 map.put("status", 200);
			 }else {
				 session.save(contactAddress);
				 map.put("msg","Contact Address Adeded" );
				 map.put("status", 200);
		     }
		 }else {
			 msg=msg.substring(0, msg.length()-1);
			 map.put("msg",msg );
		 }
		
		return map;
	}
	
	public String validate(String msg,ContactAddress contactAddress){

		if(contactAddress.getFirstName()==null || contactAddress.getFirstName().equals("")) {
			msg+="Enter FirstName,";
		}
		
		if(contactAddress.getLastName()==null || contactAddress.getLastName().equals("")) {
			msg+="Enter LastName,";
		}
		
		if(contactAddress.getEmail()==null || contactAddress.getEmail().equals("")) {
			msg+="Enter Email,";
		}
		
		if(contactAddress.getCity()==null) {
			msg+="Please Select city,";
		}
				
		if(contactAddress.getAddressLine()==null ||contactAddress.getAddressLine().equals("")) {
			msg+="Enter Address,";
		}
		
		return msg;
	}

	@Override
	public List<ContactAddress> getContactAddreessList(String str) throws JSONException {
		// TODO Auto-generated method stub
		long userId=0;
		obj=new JSONObject(str);
		if(!obj.isNull("userId")) {
			userId=obj.getLong("userId");
		}
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(ContactAddress.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Criteria userCriteria=criteria.createCriteria("user",JoinType.INNER_JOIN);
		userCriteria.add(Restrictions.eq("userId", userId));
		return criteria.list();
	}

	@Override
	public ContactAddress getContactAddreess(String str) throws JSONException {
		// TODO Auto-generated method stub
		long contactAddressId=0;
		Session session=sessionFactory.getCurrentSession();
		obj=new JSONObject(str);
		if(!obj.isNull("contactAddressId")) {
			contactAddressId=obj.getLong("contactAddressId");
		}
		return session.get(ContactAddress.class, contactAddressId);
	}

}
