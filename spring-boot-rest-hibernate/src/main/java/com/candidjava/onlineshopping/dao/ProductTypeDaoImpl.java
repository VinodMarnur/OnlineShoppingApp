package com.candidjava.onlineshopping.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidjava.onlineshopping.model.ProductType;
@Service
public class ProductTypeDaoImpl implements ProductTypeDao{
	
	@Autowired
	private SessionFactory sessionFactory;
		
	@Override
	public ProductType getProductType(long productTypeId) {
		Session session = sessionFactory.getCurrentSession();
		ProductType productType = session.get(ProductType.class, productTypeId);
		return productType;
	}
}
