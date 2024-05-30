package com.example.synchronous_A.exceptions;

import com.example.synchronous_A.CustomResponseEntity.ResponseStructure;
import com.example.synchronous_A.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseStructure<Student>> studentNotFoundException(StudentNotFoundException ex) {
        ResponseStructure<Student> responseStructure = new ResponseStructure<>();
        responseStructure.setMessage(ex.getMessage());
        responseStructure.setData(null);
        responseStructure.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyExistsWithIdException.class)
    public ResponseEntity<ResponseStructure<Student>> studentAlreadyExistsWithIdException(StudentAlreadyExistsWithIdException ex) {
        ResponseStructure<Student> responseStructure = new ResponseStructure<>();
        responseStructure.setMessage(ex.getMessage());
        responseStructure.setData(null);
        responseStructure.setStatus(HttpStatus.CONFLICT);

        return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailIdAlreadyExists.class)
    public ResponseEntity<ResponseStructure<Student>> emailIdAlreadyExists(EmailIdAlreadyExists ex) {
        ResponseStructure<Student> responseStructure = new ResponseStructure<>();
        responseStructure.setMessage(ex.getMessage());
        responseStructure.setData(null);
        responseStructure.setStatus(HttpStatus.CONFLICT);

        return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
    }
}