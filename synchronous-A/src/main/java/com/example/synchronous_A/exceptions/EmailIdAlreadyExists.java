package com.example.synchronous_A.exceptions;

public class EmailIdAlreadyExists extends RuntimeException {
    public EmailIdAlreadyExists(String message) {
        super(message);
    }
}