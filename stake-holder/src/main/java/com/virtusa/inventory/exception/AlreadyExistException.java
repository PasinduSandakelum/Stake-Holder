package com.virtusa.inventory.exception;

import java.sql.SQLIntegrityConstraintViolationException;

public class AlreadyExistException extends SQLIntegrityConstraintViolationException {
    public AlreadyExistException(String message) {
        super(message);
    }
}
