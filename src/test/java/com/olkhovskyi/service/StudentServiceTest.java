package com.olkhovskyi.service;

import com.olkhovskyi.entity.Student;
import com.olkhovskyi.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStudentById() {
        Student student = new Student("John", LocalDate.of(2023, 9, 1), LocalDate.of(2000, 1, 1), "10th Grade");
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));


        Optional<Student> result = studentService.getStudentById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("John", result.get().getName());
    }

    @Test
    void testGetStudentsByName() {
        Student student1 = new Student("John", LocalDate.of(2023, 9, 1), LocalDate.of(2000, 1, 1), "10th Grade");
        Student student2 = new Student("John", LocalDate.of(2022, 8, 1), LocalDate.of(1999, 2, 1), "11th Grade");
        List<Student> students = Arrays.asList(student1, student2);
        when(studentRepository.findByName("John")).thenReturn(students);


        List<Student> result = studentService.getStudentsByName("John");

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("10th Grade", result.get(0).getCurrentClass());
        Assertions.assertEquals("11th Grade", result.get(1).getCurrentClass());
    }

    @Test
    void testGetStudentsByCurrentClass() {
        Student student1 = new Student("John", LocalDate.of(2023, 9, 1), LocalDate.of(2000, 1, 1), "10th Grade");
        Student student2 = new Student("Jane", LocalDate.of(2022, 8, 1), LocalDate.of(1999, 2, 1), "10th Grade");
        List<Student> students = Arrays.asList(student1, student2);
        when(studentRepository.findByCurrentClass("10th Grade")).thenReturn(students);


        List<Student> result = studentService.getStudentsByCurrentClass("10th Grade");


        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals("Jane", result.get(1).getName());
    }

    @Test
    void testGetAllStudents() {
        Student student1 = new Student("John", LocalDate.of(2023, 9, 1), LocalDate.of(2000, 1, 1), "10th Grade");
        Student student2 = new Student("Jane", LocalDate.of(2022, 8, 1), LocalDate.of(1999, 2, 1), "11th Grade");
        List<Student> students = Arrays.asList(student1, student2);
        when(studentRepository.findAll()).thenReturn(students);


        List<Student> result = studentService.getAllStudents();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("John", result.get(0).getName());
        Assertions.assertEquals("Jane", result.get(1).getName());
    }

    @Test
    void testUpdateStudent() {
        Student existingStudent = new Student("John", LocalDate.of(2023, 9, 1), LocalDate.of(2000, 1, 1), "10th Grade");
        existingStudent.setId(1L);
        Student updatedStudent = new Student("John Updated", LocalDate.of(2023, 9, 1), LocalDate.of(2000, 1, 1), "11th Grade");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);


        Student result = studentService.updateStudent(1L, updatedStudent);

        Assertions.assertEquals("John Updated", result.getName());
        Assertions.assertEquals("11th Grade", result.getCurrentClass());
    }
}