package com.example.s17challange.dto;

import com.example.s17challange.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CourseResponse {
    private Course course;
    private Integer totalGpa;

}
