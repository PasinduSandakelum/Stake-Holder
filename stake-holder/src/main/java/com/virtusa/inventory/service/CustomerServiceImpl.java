package com.virtusa.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.virtusa.inventory.modal.Customer;
import com.virtusa.inventory.modal.Telephone;
import com.virtusa.inventory.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer save(Customer customer) {
		if (customer.getTelephone() != null) {
			for (Telephone telephone : customer.getTelephone()) {
				telephone.setCustomer(customer);
			}
		}

		return customerRepository.save(customer);

	}

	@Override
	public List<Customer> fetchAll() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> findOne(Integer id) {
		return customerRepository.findById(id);
	}

	@Override
	public void deleteCustomer(Integer id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Optional<Customer> fetchCustomer(Integer id) {
		
		return customerRepository.findById(id);
	}

	
	

}
