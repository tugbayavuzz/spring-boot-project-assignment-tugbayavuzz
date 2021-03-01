package com.example.springweb.service;

import com.example.springweb.domObj.StudentDO;
import com.example.springweb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDO createStudent(StudentDO student) {
        return studentRepository.save(student);
    }
}
