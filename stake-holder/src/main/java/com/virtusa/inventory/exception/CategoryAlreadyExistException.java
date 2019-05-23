package com.virtusa.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class CategoryAlreadyExistException extends RuntimeException {
	public CategoryAlreadyExistException(String exception) {
		super(exception);
	}
}
