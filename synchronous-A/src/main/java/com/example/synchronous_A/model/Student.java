package com.example.synchronous_A.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SynchronousStudent")
@Getter
@Setter
@ToString
public class Student {

    private String id;

    @NotBlank(message = "Name is Required")
    private String name;

    @NotBlank(message = "Age is Required")
    private int age;

    @NotBlank(message = "Address is Required")
    private String address;

    @NotBlank(message = "Email is Required")
    @Pattern(regexp = "^[a-z0-9._%+-]+@gmail\\.com$", message = "Email format should be in lowercase and end with '.gmail.com'")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=?<>]).{8,}$\n", message = "Password must be at least 8 characters long and contain at least one uppercase letter and one number")
    private String password;
}