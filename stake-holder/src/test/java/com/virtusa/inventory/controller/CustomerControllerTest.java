package com.virtusa.inventory.controller;

import java.util.Date;

import org.hibernate.query.criteria.internal.expression.SearchedCaseExpression.WhenClause;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.inventory.modal.Address;
import com.virtusa.inventory.modal.Category;
import com.virtusa.inventory.modal.Customer;
import com.virtusa.inventory.modal.LoyaltyCard;
import com.virtusa.inventory.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CustomerControllerTest.class)
public class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CustomerController customerController;

	@Test
	public void testCustomerIsOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/customer/details")).
		andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testCustomerNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/customer/detail")).
		andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void testCustomerPostStatusIsOk() throws Exception {

		Customer customer = new Customer();
		customer.setFirstName("saman");
		customer.setLastName("gunarathne");
		customer.setDateOfBirth(new Date("04/09/1993"));
		customer.setGender("Male");
		customer.setOccupation("business");
		customer.setSalutation("Mr");
		customer.setEmail("saman@virtusa.com");
		

		ObjectMapper mapper = new ObjectMapper();
		String customerJson = mapper.writeValueAsString(customer);

		mockMvc.perform(MockMvcRequestBuilders.post("/customer/details").contentType(MediaType.APPLICATION_JSON)
				.content(customerJson)).andExpect(MockMvcResultMatchers.status().isOk());
	}
}
