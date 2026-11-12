package com.spring.spring_rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.entity.Student;
import com.spring.spring_rest.exception.StudentNotFoundException;
import com.spring.spring_rest.mapper.StudentMapper;
import com.spring.spring_rest.repository.StudentRepository;

/**
 * Implementation of the StudentService interface.
 * Handles business operations for creating, retrieving,
 * updating, and deleting student records.
 */
@Service
public class StudentServiceImplement implements StudentService{

    private final StudentRepository studentRepo;

    /**
     * Constructs the service with the required StudentRepository dependency.
     *
     * @param studentRepo the repository for accessing student records
     */
    public StudentServiceImplement(StudentRepository studentRepo){
        this.studentRepo = studentRepo;
    }

    /**
     * Creates a new student record in the database.
     *
     * @param theStudentDto the student details to save
     * @return the saved student as a DTO
     */
    @Override 
    public StudentDto createStudent(StudentDto theStudentDto){
        Student theStudent = StudentMapper.mapToStudent(theStudentDto);
        Student saved = studentRepo.save(theStudent);

        return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * Retrieves a student by their unique identifier.
     *
     * @param theId the ID of the student to retrieve
     * @return the student as a DTO
     * @throws StudentNotFoundException if the student does not exist
     */
    @Override 
    public StudentDto getStudentById(Long theId){
        Student student = studentRepo.findById(theId)
                .orElseThrow(() -> new StudentNotFoundException
                                ("Student id " + theId + " not found!"));

        return StudentMapper.mapToStudentDto(student);
    }

    /**
     * Retrieves all student records from the database.
     *
     * @return a list of all students as DTOs
     */
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepo.findAll();

        return students.stream().map(
                        (student) -> StudentMapper.mapToStudentDto(student))
                        .collect(Collectors.toList());
    }

    /**
     * Updates all editable details of an existing student.
     *
     * @param theId the ID of the student to update
     * @param theStudentDto the new student details
     * @return the updated student as a DTO
     * @throws StudentNotFoundException if the student does not exist
     */
    @Override
    public StudentDto updateStudent(Long theId, StudentDto theStudentDto) {
        Student student = studentRepo.findById(theId)
                .orElseThrow(() -> new StudentNotFoundException
                                ("Student id " + theId + " not found!"));

        student.setFirstName(theStudentDto.getFirstName());
        student.setLastName(theStudentDto.getLastName());
        student.setEmail(theStudentDto.getEmail());

        Student saved = studentRepo.save(student);
        return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * Partially updates an existing student's details.
     * Only fields with non-null values are updated.
     *
     * @param theId the ID of the student to update
     * @param theStudentDto the student details containing fields to update
     * @return the updated student as a DTO
     * @throws StudentNotFoundException if the student does not exist
     */
    @Override
    public StudentDto patchStudent(Long theId, StudentDto theStudentDto) {
        Student student = studentRepo.findById(theId)
                .orElseThrow(() -> new StudentNotFoundException
                                ("Student id " + theId + " not found!"));

        if(theStudentDto.getFirstName() != null){
            student.setFirstName(theStudentDto.getFirstName());
        }
        if(theStudentDto.getLastName() != null){
            student.setLastName(theStudentDto.getLastName());
        }
        if(theStudentDto.getEmail() != null){
            student.setEmail(theStudentDto.getEmail());
        }

        Student saved = studentRepo.save(student);
        return StudentMapper.mapToStudentDto(saved);
    }

    /**
     * Deletes an existing student record by its unique identifier.
     *
     * @param theId the ID of the student to delete
     * @throws StudentNotFoundException if the student does not exist
     */
    @Override
    public void deleteStudentById(Long theId) {
        studentRepo.findById(theId)
                .orElseThrow(() -> new StudentNotFoundException
                                ("Student id " + theId + " not found!"));

        studentRepo.deleteById(theId);
    }


}