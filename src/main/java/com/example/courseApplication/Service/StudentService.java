package com.example.courseApplication.Service;

import com.example.courseApplication.Entity.Student;
import com.example.courseApplication.Repository.StudentRepository;
import com.example.courseApplication.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Method to add a student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + id + " not found"));
    }

}
