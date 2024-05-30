package com.example.synchronous_A.exceptions;

public class StudentAlreadyExistsWithIdException extends RuntimeException {
    public StudentAlreadyExistsWithIdException(String message) {
        super(message);
    }
}