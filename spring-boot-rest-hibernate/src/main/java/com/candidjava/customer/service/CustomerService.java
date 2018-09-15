package com.candidjava.customer.service;

import java.util.List;

import com.candidjava.customer.model.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomer();
	
	Customer getCustomerById(long customerId);
	
	Customer addUpdateCustomer(String jsonData);

}
