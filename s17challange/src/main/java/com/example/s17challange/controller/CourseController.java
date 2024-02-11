package com.example.s17challange.controller;

import com.example.s17challange.dto.CourseResponse;
import com.example.s17challange.exceptions.CourseExceptions;
import com.example.s17challange.model.Course;
import com.example.s17challange.model.CourseGpa;
import com.example.s17challange.validation.CourseValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class CourseController {

    private CourseGpa courseGpaLow;
    private CourseGpa courseGpaMedium;
    private CourseGpa courseGpaHigh;

    private List<Course> courses;

    @Autowired
    public CourseController(@Qualifier("lowCourseGpa") CourseGpa courseGpaLow,
                            @Qualifier("mediumCourseGpa") CourseGpa courseGpaMedium,
                            @Qualifier("highCourseGpa") CourseGpa courseGpaHigh) {
        this.courseGpaLow = courseGpaLow;
        this.courseGpaMedium = courseGpaMedium;
        this.courseGpaHigh = courseGpaHigh;
    }

    @PostConstruct
    public void init() {
        this.courses = new ArrayList<>();
    }

    @GetMapping("/courses")
    public List<Course> getAll() {
        return this.courses;
    }

    @GetMapping("/courses/{name}")
    public Course getByName(@PathVariable("name") String courseName) {
        return CourseValidation.isNameExistForGetByName(courses, courseName);
    }

    @PostMapping("/courses")
    public CourseResponse save(@RequestBody Course course) {
        CourseValidation.isIdExist(courses, course);
        CourseValidation.isNameExist(course.getName());
        CourseValidation.isSameNameExist(courses, course);
        CourseValidation.checkCradentialCredit(course.getCredit());
        courses.add(course);
        return new CourseResponse(course, getTotalGpa(course));
    }

    public Integer getTotalGpa(Course course) {
        if (course.getCredit() <= 2) {
            return course.getGrade().getCoefficient() * course.getCredit() * courseGpaLow.getGpa();
        } else if (course.getCredit() == 3) {
            return course.getGrade().getCoefficient() * course.getCredit() * courseGpaMedium.getGpa();
        } else
            return course.getGrade().getCoefficient() * course.getCredit() * courseGpaHigh.getGpa();
    }

    @PutMapping("/courses/{id}")
    public CourseResponse update(@PathVariable("id") Integer courseId, @RequestBody Course course) {
        CourseValidation.isNameExist(course.getName());
        CourseValidation.isSameNameExist(courses, course);
        Course course1 = CourseValidation.checkData(courses, course,courseId);
        return new CourseResponse(course1, getTotalGpa(course));
    }

    @DeleteMapping("/courses/{id}")
    public void delete(@PathVariable("id") Integer courseId){
        CourseValidation.removeFromList(courses,courseId);
    }
}
