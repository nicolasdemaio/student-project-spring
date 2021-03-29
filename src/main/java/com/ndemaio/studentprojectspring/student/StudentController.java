package com.ndemaio.studentprojectspring.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "api/v1/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> signUp(@RequestBody Student aStudent){

        Student student = studentService.signUp(aStudent);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping (path = "/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId){

        Student student = studentService.getStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = studentService.getStudents();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @DeleteMapping (path = "/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){

        studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body("Student was deleted correctly.");
    }


}
