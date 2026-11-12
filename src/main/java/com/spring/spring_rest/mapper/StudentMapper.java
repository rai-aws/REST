package com.spring.spring_rest.mapper;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.entity.Student;

/**
 * Utility class responsible for converting between Student entities
 * and StudentDto objects.
 */
public class StudentMapper {

    /**
     * Converts a Student entity into a StudentDto object.
     *
     * @param theStudent the Student entity to convert
     * @return the corresponding StudentDto object
     */
    public static StudentDto mapToStudentDto(Student theStudent){
        return new StudentDto(
            theStudent.getId(), 
            theStudent.getFirstName(), 
            theStudent.getLastName(), 
            theStudent.getEmail());
    }

    /**
     * Converts a StudentDto object into a Student entity.
     *
     * @param theStudentDto the StudentDto object to convert
     * @return the corresponding Student entity
     */
    public static Student mapToStudent(StudentDto theStudentDto){
        return new Student(
            theStudentDto.getId(),
            theStudentDto.getFirstName(), 
            theStudentDto.getLastName(), 
            theStudentDto.getEmail());
    }
}