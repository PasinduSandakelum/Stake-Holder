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

import com.virtusa.inventory.exception.TelephoneNotFoundException;
import com.virtusa.inventory.modal.Telephone;
import com.virtusa.inventory.service.TelephoneService;

@RestController
@RequestMapping("/telephones")
public class TelephoneController {

	@Autowired
	private TelephoneService telephoneService;

	@RequestMapping(value = "/telephone", method = RequestMethod.GET)
	public ResponseEntity<List<Telephone>> fetchAll() {
		return ResponseEntity.ok(telephoneService.fetchAll());
	}

	@RequestMapping(value = "/telephone", method = RequestMethod.POST)
	public ResponseEntity<Telephone> save(@RequestBody Telephone telephone) {
		return ResponseEntity.ok(telephoneService.save(telephone));
	}

	@RequestMapping(value = "/telephone/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Telephone> update(@PathVariable Integer id, @Valid @RequestBody Telephone telephone) {
		Optional<Telephone> optionalTelephone = telephoneService.findOne(id);
		if(!optionalTelephone.isPresent()){
			throw new TelephoneNotFoundException("ID" +id);
		}
		telephone.setId(id);
		return ResponseEntity.ok(telephoneService.save(telephone));
	}

	@RequestMapping(value = "/telephone/{id}", method = RequestMethod.DELETE)
	public HttpStatus delete(@PathVariable Integer id) {
		telephoneService.delete(id);
		return HttpStatus.OK;
	}
}
