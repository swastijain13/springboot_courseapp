package com.example.courseApplication.Controller;

import com.example.courseApplication.Entity.Course;
import com.example.courseApplication.Entity.Student;
import com.example.courseApplication.Service.CourseService;
import com.example.courseApplication.Service.EnrollmentService;
import com.example.courseApplication.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentservice;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/add_student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.addStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    @PostMapping("/add_course")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        Course newCourse = courseService.addCourse(course);
        return ResponseEntity.ok(newCourse);
    }

    @PostMapping("/students/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> enrollStudent(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        enrollmentservice.enrollStudent(studentId, courseId);
        return ResponseEntity.ok("Student enrolled successfully");
    }

    @DeleteMapping("/students/{studentId}/enroll/{courseId}")
    public ResponseEntity<String> removeEnrollment(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        enrollmentservice.removeEnrollment(studentId, courseId);
        return ResponseEntity.ok("Enrollment removed successfully");
    }

    @GetMapping("/students/{studentId}/courses")
    public ResponseEntity<?> getCoursesForStudent(@PathVariable Integer studentId) {
        Set<Course> courses = enrollmentservice.getCoursesForStudent(studentId);
        return ResponseEntity.ok(courses);
    }
}
