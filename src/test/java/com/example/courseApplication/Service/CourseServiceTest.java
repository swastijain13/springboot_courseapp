package com.example.courseApplication.Service;

import com.example.courseApplication.Entity.Course;
import com.example.courseApplication.Repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepo;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void testAddCourse(){
        Course course = new Course();
        course.setId(1);
        course.setTitle("Spring Boot");

        when(courseRepo.save(any(Course.class))).thenReturn(course);

        // Act
        Course savedCourse = courseService.addCourse(course);

        // Assert
        assertNotNull(savedCourse);
        assertEquals("Spring Boot", savedCourse.getTitle());
    }
}
