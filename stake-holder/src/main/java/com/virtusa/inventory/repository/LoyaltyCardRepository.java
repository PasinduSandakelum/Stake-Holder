package com.virtusa.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.inventory.modal.LoyaltyCard;

public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Integer> {
	
	Optional<LoyaltyCard> findByNumber(String number);

//	@Query("Select lc from LoyaltyCard as lc where lc.customer.email=:email")
//	List<LoyaltyCard> findByCustomerEmail(@Param("email") String email);
}
