package com.spring.spring_rest.service;

import java.util.List;

import com.spring.spring_rest.dto.StudentDto;

/**
 * Service interface defining business operations for managing student records.
 */
public interface StudentService {

    /**
     * Creates a new student record.
     *
     * @param theStudentDto the student details to create
     * @return the created student as a DTO
     */
    StudentDto createStudent(StudentDto theStudentDto);

    /**
     * Retrieves a student by their unique identifier.
     *
     * @param theId the student's ID
     * @return the student as a DTO
     */
    StudentDto getStudentById(Long theId);

    /**
     * Retrieves all student records.
     *
     * @return a list of all students as DTOs
     */
    List<StudentDto> getAllStudents();

    /**
     * Updates an existing student's details.
     *
     * @param theId the ID of the student to update
     * @param theStudentDto the updated student details
     * @return the updated student as a DTO
     */
    StudentDto updateStudent(Long theId, StudentDto theStudentDto);

    /**
     * Partially updates an existing student's details.
     *
     * @param theId the ID of the student to update
     * @param theStudentDto the student details to update
     * @return the updated student as a DTO
     */
    StudentDto patchStudent(Long theId, StudentDto theStudentDto);

    /**
     * Deletes a student record by its unique identifier.
     *
     * @param theId the ID of the student to delete
     */
    void deleteStudentById(Long theId);
}