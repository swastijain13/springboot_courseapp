package com.example.courseApplication.Service;

import com.example.courseApplication.Entity.Course;
import com.example.courseApplication.Entity.User;
import com.example.courseApplication.Repository.CourseRepository;
import com.example.courseApplication.Repository.UserRepository;
import com.example.courseApplication.dto.CourseDTO;
import com.example.courseApplication.dto.UserDTO;
import com.example.courseApplication.exceptions.CourseNotFoundException;
import com.example.courseApplication.exceptions.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private UserRepository userrepo;

    @Autowired
    private CourseRepository courserepo;

    public void enrollStudent(Integer studentId, Integer courseId) {
        User student = userrepo.findById(studentId)
                .filter(user -> "STUDENT".equals(user.getRole()))
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found"));

        Course course = courserepo.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id " + courseId + " not found"));

        student.getCourses().add(course);
        userrepo.save(student);
    }


    public void removeEnrollment(Integer studentId, Integer courseId) {
        User student = userrepo.findById(studentId)
                .filter(user->"STUDENT".equals(user.getRole()))
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found"));
        Course course = courserepo.findById(courseId)
                .orElseThrow(()-> new CourseNotFoundException("Course with id " + courseId + " not found"));

        student.getCourses().remove(course);
        userrepo.save(student);
    }

    public List<CourseDTO> getCoursesForStudent(Integer studentId) {
        User student = userrepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student with ID " + studentId + " not found"));

        List<CourseDTO> courseDTOs = student.getCourses().stream()
                .map(course -> new CourseDTO(course.getId(), course.getTitle()))
                .collect(Collectors.toList());

        return courseDTOs;

    }

    public List<UserDTO> getStudentsForCourse(Integer courseId){
        Course course = courserepo.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        List<UserDTO> students = course.getStudents().stream()
                .filter(user -> "STUDENT".equalsIgnoreCase(user.getRole()))
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail()))
                .toList();

        return students;
    }
}
