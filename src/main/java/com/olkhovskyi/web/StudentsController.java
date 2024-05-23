package com.olkhovskyi.web;

import com.olkhovskyi.entity.Student;
import com.olkhovskyi.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentsController {

    private static final Logger logger = LoggerFactory.getLogger(StudentsController.class);

    private final StudentService studentService;

    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        logger.info("Received request to get all students");
        return studentService.getAllStudents();
    }

    @PostMapping("/students")
    void addStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        logger.info("Received request to get student by id: {}", id);
        return studentService.getStudentById(id);
    }

    @GetMapping("/students/name/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        logger.info("Received request to get students by name: {}", name);
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/students/class/{currentClass}")
    public List<Student> getStudentsByCurrentClass(@PathVariable String currentClass) {
        logger.info("Received request to get students by class: {}", currentClass);
        return studentService.getStudentsByCurrentClass(currentClass);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        logger.info("Received request to update student with id: {}", id);
        return studentService.updateStudent(id, updatedStudent);
    }
}
