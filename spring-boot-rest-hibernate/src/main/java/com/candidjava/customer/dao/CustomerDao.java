package com.candidjava.customer.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.candidjava.customer.model.Customer;

public interface CustomerDao {
	
	
	List<Customer> getAllCustomers();
	
	Customer getCutomerById(long customerId);
	
	void addUpdateCustomer(Customer customer);
	
    Customer updateCustomer(Customer customer,long customerId);
    
    List<LinkedHashMap> getCustomerWithEmail();
 
}
