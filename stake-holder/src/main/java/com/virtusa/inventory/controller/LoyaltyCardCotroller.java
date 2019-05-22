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

	@RequestMapping(value = "/card", method = RequestMethod.GET)
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
			return ResponseEntity.notFound().build();
		}

		loyaltyCard.setId(id);
		return ResponseEntity.ok(loyaltyCardService.save(loyaltyCard));
	}

	@RequestMapping(value = "/card/{id}/category", method = RequestMethod.PUT)
	public ResponseEntity<LoyaltyCard> updateCateoryById(@PathVariable Integer id, @Valid @RequestBody Category category) {
		Optional<LoyaltyCard> optionalLoyalty = loyaltyCardService.findOne(id);
		if (!optionalLoyalty.isPresent()) {
			throw new LoyaltyCardNotFoundException("id-" + id);
		}
		LoyaltyCard loyaltyCard = optionalLoyalty.get();
		loyaltyCard.setCategory(category);
		return ResponseEntity.ok(loyaltyCardService.save(loyaltyCard));
	}

	@RequestMapping(value = "/card/update/{id}", method = RequestMethod.GET)
	public ResponseEntity<LoyaltyCard> updateLoyaltyCardPoints(@PathVariable Integer id,@PathParam(value = "points") Double points) {
		Optional<LoyaltyCard> optionalLoyalty = loyaltyCardService.findOne(id);
		if (!optionalLoyalty.isPresent()) {
			throw new LoyaltyCardNotFoundException("id-" + id);
		}
		return ResponseEntity.ok(loyaltyCardService.updatePointBalance(id, points));
	}
	
	@RequestMapping(value = "/card/{id}", method = RequestMethod.DELETE)
	public HttpStatus delete(@PathVariable Integer id) {
		loyaltyCardService.delete(id);
		return HttpStatus.OK;
	}
}
