package com.virtusa.inventory.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.inventory.exception.LoyaltyCardNotFoundException;
import com.virtusa.inventory.modal.Category;
import com.virtusa.inventory.modal.LoyaltyCard;
import com.virtusa.inventory.service.LoyaltyCardService;

@RestController
@RequestMapping("/loyalty")
public class LoyaltyCardCotroller {

	@Autowired
	private LoyaltyCardService loyaltyCardService;

	@RequestMapping(value = "/card", method = RequestMethod.POST)
	public ResponseEntity<LoyaltyCard> create(@Valid @RequestBody LoyaltyCard loyaltyCard) {
		return ResponseEntity.ok(loyaltyCardService.save(loyaltyCard));
	}

	@RequestMapping(value = "/cards", method = RequestMethod.GET)
	public List<LoyaltyCard> fetchAll() {
		return loyaltyCardService.fetchAll();
	}

	@RequestMapping(value = "/card/{id}", method = RequestMethod.GET)
	public ResponseEntity<LoyaltyCard> fetchOne(@PathVariable Integer id) {
		Optional<LoyaltyCard> optionalLoyalty = loyaltyCardService.findOne(id);
		if (!optionalLoyalty.isPresent()) {
			throw new LoyaltyCardNotFoundException("id-" + id);
		}
		return ResponseEntity.ok(loyaltyCardService.findOne(id).get());
	}

	@RequestMapping(value = "/card/{id}", method = RequestMethod.PUT)
	public ResponseEntity<LoyaltyCard> update(@PathVariable Integer id, @Valid @RequestBody LoyaltyCard loyaltyCard) {
		Optional<LoyaltyCard> optionalLoyalty = loyaltyCardService.findOne(id);
		if (!optionalLoyalty.isPresent()) {
			throw new LoyaltyCardNotFoundException("id-" + id);
		}

		loyaltyCard.setId(id);
		return ResponseEntity.ok(loyaltyCardService.save(loyaltyCard));
	}

	@RequestMapping(value = "/card/{id}/category", method = RequestMethod.PUT)
	public ResponseEntity<LoyaltyCard> updateCateoryById(@PathVariable Integer id,
			@Valid @RequestBody Category category) {
		Optional<LoyaltyCard> optionalLoyalty = loyaltyCardService.findOne(id);
		if (!optionalLoyalty.isPresent()) {
			throw new LoyaltyCardNotFoundException("id-" + id);
		}
		LoyaltyCard loyaltyCard = optionalLoyalty.get();
		loyaltyCard.setCategory(category);
		return ResponseEntity.ok(loyaltyCardService.save(loyaltyCard));
	}

	@RequestMapping(value = "/card/update/customer/{cid}", method = RequestMethod.GET)
	public ResponseEntity<LoyaltyCard> updateLoyaltyCardPoints(@PathVariable Integer cid,
			@PathParam(value = "points") Double points) {
		return ResponseEntity.ok(loyaltyCardService.updatePointCustomerLoyalty(cid,points));
	}

	@RequestMapping(value = "/card/{id}", method = RequestMethod.DELETE)
	public HttpStatus delete(@PathVariable Integer id) {
		loyaltyCardService.delete(id);
		return HttpStatus.OK;
	}

	@RequestMapping(value = "/card", method = RequestMethod.GET)
	public ResponseEntity<LoyaltyCard> fetchByNumber(@PathParam(value = "number") String number) {
		Optional<LoyaltyCard> optionalLoyalty = loyaltyCardService.findByNumber(number);
		if (!optionalLoyalty.isPresent()) {
			throw new LoyaltyCardNotFoundException("Number - " + number);
		}
		return ResponseEntity.ok(optionalLoyalty.get());
	}

//	@RequestMapping(value = "/card", method = RequestMethod.GET)
//	public ResponseEntity<LoyaltyCard> fetchByCustomerEmail(@PathParam(value = "email") String email) {
//		List<LoyaltyCard> loyaltycard = loyaltyCardService.findByCustomerEmail(email);
//		if (loyaltycard.isEmpty()) {
//			throw new LoyaltyCardNotFoundException("Customer has no card contain email - " + email);
//		}
//		return ResponseEntity.ok(loyaltycard.get(0));
//	}
}
