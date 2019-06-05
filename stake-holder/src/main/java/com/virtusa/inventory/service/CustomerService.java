package com.virtusa.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.virtusa.inventory.modal.Customer;

public interface CustomerService {
	
	
	public Customer save(Customer customer);
	public List<Customer> fetchAll();
	public Optional<Customer> fetchCustomer(Integer id);
	public Optional<Customer> findOne(Integer id);
	public void deleteCustomer(Integer id);
	
	
	
}
