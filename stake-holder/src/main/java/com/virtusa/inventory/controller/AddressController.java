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

import com.virtusa.inventory.exception.AddressNotFoundException;
import com.virtusa.inventory.modal.Address;
import com.virtusa.inventory.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {
	@Autowired
	AddressService addressService;

	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public ResponseEntity<List<Address>> fetchAll() {
		return ResponseEntity.ok(addressService.fetchAll());
	}

	@RequestMapping(value = "/address", method = RequestMethod.POST)
	public ResponseEntity<Address> save(@RequestBody Address address) {
		return ResponseEntity.ok(addressService.save(address));
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Address> update(@PathVariable Integer id, @Valid @RequestBody Address address) {
		Optional<Address> optionalAddress = addressService.findOne(id);
		if(!optionalAddress.isPresent()){
			throw new AddressNotFoundException("id" + id);
		}
		address.setId(id);
		return ResponseEntity.ok(addressService.save(address));
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
	public HttpStatus delete(@PathVariable Integer id) {
		addressService.delete(id);
		return HttpStatus.OK;
	}
}
