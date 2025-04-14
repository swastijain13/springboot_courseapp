package com.example.courseApplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<User> students = new HashSet<>();

    public Course(){}

    public Course(Integer id, String title){
        this.id = id;
        this.title = title;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Set<User> getStudents(){
        return this.students;
    }

}
