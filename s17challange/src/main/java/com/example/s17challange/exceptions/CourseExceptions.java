package com.example.s17challange.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CourseExceptions extends RuntimeException{
    private HttpStatus status;

    public CourseExceptions(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
