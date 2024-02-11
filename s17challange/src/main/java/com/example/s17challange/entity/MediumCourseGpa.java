package com.example.s17challange.entity;

import com.example.s17challange.model.CourseGpa;
import org.springframework.stereotype.Component;

@Component
public class MediumCourseGpa implements CourseGpa {
    @Override
    public int getGpa() {
        return 5;
    }
}
