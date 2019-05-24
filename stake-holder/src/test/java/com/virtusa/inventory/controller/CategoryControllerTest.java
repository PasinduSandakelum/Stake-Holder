package com.virtusa.inventory.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.inventory.modal.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CategoryControllerTest.class)
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CategoryController categoryController;

	@Test
	public void testUrlIsOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/categories/detail"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testUrlNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/categories/details"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void testSaveUpdateCategory() throws Exception {

		// Save
		Category category = new Category();
		category.setPointRange(1500);
		category.setType("Gold");

		ObjectMapper mapper = new ObjectMapper();
		String categoryAsString = mapper.writeValueAsString(category);
		mockMvc.perform(MockMvcRequestBuilders.post("/categories/detail").contentType(MediaType.APPLICATION_JSON)
				.content(categoryAsString)).andExpect(MockMvcResultMatchers.status().isOk());
		
		//update
		category.setId(2);
		category.setPointRange(2000);
		category.setType("Platinum");
		categoryAsString = mapper.writeValueAsString(category);

		mockMvc.perform(MockMvcRequestBuilders.put("/categories/detail/1").contentType(MediaType.APPLICATION_JSON)
				.content(categoryAsString)).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testUpdateCategory() throws Exception {

		Category category = new Category();
		category.setPointRange(12000);
		category.setType("Vibranium");
		category.setId(1);

		ObjectMapper mapper = new ObjectMapper();
		String categoryAsString = mapper.writeValueAsString(category);
		mockMvc.perform(MockMvcRequestBuilders.put("/categories/detail/1").contentType(MediaType.APPLICATION_JSON)
				.content(categoryAsString)).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
