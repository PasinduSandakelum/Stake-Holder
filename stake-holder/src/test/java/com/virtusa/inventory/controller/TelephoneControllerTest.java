package com.virtusa.inventory.controller;

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
import com.virtusa.inventory.modal.Telephone;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TelephoneControllerTest.class)
public class TelephoneControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TelephoneController telephoneController;

	@Test
	public void telephoneOkTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/telephones/telephone"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void telephoneNotFoundTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/telepho"))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void saveTelephoneTest() throws Exception {
		Telephone telephone = new Telephone();
		telephone.setNumber("0710336438");
		telephone.setType("mobile");

		ObjectMapper objectMapper = new ObjectMapper();
		String telephoneObject = objectMapper.writeValueAsString(telephone);

		mockMvc.perform(MockMvcRequestBuilders.post("/telephones/telephone").contentType(MediaType.APPLICATION_JSON)
				.content(telephoneObject)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void updateTelephoneTest() throws Exception {

		Telephone telephone1 = new Telephone();
		telephone1.setNumber("0710336438");
		telephone1.setType("mobile");

		ObjectMapper objectMapper = new ObjectMapper();
		String telephoneObject = objectMapper.writeValueAsString(telephone1);

		mockMvc.perform(MockMvcRequestBuilders.post("/telephones/telephone").contentType(MediaType.APPLICATION_JSON)
				.content(telephoneObject));
		
		Telephone telephonePost = new Telephone();
		telephonePost.setId(1);
		telephonePost.setNumber("0711758007");
		telephonePost.setType("home");

		String telephoneObjectPost = objectMapper.writeValueAsString(telephonePost);

		mockMvc.perform(MockMvcRequestBuilders.put("/telephones/telephone/1").contentType(MediaType.APPLICATION_JSON)
				.content(telephoneObjectPost)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void deleteTelephoneTest() throws Exception {
		Telephone telephone1 = new Telephone();
		telephone1.setNumber("0710336438");
		telephone1.setType("mobile");

		ObjectMapper objectMapper = new ObjectMapper();
		String telephoneObject = objectMapper.writeValueAsString(telephone1);

		mockMvc.perform(MockMvcRequestBuilders.post("/telephones/telephone").contentType(MediaType.APPLICATION_JSON)
				.content(telephoneObject));

		mockMvc.perform(MockMvcRequestBuilders.delete("/telephones/telephone/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
