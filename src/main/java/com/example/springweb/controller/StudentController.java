package com.example.springweb.controller;

import com.example.springweb.domObj.StudentDO;
import com.example.springweb.dto.StudentDTO;
import com.example.springweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(path = "/student", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDO studentDO){
        StudentDTO updatedStudent = studentService.updateStudent(studentDO);
        return new ResponseEntity<>(updatedStudent, HttpStatus.CREATED);
    }

    @GetMapping(path = "/students/{studentId}")
    public ResponseEntity<StudentDO> getStudent(@PathVariable(value="studentId") Long studentId){
        StudentDO student = studentService.getStudent(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/students-by-name/{studentName}")
    public ResponseEntity<StudentDO> getStudent(@PathVariable(value="studentName") String studentName){
        StudentDO student = studentService.getStudent(studentName);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "/students")
    public ResponseEntity<List<StudentDO>> getAllStudents(){
        List<StudentDO> allStudents = studentService.getAllStudent();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }
    @GetMapping(path = "/students{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable(value="studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student with id: " +studentId+ "is deleted.", HttpStatus.OK);
    }


    @GetMapping("/hello")
    String student(){
        return "hello";
    }

}
