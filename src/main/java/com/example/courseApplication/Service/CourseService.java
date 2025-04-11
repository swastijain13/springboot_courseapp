package com.example.courseApplication.Service;

import com.example.courseApplication.Entity.Course;
import com.example.courseApplication.Repository.CourseRepository;
import com.example.courseApplication.exceptions.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Method to add a course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course findCourseById(Integer id){
        return courseRepository.findById(id)
                .orElseThrow(()-> new CourseNotFoundException("Course with id " + id + "not found"));
    }
}
