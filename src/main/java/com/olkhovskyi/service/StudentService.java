package com.olkhovskyi.service;

import com.olkhovskyi.entity.Student;
import com.olkhovskyi.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudentById(Long id) {
        logger.info("Fetching student with id: {}", id);
        return studentRepository.findById(id);
    }

    public List<Student> getStudentsByName(String name) {
        logger.info("Fetching students with name: {}", name);
        return studentRepository.findByName(name);
    }

    public List<Student> getStudentsByCurrentClass(String currentClass) {
        logger.info("Fetching students in class: {}", currentClass);
        return studentRepository.findByCurrentClass(currentClass);
    }

    public List<Student> getAllStudents() {
        logger.info("Fetching all students");
        return (List<Student>) studentRepository.findAll();
    }

    public void saveStudent(Student student){
        logger.info("Saving student: {}",
                student);
        studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        logger.info("Updating student {} with {}", id, updatedStudent);
        return studentRepository.findById(id).map(existingStudent -> {
            updateIfPresent(updatedStudent.getName(), existingStudent::setName);
            updateIfPresent(updatedStudent.getJoinDate(), existingStudent::setJoinDate);
            updateIfPresent(updatedStudent.getBirthDate(), existingStudent::setBirthDate);
            updateIfPresent(updatedStudent.getCurrentClass(), existingStudent::setCurrentClass);
            return studentRepository.save(existingStudent);
        }).orElseGet(() -> {
            updatedStudent.setId(id);
            return studentRepository.save(updatedStudent);
        });
    }

    private <T> void updateIfPresent(T value, Consumer<T> setter) {
        Optional.ofNullable(value).ifPresent(setter);
    }
}
