package com.spring.springdemo.service;

import java.util.List;
import com.spring.springdemo.entity.*;
public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);
	
	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
}
