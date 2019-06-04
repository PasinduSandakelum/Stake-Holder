package com.virtusa.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.inventory.modal.Category;
import com.virtusa.inventory.modal.Customer;
import com.virtusa.inventory.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> fetchAll() {
		return categoryRepository.findAll();
	}
	
	@Override
	public long getNoOfCategories() {
		return categoryRepository.count();
	}

	@Override
	public Category save(Category category) {
		
		return categoryRepository.save(category);
		
//		boolean matched = false;
//
//		List<Category> categoryTable = categoryRepository.findAll();
//		for (Category item : categoryTable) {
//			if (item.getType().equals(category.getType().toLowerCase())) {
//				matched = true;
//				break;
//			} else {
//				matched = false;
//			}
//		}
//		
//		if (category.getType() != null && matched == false) {
//			
//			Category newCategory = new Category();
//			newCategory.setId(category.getId());
//			newCategory.setPointRange(category.getPointRange());
//			newCategory.setType(category.getType().toLowerCase());
//			
//			return categoryRepository.save(category);
//			
//		}else {
//			return null;
//		}
	}

	@Override
	public Category update(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		categoryRepository.deleteById(id);
	}
	
	@Override
	public void deleteAll() {
		 categoryRepository.deleteAll();
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

}
