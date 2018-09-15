package com.candidjava.customer.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidjava.customer.model.Customer;
import com.candidjava.spring.bean.User;

@Service
public class CustomerDaoImpl implements CustomerDao{

	@Autowired
	CustomerDao customerDao;
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
//		@SuppressWarnings("unchecked")
//		List<Customer> list= session.createCriteria("from Customer f ").list();
////		List<Customer> list= session.createCriteria(Customer.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		
		Criteria criteria = session.createCriteria(Customer.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Customer> customers = criteria.list();
		return customers;
	}
	
	@Override
	public Customer getCutomerById(long customerId) {
		 	Session session = sessionFactory.getCurrentSession();
			Customer customer=(Customer) session.get(Customer.class,customerId);
			return customer;
	}

	@Override
	public void addUpdateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();
		 session.save(customer); 
	}

	@Override
	public Customer updateCustomer(Customer customer, long customerId) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.getCurrentSession();
			Customer updateCustomer =(Customer)session.get(Customer.class, customerId);
			updateCustomer.setCustomerName(customer.getCustomerName());
			updateCustomer.setEmail(customer.getEmail());
			session.update(updateCustomer);
			return updateCustomer;

	}
	
	@Override
	public List<LinkedHashMap> getCustomerWithEmail(){
		
		return null;
	}

}
