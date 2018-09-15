package com.candidjava.customer.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.customer.dao.CustomerDao;
import com.candidjava.customer.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerDao customerDao;
	JSONObject obj=null;
	
	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerDao.getAllCustomers();
	}
	
	@Override
	public Customer getCustomerById(long customerId) {
		return customerDao.getCutomerById(customerId);
	}

	@Override
	public Customer addUpdateCustomer(String jsonData)  {
		Customer customer=new Customer();
		long customerId=0;
		try {
		obj=new JSONObject(jsonData);
		if(!obj.isNull("customerId")) {
			customerId=obj.getLong("customerId");
			customer.setCustomerId(obj.getLong("customerId"));
		}
	
		if(!obj.isNull("customerName")) {
			customer.setCustomerName(obj.getString("customerName"));
		}
		if(!obj.isNull("email")) {
			customer.setEmail(obj.getString("email"));
		}
		if(!obj.isNull("mobileNo")) {
			customer.setMobileNo(obj.getString("mobileNo"));
		}
		
		System.out.println("CustomerId"+customerId);
		if(customerId!=0) {
			customer=customerDao.updateCustomer(customer, customerId);
		}else {
			customerDao.addUpdateCustomer(customer);
		}
		}catch(Exception e) {
			System.out.println("Exception "+e);
		}
		return customer;
	}

}
