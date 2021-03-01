package com.example.springweb.service;

import com.example.springweb.domObj.AddressDO;
import com.example.springweb.domObj.StudentDO;
import com.example.springweb.dto.AddressDTO;
import com.example.springweb.dto.StudentDTO;
import com.example.springweb.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDO createStudent(StudentDO student) {
        return studentRepository.save(student);
    }

    @Override
    public StudentDTO updateStudent(StudentDO student) {

        long studentId = student.getId();
        Optional<StudentDO> currentStudent = studentRepository.findById(studentId);
        if (currentStudent.isPresent()) {
            currentStudent.get().setName(student.getName());
            currentStudent.get().setLastname(student.getLastname());
            currentStudent.get().setDepartment(student.getDepartment());

            AddressDO studentAddress = currentStudent.get().getAddress();
            if (studentAddress == null) {
                studentAddress = new AddressDO();
            }

            studentAddress.setCity(student.getAddress().getCity());
            studentAddress.setRegion(student.getAddress().getRegion());
            studentAddress.setPostCode(student.getAddress().getPostCode());
            currentStudent.get().setAddress(studentAddress);

            studentRepository.save(currentStudent.get()); // veritabanına kaydettik

            StudentDTO studentDTO = new ModelMapper().map(currentStudent.get(), StudentDTO.class);
            AddressDTO addressDTO = new ModelMapper().map(studentAddress, AddressDTO.class);
            studentDTO.setAddressDTO(addressDTO);
            return studentDTO;
        }
        return null;

    }

    @Override
    public void deleteStudent(Long studentId) {
        Optional<StudentDO> currentStudent = studentRepository.findById(studentId);
        if (currentStudent.isPresent()) {
            studentRepository.deleteById(studentId);  //veritabanında varsa sil
        }
    }

    @Override
    public StudentDO getStudent(Long studentId) {
        Optional<StudentDO> currentStudent = studentRepository.findById(studentId);
        if (currentStudent.isPresent()) {
            return currentStudent.get();
        }
        return null;
    }
    @Override
    public StudentDO getStudent(String studentName) {
        Optional<StudentDO> currentStudent = studentRepository.findByName(studentName);
        if (currentStudent.isPresent()) {
            return currentStudent.get();
        }
        return null;
    }

    @Override
    public List<StudentDO> getAllStudent() {
        return studentRepository.findAll();
    }
}
