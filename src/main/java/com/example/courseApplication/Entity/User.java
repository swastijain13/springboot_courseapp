package com.example.courseApplication.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`user`")
public class User {
    public Integer getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String email;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role; // "ADMIN" or "STUDENT"

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String name){
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getCourses(){
        return this.courses;
    }

}
