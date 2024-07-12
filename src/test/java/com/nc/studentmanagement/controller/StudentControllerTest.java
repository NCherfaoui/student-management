package com.nc.studentmanagement.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.studentmanagement.model.Student;
import com.nc.studentmanagement.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllStudents() throws Exception {
        Student student1 = new Student("1", "John", "Doe", "john@example.com", 20);
        Student student2 = new Student("2", "Jane", "Doe", "jane@example.com", 22);
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    void getStudentById() throws Exception {
        Student student = new Student("1", "John", "Doe", "john@example.com", 20);
        when(studentService.getStudentById("1")).thenReturn(Optional.of(student));

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));

        verify(studentService, times(1)).getStudentById("1");
    }

    @Test
    void createStudent() throws Exception {
        Student student = new Student(null, "John", "Doe", "john@example.com", 20);
        Student createdStudent = new Student("1", "John", "Doe", "john@example.com", 20);
        when(studentService.createStudent(any(Student.class))).thenReturn(createdStudent);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("John"));

        verify(studentService, times(1)).createStudent(any(Student.class));
    }

    @Test
    void updateStudent() throws Exception {
        Student updatedStudent = new Student("1", "John", "Smith", "john@example.com", 21);
        when(studentService.updateStudent(eq("1"), any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.age").value(21));

        verify(studentService, times(1)).updateStudent(eq("1"), any(Student.class));
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent("1");
    }
}