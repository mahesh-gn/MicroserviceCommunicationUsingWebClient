package com.example.synchronous_A.repository;

import com.example.synchronous_A.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    boolean existsById(String id);

    boolean existsByEmail(String email);
}