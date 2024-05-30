package com.example.synchronous_A.controller;

import com.example.synchronous_A.CustomResponseEntity.ResponseStructure;
import com.example.synchronous_A.model.Student;
import com.example.synchronous_A.service.Interface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Student>> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Student>>> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Student>> getStudentById(@PathVariable String id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Student>> updateStudent(@PathVariable String id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Student>> deleteStudentById(@PathVariable String id) {
        return studentService.deleteStudentById(id);
    }
}