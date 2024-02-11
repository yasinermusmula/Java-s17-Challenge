package com.example.s17challange.validation;

import com.example.s17challange.dto.CourseResponse;
import com.example.s17challange.exceptions.CourseExceptions;
import com.example.s17challange.model.Course;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CourseValidation {

    public static final String COURSE_NAME_VALID = "Course name can't be null or empty";
    public static final String COURSE_HAS_SAME_NAME = "Course list has same name course already";

    public static final String COURSE_CREDIT_VALID = "Credit valuse is not valid";

    public static final String COURSE_HAS_VALID_ID = "Id is already in list";

    public static final String COURSE_HAS_NOT_VALID_NAME = "There is no name you want to get it";

    public static void isSameNameExist(List<Course> courseList,Course course){
        for (Course c: courseList){
            if (c.getName().equalsIgnoreCase(course.getName())){
                throw new CourseExceptions(COURSE_HAS_SAME_NAME,HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void isNameExist(String name){
        if (name == null || name.isEmpty()){
            throw new CourseExceptions(COURSE_NAME_VALID,HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkCradentialCredit(Integer courseCredit){
        if (courseCredit<0 || courseCredit > 4){
            throw new CourseExceptions(COURSE_CREDIT_VALID,HttpStatus.BAD_REQUEST);
        }
    }

    public static void isIdExist(List<Course> courseList,Course course){
        for (Course c: courseList){
            if (c.getId().equals(course.getId())){
                throw new CourseExceptions(COURSE_HAS_VALID_ID,HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static Course isNameExistForGetByName(List<Course> courseList,String courseName){
        for (Course course : courseList){
           if (course.getName().equalsIgnoreCase(courseName)){
               return course;
           }
        }
        throw new CourseExceptions(CourseValidation.COURSE_HAS_NOT_VALID_NAME,HttpStatus.NOT_FOUND);
    }

    public static Course checkData(List<Course> courses, Course courseNew, Integer courseId){
        for (Course course : courses){
            if (course.getId().equals(courseId)){
                course.setId(courseNew.getId());
                course.setName(courseNew.getName());
                course.setCredit(courseNew.getCredit());
                course.setGrade(courseNew.getGrade());
            }
        }
        return courseNew;
    }

    public static void removeFromList(List<Course> courseList, Integer courseId){
        for (Course course1: courseList){
            if (course1.getId().equals(courseId)){
                courseList.remove(course1);
            }
        }
    }
}
