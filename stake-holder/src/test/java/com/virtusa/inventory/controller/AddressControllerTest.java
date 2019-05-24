package com.virtusa.inventory.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.inventory.modal.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AddressControllerTest.class)
public class AddressControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AddressController addressController; 

	@Test
	public void testUrlIsOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/addresses/address"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testUrlNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/addresses/addres"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testSaveAddress() throws Exception {
		
		Address address = new Address();
		address.setAddressLine1("no 46/3");
		address.setAddressLine2("Indrajothi Mawatha");
		address.setCity("Battaramulla");
		address.setPostalCode("01225");
		
		ObjectMapper mapper= new ObjectMapper();
		String str = mapper.writeValueAsString(address);
		
        mockMvc.perform(MockMvcRequestBuilders.post("/addresses/address")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(str))
                .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testUpdateAddress() throws Exception {
		
		Address address = new Address();
		address.setId(1);
		address.setAddressLine1("no 46/3");
		address.setAddressLine2("Indrajothi Mawatha");
		address.setCity("Kaduwela");
		address.setPostalCode("01245");
		
		ObjectMapper mapper = new ObjectMapper();
		String addressPost = mapper.writeValueAsString(address);
		
		 mockMvc.perform(MockMvcRequestBuilders.post("/addresses/address")
	        		.contentType(MediaType.APPLICATION_JSON)
	        		.content(addressPost));
		
		 address.setCity("Kottawa");
		 address.setId(1);      
		 String addressPut = mapper.writeValueAsString(address);
		mockMvc.perform(MockMvcRequestBuilders.put("/addresses/address/12")
				.contentType(MediaType.APPLICATION_JSON)
				.content(addressPut))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	

}
