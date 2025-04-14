package com.example.courseApplication.dto;

public class CourseDTO {
    private Integer id;
    private String title;

    public CourseDTO() {}

    public CourseDTO(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
