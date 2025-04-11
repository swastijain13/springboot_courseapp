package com.example.courseApplication.Entity;

import jakarta.persistence.*;
import com.example.courseApplication.Entity.Course;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Integer s_id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    public Student(){}

    public Student(Integer id, String name){
        this.s_id = id;
        this.name = name;
    }

    public Integer getSid(){
        return s_id;
    }

    public void setSid(Integer id){
        this.s_id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}
