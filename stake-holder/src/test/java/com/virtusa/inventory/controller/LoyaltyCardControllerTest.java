package com.virtusa.inventory.controller;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.inventory.modal.LoyaltyCard;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(LoyaltyCardControllerTest.class)
public class LoyaltyCardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoyaltyCardCotroller loyaltyCardCotroller;

	@BeforeTransaction
	public void setData() throws Exception {
		System.out.println("Before Test");

		LoyaltyCard loyaltyCard = new LoyaltyCard();
		loyaltyCard.setName("pasindu");
		loyaltyCard.setNumber("12345678");
		loyaltyCard.setPointBalance(10.0);
		loyaltyCard.setIssuedDate(new Date());
		loyaltyCard.setExpiryDate(new Date());

		// Convert LoyaltyCard object into String
		ObjectMapper mapper = new ObjectMapper();
		String loyaltyJson = mapper.writeValueAsString(loyaltyCard);

		System.out.println("Posting some data.....");
		// post data first
		mockMvc.perform(MockMvcRequestBuilders.post("/loyalty/card").contentType(MediaType.APPLICATION_JSON)
				.content(loyaltyJson));
	}

	@Test
	public void testLoyaltyCardGetStatusIsOk() throws Exception {

		System.out.println("\nTesting testLoyaltyCardGetStatusIsOk");
		// test get method
		mockMvc.perform(MockMvcRequestBuilders.get("/loyalty/card").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testLoyaltyCardGetByIdStatusIsOk() throws Exception {

		System.out.println("\nTesting testLoyaltyCardGetByIdStatusIsOk");
		// test get method
		mockMvc.perform(MockMvcRequestBuilders.get("/loyalty/card/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testLoyaltyCardGetStatusIsNotFound() throws Exception {
		System.out.println("\nTesting testLoyaltyCardGetStatusIsNotFound");
		mockMvc.perform(MockMvcRequestBuilders.get("/loyalty/cards").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void testLoyaltyCardPutStatusIsOk() throws Exception {

		LoyaltyCard loyaltyCardPut = new LoyaltyCard();
		loyaltyCardPut.setId(1);
		loyaltyCardPut.setName("pasindu");
		loyaltyCardPut.setNumber("12345678");
		loyaltyCardPut.setPointBalance(10.0);
		loyaltyCardPut.setIssuedDate(new Date());
		loyaltyCardPut.setExpiryDate(new Date());

		// Convert LoyaltyCard object into String
		ObjectMapper mapper = new ObjectMapper();
		String loyaltyJson = mapper.writeValueAsString(loyaltyCardPut);
//		System.out.println(loyaltyJson);

		System.out.println("\nTesting testLoyaltyCardPutStatusIsOk");
		// test post method
		mockMvc.perform(MockMvcRequestBuilders.put("/loyalty/card/100").contentType(MediaType.APPLICATION_JSON)
				.content(loyaltyJson)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testLoyaltyCardDeleteStatusIsOk() throws Exception {

		System.out.println("\nTesting testLoyaltyCardDeleteStatusIsOk");
		// test delete method
		mockMvc.perform(MockMvcRequestBuilders.delete("/loyalty/card/10"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
