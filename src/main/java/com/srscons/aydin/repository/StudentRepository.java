package com.srscons.aydin.repository;

import com.srscons.aydin.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    //arxada sql ishleyir
    List<Student> findByNameContainingIgnoreCase(String name);
}
