package com.virtusa.inventory.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.inventory.exception.CategoryAlreadyExistException;
import com.virtusa.inventory.exception.CategoryDoesNotExistException;
import com.virtusa.inventory.exception.CustomerNotFoundException;
import com.virtusa.inventory.modal.Category;
import com.virtusa.inventory.modal.Customer;
import com.virtusa.inventory.service.CategoryService;;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> fetchAll() {
		return ResponseEntity.ok(categoryService.fetchAll());
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Category>> fetchOne(@PathVariable Integer id) {

		Optional<Category> optional = categoryService.findById(id);

		if (!optional.isPresent()) {
			throw new CategoryDoesNotExistException("No category exists under id : " + id);

		}
		return ResponseEntity.ok(optional);

	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ResponseEntity<Long> fetchCount() {
		return ResponseEntity.ok(categoryService.getNoOfCategories());
	}

	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ResponseEntity<Category> save(@Valid @RequestBody Category category) {

		boolean matched = false;

		List<Category> categoryTable = categoryService.fetchAll();
		for (Category item : categoryTable) {
			if (item.getType().equals(category.getType().toLowerCase())) {
				matched = true;
				break;
			} else {
				matched = false;
			}
		}

		if (category.getType() != null && matched == false) {
			Category newCategory = new Category();
			newCategory.setId(category.getId());
			newCategory.setPointRange(category.getPointRange());
			newCategory.setType(category.getType().toLowerCase());
			return ResponseEntity.ok(categoryService.save(newCategory));

		} else {
			throw new CategoryAlreadyExistException("Couldn't add - Category already exists!");
		}

	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Category> update(@PathVariable Integer id, @Valid @RequestBody Category category) {

		boolean matched = false;

		List<Category> categoryTable = categoryService.fetchAll();
		for (Category item : categoryTable) {
			if (item.getId().equals(id)) {
				matched = true;
				break;
			} else {
				matched = false;
			}
		}

		if (category.getType() != null && matched == true) {

			Category newCategory = new Category();
			newCategory.setId(id);
			newCategory.setPointRange(category.getPointRange());
			newCategory.setType(category.getType().toLowerCase());

			return ResponseEntity.ok(categoryService.update(newCategory));

		} else {
			throw new CategoryDoesNotExistException("Couldn't update - No category exists under category id : " + id);
		}
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.DELETE)
	public HttpStatus delete(@PathVariable Integer id) {

		categoryService.delete(id);
		return HttpStatus.OK;

	}

	@RequestMapping(value = "/detail", method = RequestMethod.DELETE)
	public HttpStatus deleteAll() {

		categoryService.deleteAll();
		return HttpStatus.OK;

	}
}
