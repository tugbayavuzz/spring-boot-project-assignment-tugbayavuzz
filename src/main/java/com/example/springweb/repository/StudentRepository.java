package com.example.springweb.repository;

import com.example.springweb.domObj.StudentDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentDO,Long> {
    Optional<StudentDO> findByDepartment(String department);
}
