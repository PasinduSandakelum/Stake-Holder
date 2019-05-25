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

import net.minidev.json.JSONObject;

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
				.content(categoryAsString))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
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
	
//	@Test
//	public void test() throws Exception {
//		
//		 JSONObject obj = new JSONObject();
//
//	      obj.put("id", new Integer(1));
//	      obj.put("type", new String("gold"));
//	      obj.put("typeRange", new Integer(1000));
//		
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.post("/categories/detail")
//				.contentType(MediaType.APPLICATION_JSON_VALUE)
//				.accept(MediaType.APPLICATION_JSON)
//				.characterEncoding("UTF-8")
//				.content(getArticleInJson("gold",3000));
//		
//			this.mockMvc.perform(builder)
//					.andExpect(MockMvcResultMatchers.status().isOk())
//					.andExpect(MockMvcResultMatchers.content().string(obj.toString()))/*.string("{\"id\":1, \"type\":\"gold\", \"pointRange\":3000}"))*/
//					.andDo(MockMvcResultHandlers.print());
//	}
//
//	private String getArticleInJson(String type, Integer pointRange) {
////		return "{\"type\":"+type+", \"pointRange\":"+pointRange+"}";
//		return "{\"type\":\"" + type + "\", \"pointRange\":"+pointRange+"}";
//	}

}
