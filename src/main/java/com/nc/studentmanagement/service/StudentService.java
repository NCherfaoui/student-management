package com.nc.studentmanagement.service;

import com.nc.studentmanagement.model.Student;
import com.nc.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(String id, Student studentDetails) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student existingStudent = student.get();
            existingStudent.setFirstName(studentDetails.getFirstName());
            existingStudent.setLastName(studentDetails.getLastName());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setAge(studentDetails.getAge());
            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}