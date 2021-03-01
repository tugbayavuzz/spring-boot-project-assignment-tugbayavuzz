package com.example.springweb.controller;

import com.example.springweb.domObj.StudentDO;
import com.example.springweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/student", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentDO> createStudent(@RequestBody StudentDO studentDO){
        StudentDO createdStudent = studentService.createStudent(studentDO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    String student(){
        return "hello";
    }

}
