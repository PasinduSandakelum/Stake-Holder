package com.virtusa.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.inventory.modal.LoyaltyCard;
import com.virtusa.inventory.repository.LoyaltyCardRepository;

@Service
public class LoyaltyCardServiceImpl implements LoyaltyCardService {

	@Autowired
	private LoyaltyCardRepository loyaltyCardRepository;

	@Override
	public List<LoyaltyCard> fetchAll() {
		return loyaltyCardRepository.findAll();
	}

	@Override
	public LoyaltyCard save(LoyaltyCard loyaltyCard) {
		return loyaltyCardRepository.save(loyaltyCard);
	}

	@Override
	public Optional<LoyaltyCard> findOne(Integer id) {
		return loyaltyCardRepository.findById(id);
	}

	@Override
	public void delete(Integer id) {
		loyaltyCardRepository.deleteById(id);
	}

	@Override
	public LoyaltyCard updatePointBalance(Integer id, Double points) {
		LoyaltyCard updatedLoyaltyCard = loyaltyCardRepository.findById(id).get();
		updatedLoyaltyCard.setPointBalance(updatedLoyaltyCard.getPointBalance() + points);
		return loyaltyCardRepository.save(updatedLoyaltyCard);

	}

	@Override
	public Optional<LoyaltyCard> findByNumber(String number) {
		return loyaltyCardRepository.findByNumber(number);
	}

//	@Override
//	public List<LoyaltyCard> findByCustomerEmail(String email) {
//		return loyaltyCardRepository.findByCustomerEmail(email);
//	}
}
