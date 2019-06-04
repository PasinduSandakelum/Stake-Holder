package com.virtusa.inventory.service;

import java.util.List;
import java.util.Optional;

import com.virtusa.inventory.modal.Category;

public interface CategoryService {

	List<Category> fetchAll();
	
	public long getNoOfCategories();

	Category save(Category category);

	Category update(Category category);

	void delete(Integer id);
	
	public void deleteAll();
	
	public Optional<Category> findById(Integer id);

}