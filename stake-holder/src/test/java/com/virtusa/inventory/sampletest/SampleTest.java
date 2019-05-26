package com.virtusa.inventory.sampletest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.virtusa.inventory.modal.Category;
import com.virtusa.inventory.modal.Customer;
import com.virtusa.inventory.modal.LoyaltyCard;

public class SampleTest extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testGetLoyalty() throws Exception {
		String uri = "/loyalty/card";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

	@Test
	public void testPostLoyalty() throws Exception {
		String uri = "/loyalty/card";

		LoyaltyCard loyaltyCard = new LoyaltyCard();
		loyaltyCard.setName("pasindu");
		loyaltyCard.setNumber("12345678");
		loyaltyCard.setPointBalance(10.0);
		loyaltyCard.setIssuedDate(new Date());
		loyaltyCard.setExpiryDate(new Date());
		loyaltyCard.setCategory(new Category());
		loyaltyCard.setCutomer(new Customer());
		
		String inputJson = super.mapToJson(loyaltyCard);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

}
