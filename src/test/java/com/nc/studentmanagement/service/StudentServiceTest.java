package com.nc.studentmanagement.service;

import com.nc.studentmanagement.model.Student;
import com.nc.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void getAllStudents() {
        Student student1 = new Student("1", "John", "Doe", "john@example.com", 20);
        Student student2 = new Student("2", "Jane", "Doe", "jane@example.com", 22);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        List<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentById() {
        Student student = new Student("1", "John", "Doe", "john@example.com", 20);
        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        Optional<Student> foundStudent = studentService.getStudentById("1");

        assertTrue(foundStudent.isPresent());
        assertEquals("John", foundStudent.get().getFirstName());
        verify(studentRepository, times(1)).findById("1");
    }

    @Test
    void createStudent() {
        Student student = new Student(null, "John", "Doe", "john@example.com", 20);
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);

        assertNotNull(createdStudent);
        assertEquals("John", createdStudent.getFirstName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void updateStudent() {
        Student existingStudent = new Student("1", "John", "Doe", "john@example.com", 20);
        Student updatedStudent = new Student("1", "John", "Smith", "john@example.com", 21);
        when(studentRepository.findById("1")).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student result = studentService.updateStudent("1", updatedStudent);

        assertNotNull(result);
        assertEquals("Smith", result.getLastName());
        assertEquals(21, result.getAge());
        verify(studentRepository, times(1)).findById("1");
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void deleteStudent() {
        studentService.deleteStudent("1");
        verify(studentRepository, times(1)).deleteById("1");
    }
}