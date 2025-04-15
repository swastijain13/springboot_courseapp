package com.example.courseApplication.Service;

import com.example.courseApplication.Entity.Course;
import com.example.courseApplication.Entity.User;
import com.example.courseApplication.Repository.CourseRepository;
import com.example.courseApplication.Repository.UserRepository;
import com.example.courseApplication.dto.CourseDTO;
import com.example.courseApplication.exceptions.CourseNotFoundException;
import com.example.courseApplication.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private User student;
    private User student2;
    private Course course;

    @BeforeEach
    void setUp() {
        student = new User();
        student.setId(2);
        student.setUsername("student1");
        student.setEmail("student1@example.com");
        student.setRole("STUDENT");
        student.setCourses(new HashSet<>());

        course = new Course();
        course.setId(101);
        course.setTitle("Java Basics");

        student.getCourses().add(course);

        student2 = new User();

    }

    @Test
    public void testEnrollStudent_Success() {
        when(userRepository.findById(2)).thenReturn(Optional.of(student));
        when(courseRepository.findById(101)).thenReturn(Optional.of(course));

        enrollmentService.enrollStudent(2, 101);

        assertTrue(student.getCourses().contains(course));
        verify(userRepository).save(student);
    }

    @Test
    public void testEnrollStudent_StudentNotFound() {
        when(userRepository.findById(3)).thenReturn(Optional.empty());

        Exception exception = assertThrows(StudentNotFoundException.class, () ->
                enrollmentService.enrollStudent(3, 101));

        assertEquals("Student with ID 3 not found", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testEnrollStudent_CourseNotFound() {
        when(userRepository.findById(3)).thenReturn(Optional.of(student));
        when(courseRepository.findById(101)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CourseNotFoundException.class, () ->
                enrollmentService.enrollStudent(3, 101));

        assertEquals("Course with id 101 not found", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testRemoveEnrollment_Success(){
        when(userRepository.findById(2)).thenReturn(Optional.of(student));
        when(courseRepository.findById(101)).thenReturn(Optional.of(course));

        enrollmentService.removeEnrollment(2, 101);

        assertFalse(student.getCourses().contains(course));
        verify(userRepository).save(student);
    }

    @Test
    public void testRemoveEnrollment_CourseNotFound() {

        when(userRepository.findById(2)).thenReturn(Optional.of(student));
        when(courseRepository.findById(101)).thenReturn(Optional.empty());

        Exception exception = assertThrows(CourseNotFoundException.class, () -> {
            enrollmentService.removeEnrollment(2, 101);
        });

        assertEquals("Course with id 101 not found", exception.getMessage());
    }

    @Test
    public void testGetCoursesForStudent_Success() {
        Course course1 = new Course(1, "Math");
        Course course2 = new Course(2, "Science");

        student2.setId(100);
        student2.setRole("STUDENT");
        student2.getCourses().addAll(List.of(course1, course2));

        when(userRepository.findById(100)).thenReturn(Optional.of(student2));

        List<CourseDTO> courseDTOs = enrollmentService.getCoursesForStudent(100);

        assertEquals(2, courseDTOs.size());
        assertEquals("Math", courseDTOs.get(0).getTitle());
        assertEquals("Science", courseDTOs.get(1).getTitle());
    }

    @Test
    public void testGetCoursesForStudent_StudentNotFound() {
        when(userRepository.findById(100)).thenReturn(Optional.empty());

        Exception exception = assertThrows(StudentNotFoundException.class, () -> {
            enrollmentService.getCoursesForStudent(100);
        });

        assertEquals("Student with ID 100 not found", exception.getMessage());
    }
}
