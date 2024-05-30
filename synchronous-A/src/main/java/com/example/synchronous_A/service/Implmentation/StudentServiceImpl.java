package com.example.synchronous_A.service.Implmentation;

import com.example.synchronous_A.CustomResponseEntity.ResponseStructure;
import com.example.synchronous_A.exceptions.EmailIdAlreadyExists;
import com.example.synchronous_A.exceptions.StudentAlreadyExistsWithIdException;
import com.example.synchronous_A.exceptions.StudentNotFoundException;
import com.example.synchronous_A.model.Student;
import com.example.synchronous_A.repository.StudentRepository;
import com.example.synchronous_A.service.Interface.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081/notifications").build();

    @Override
    public ResponseEntity<ResponseStructure<Student>> createStudent(Student student) {
        boolean byId = studentRepository.existsById(student.getId());
        boolean byEmail = studentRepository.existsByEmail(student.getEmail());
        if (byId) {
            throw new StudentAlreadyExistsWithIdException("Student Already Exists With ID : " + student.getId());
        } else if (byEmail) {
            throw new EmailIdAlreadyExists("Email Already Exists : " + student.getEmail());
        } else {
            Student createdStudent = studentRepository.save(student);
            System.out.println("Starting " + LocalTime.now());

            sendNotification("New Student Added : " + student);
            System.out.println("Object Done " + LocalTime.now());

            sendNotification("New student created: " + student.getName());
            System.out.println("String done " + LocalTime.now());

            ResponseStructure<Student> responseStructure = new ResponseStructure<>("Student Account Created Successfully", createdStudent, HttpStatus.CREATED);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseStructure);
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<List<Student>>> getAll() {
        List<Student> students = studentRepository.findAll();
        ResponseStructure<List<Student>> responseStructure = new ResponseStructure<>("Students Retrieved Successfully", students, HttpStatus.OK);
        return ResponseEntity.ok().body(responseStructure);
    }

    @Override
    public ResponseEntity<ResponseStructure<Student>> getById(String id) throws StudentNotFoundException {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student = studentOptional.orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
        ResponseStructure<Student> responseStructure = new ResponseStructure<>("Student Retrieved Successfully", student, HttpStatus.OK);
        return ResponseEntity.ok().body(responseStructure);
    }

    @Override
    public ResponseEntity<ResponseStructure<Student>> updateStudent(String id, Student student) throws StudentNotFoundException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        } else {
            Student existingStudent = optionalStudent.get();

            if (student.getName() != null) {
                existingStudent.setName(student.getName());
            }
            if (student.getAge() != 0) {
                existingStudent.setAge(student.getAge());
            }
            if (student.getAddress() != null) {
                existingStudent.setAddress(student.getAddress());
            }
            if (student.getEmail() != null) {
                existingStudent.setEmail(student.getEmail());
            }
            Student updatedStudent = studentRepository.save(existingStudent);
            sendNotification(updatedStudent);
            ResponseStructure<Student> responseStructure = new ResponseStructure<>("Student updated successfully", updatedStudent, HttpStatus.OK);
            return ResponseEntity.ok().body(responseStructure);
        }
    }

    @Override
    public ResponseEntity<ResponseStructure<Student>> deleteStudentById(String id) throws StudentNotFoundException {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
        ResponseStructure<Student> responseStructure = new ResponseStructure<>("Student deleted successfully", null, HttpStatus.OK);
        return ResponseEntity.ok().body(responseStructure);
    }

    private void sendNotification(Student student) {
        webClient.post().uri("localhost:8081").bodyValue(student).retrieve().bodyToMono(String.class).block();    //Synchronous call
//                .subscribe(response -> System.out.println("Notification response: " + response));         // ASynchronous call
    }

    private void sendNotification(String message) {
        webClient.post().uri("/sendNotification").body(BodyInserters.fromValue(message)).retrieve().bodyToMono(String.class).block();       //Synchronous call
//                .subscribe(response -> System.out.println("Notification response: " + response));     // ASynchronous call
    }
}