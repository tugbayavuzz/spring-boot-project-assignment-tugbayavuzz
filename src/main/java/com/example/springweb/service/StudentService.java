package com.example.springweb.service;

import com.example.springweb.domObj.StudentDO;
import com.example.springweb.dto.StudentDTO;

import java.util.List;

public interface StudentService {

     StudentDO createStudent(StudentDO student);

     StudentDTO updateStudent(StudentDO student);

     void deleteStudent(Long studentId);

     StudentDO getStudent(Long studentId);

     StudentDO getStudent(String studentName);

     List<StudentDO> getAllStudent();
}
