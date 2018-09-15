package com.candidjava.customer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidjava.customer.dao.CustomerDaoImpl;
import com.candidjava.customer.model.Customer;
import com.candidjava.customer.service.CustomerService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerDaoImpl dustomerDaoImpl;
	
	@RequestMapping("/customer-list")
	public List<Customer> getCustomers() {
		return customerService.getAllCustomer();
	}
	
	@RequestMapping("/customer/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") long customerId) {
		return customerService.getCustomerById(customerId);
	}
	
	
	
	@RequestMapping("/customer/add-update")
	public Customer addUpdateCustomer(@RequestBody String data) {
		return customerService.addUpdateCustomer(data);
	}
	

}
