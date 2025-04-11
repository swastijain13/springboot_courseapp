package com.example.courseApplication.Service;

import com.example.courseApplication.Entity.Course;
import com.example.courseApplication.Entity.Student;
import com.example.courseApplication.Repository.CourseRepository;
import com.example.courseApplication.Repository.StudentRepository;
import com.example.courseApplication.exceptions.CourseNotFoundException;
import com.example.courseApplication.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class EnrollmentService {

    @Autowired
    private StudentRepository studrepo;

    @Autowired
    private CourseRepository courserepo;

    public void enrollStudent(Integer studentId, Integer courseId) {
        Student student = studrepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found"));
        Course course = courserepo.findById(courseId)
                .orElseThrow(()-> new CourseNotFoundException("Course with id " + courseId + " not found"));

        // Enroll student in course
        student.getCourses().add(course);
        studrepo.save(student);
    }


    public void removeEnrollment(Integer studentId, Integer courseId) {
        Student student = studrepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found"));
        Course course = courserepo.findById(courseId)
                .orElseThrow(()-> new CourseNotFoundException("Course with id " + courseId + " not found"));

        student.getCourses().remove(course);
        studrepo.save(student);
    }

    public Set<Course> getCoursesForStudent(Integer studentId) {
        Student student = studrepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found"));

        return student.getCourses();

    }
}
