package com.example.synchronous_A.CustomResponseEntity;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {
    private String message;
    private T data;
    private HttpStatus status;
}