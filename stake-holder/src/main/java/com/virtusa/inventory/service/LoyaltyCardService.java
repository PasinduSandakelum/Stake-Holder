package com.virtusa.inventory.service;

import java.util.List;
import java.util.Optional;

import com.virtusa.inventory.modal.LoyaltyCard;

public interface LoyaltyCardService {

	List<LoyaltyCard> fetchAll();

	LoyaltyCard save(LoyaltyCard loyaltyCard);

	Optional<LoyaltyCard> findOne(Integer id);
	
	Optional<LoyaltyCard> findByNumber(String number);
	
//	List<LoyaltyCard> findByCustomerEmail(String email);

	void delete(Integer id);

	LoyaltyCard updatePointBalance(Integer id,Double points);

	LoyaltyCard updatePointCustomerLoyalty(Integer cid,Double points);
}