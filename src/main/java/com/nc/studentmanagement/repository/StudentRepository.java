package com.nc.studentmanagement.repository;

import com.nc.studentmanagement.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}