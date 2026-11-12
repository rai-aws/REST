package com.spring.spring_rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_rest.dto.StudentDto;
import com.spring.spring_rest.entity.Student;
import com.spring.spring_rest.exception.StudentNotFoundException;
import com.spring.spring_rest.service.StudentService;

import jakarta.validation.Valid;
// import jakarta.annotation.PostConstruct;

/**
 * REST controller responsible for handling HTTP requests
 * Provides endpoints for creating, retrieving, updating,
 * and deleting student records.
 */
@RestController 
@RequestMapping("/api/students")
public class StudentRestController {

    private List<Student> theStudents;
    private StudentService studentService;

    /**
     * Constructs the controller with the required StudentService dependency.
     *
     * @param studentService the service responsible for student operations
     */
    public StudentRestController(StudentService studentService){
        this.studentService = studentService;
    }

    // @PostConstruct 
    // public void initialize(){
    //     theStudents = new ArrayList<>();

    //     theStudents.add(new Student("Light", "Yagami"));
    //     theStudents.add(new Student("Saber", "Nana"));
    //     theStudents.add(new Student("Alu", "Card"));
    // }

    /**
     * Retrieves the list of students.
     *
     * @return the list of student records
     */
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    /**
     * Retrieves a student using their index in the student list.
     *
     * @param studentId the index of the student to retrieve
     * @return the student at the specified index
     * @throws StudentNotFoundException if the student ID is invalid
     */
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException
                        ("Student id " + studentId + " not found");
        }
        return theStudents.get(studentId);
    }

    /**
     * Creates a new student record.
     *
     * @param theStudentDto the student details to create
     * @return a response containing the created student and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<StudentDto> createStudents(
                        @Valid @RequestBody StudentDto theStudentDto){
        StudentDto saved = studentService.createStudent(theStudentDto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Retrieves a student by their unique identifier.
     *
     * @param id the ID of the student to retrieve
     * @return a response containing the student and HTTP 200 status
     */
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
        StudentDto studentDto = studentService.getStudentById(id);

        return ResponseEntity.ok(studentDto);
    }

    /**
     * Retrieves all student records.
     *
     * @return a response containing the list of students and HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> students = studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }

    /**
     * Updates all editable details of an existing student.
     *
     * @param id the ID of the student to update
     * @param theStudentDto the updated student details
     * @return a response containing the updated student and HTTP 200 status
     */
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, 
                            @Valid @RequestBody StudentDto theStudentDto) {
        StudentDto studentDto = studentService.
                                updateStudent(id, theStudentDto);

        return ResponseEntity.ok(studentDto);
    }

    /**
     * Partially updates an existing student's details.
     *
     * @param id the ID of the student to update
     * @param theStudentDto the student fields to update
     * @return a response containing the updated student and HTTP 200 status
     */
    @PatchMapping("{id}")
    public ResponseEntity<StudentDto> patchStudent(@PathVariable Long id,
                            @Valid @RequestBody StudentDto theStudentDto ) {
        StudentDto studentDto = studentService.patchStudent(id, theStudentDto);

        return ResponseEntity.ok(studentDto);
    }

    /**
     * Deletes a student record by its unique identifier.
     *
     * @param id the ID of the student to delete
     * @return a response containing a success message and HTTP 200 status
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);

        return ResponseEntity.ok("Student deleted successfully!");
    }
}