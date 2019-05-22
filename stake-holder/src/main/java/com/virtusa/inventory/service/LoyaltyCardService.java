package com.virtusa.inventory.service;

import java.util.List;
import java.util.Optional;

import com.virtusa.inventory.modal.LoyaltyCard;

public interface LoyaltyCardService {

	List<LoyaltyCard> fetchAll();

	LoyaltyCard save(LoyaltyCard loyaltyCard);

	Optional<LoyaltyCard> findOne(Integer id);

	void delete(Integer id);

	LoyaltyCard updatePointBalance(Integer id,Double points);
}