package com.virtusa.inventory.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.inventory.exception.CustomerNotFoundException;
import com.virtusa.inventory.modal.Category;
import com.virtusa.inventory.modal.Customer;
import com.virtusa.inventory.modal.LoyaltyCard;
import com.virtusa.inventory.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public ResponseEntity<Customer> saveC(@Valid @RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.save(customer));
	}

	@RequestMapping(value = "/details/{id}/loyalty", method = RequestMethod.POST)
	public ResponseEntity<Customer> createLoyalty(@PathVariable Integer id,
			@Valid @RequestBody LoyaltyCard loyaltyCard) {
		Optional<Customer> optionalCustomer = customerService.findOne(id);
		if (!optionalCustomer.isPresent()) {
			throw new CustomerNotFoundException("customer is not avilable for Id-" + id);

		}
		Customer customerUpdated = optionalCustomer.get();
		customerUpdated.setCard(loyaltyCard);
		return ResponseEntity.ok(customerService.save(customerUpdated));
	}

	@RequestMapping(value = "/details/{id}/loyalty/category", method = RequestMethod.POST)
	public ResponseEntity<Customer> createCustomerLoyaltyCategory(@PathVariable Integer id,
			@Valid @RequestBody Category category) {

		Optional<Customer> optionalCustomer = customerService.findOne(id);
		if (!optionalCustomer.isPresent()) {
			throw new CustomerNotFoundException("customer is not avilable for Id-" + id);

		}
		Customer customerUpdated = optionalCustomer.get();
		customerUpdated.getCard().setCategory(category);
		return ResponseEntity.ok(customerService.save(customerUpdated));
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> fetchAll() {
		return ResponseEntity.ok(customerService.fetchAll());
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Customer>> fetchCustomer(@PathVariable Integer id) {
		return ResponseEntity.ok(customerService.fetchCustomer(id));
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> update(@Valid @PathVariable Integer id, @RequestBody Customer customer) {

		Optional<Customer> optionalCustomer = customerService.findOne(id);
		if (!optionalCustomer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found for this Id- " + id);
			// return ResponseEntity.notFound().build();
		}
		customer.setId(id);
		return ResponseEntity.ok(customerService.save(customer));

	}
	@RequestMapping(value = "/details/{id}/loyalty/category",method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomerLoyaltyCategory(@PathVariable Integer id, @RequestBody Category category){
		Optional<Customer> optional= customerService.fetchCustomer(id);
		
		if(!optional.isPresent()) {
			throw new CustomerNotFoundException("Customer is not avilable");
		}
		
				
				
		Customer customerUpdate=optional.get();
		customerUpdate.getCard().setCategory(category);
		return ResponseEntity.ok(customerService.save(customerUpdate));
		
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.DELETE)
	public HttpStatus delete(@Valid @PathVariable Integer id) {
		customerService.deleteCustomer(id);
		return HttpStatus.OK;
	}
	

}
