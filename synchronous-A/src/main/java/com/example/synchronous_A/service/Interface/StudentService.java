package com.example.synchronous_A.service.Interface;

import com.example.synchronous_A.CustomResponseEntity.ResponseStructure;
import com.example.synchronous_A.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<ResponseStructure<Student>> createStudent(Student student);

    ResponseEntity<ResponseStructure<List<Student>>> getAll();

    ResponseEntity<ResponseStructure<Student>> getById(String id);

    ResponseEntity<ResponseStructure<Student>> updateStudent(String id, Student student);

    ResponseEntity<ResponseStructure<Student>> deleteStudentById(String id);

}