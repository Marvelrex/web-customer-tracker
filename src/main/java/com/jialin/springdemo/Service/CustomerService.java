package com.jialin.springdemo.Service;

import com.jialin.springdemo.entity.Customer;

import java.util.List;



public interface CustomerService {

	public List<Customer> getCustomers(int sortField);

	public void addCustomer(Customer customer);

	Customer getCustomer(int theId);

	void deleteCustomer(int theId);

	List<Customer> searchCustomer(String name);
}
