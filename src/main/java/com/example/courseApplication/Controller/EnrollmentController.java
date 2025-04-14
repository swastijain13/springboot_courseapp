package com.example.courseApplication.Controller;

import com.example.courseApplication.Entity.Course;
import com.example.courseApplication.Service.CourseService;
import com.example.courseApplication.Service.EnrollmentService;
import com.example.courseApplication.dto.CourseDTO;
import com.example.courseApplication.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentservice;

    @Autowired
    private CourseService courseService;

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
        List<CourseDTO> courses = enrollmentservice.getCoursesForStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/admin/course/{courseId}/students")
    public ResponseEntity<?> getStudentsForCourse(@PathVariable Integer courseId) {
        List<UserDTO> students = enrollmentservice.getStudentsForCourse(courseId);
        return ResponseEntity.ok(students);
    }
}
