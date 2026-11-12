package com.spring.spring_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.spring_rest.entity.Student;

/**
 * Repository interface for performing database operations on Student entities.
 * Extends JpaRepository to provide built-in CRUD operations.
 */
public interface StudentRepository extends JpaRepository<Student, Long>{

}
