package com.nc.studentmanagement.config;

import com.nc.studentmanagement.model.Student;
import com.nc.studentmanagement.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(StudentRepository repository) {
        return args -> {
            repository.deleteAll();

            repository.save(new Student("1", "John", "Doe", "john.doe@example", 20));
            repository.save(new Student("2", "Jane", "Smith", "jane.smith@example.com", 22));
            repository.save(new Student("3", "Mike", "Johnson", "mike.johnson@example.com", 21));

            System.out.println("Sample data initialized");
        };
    }
}