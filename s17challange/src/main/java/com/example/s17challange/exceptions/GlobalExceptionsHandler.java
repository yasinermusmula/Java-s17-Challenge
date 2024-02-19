package com.example.s17challange.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(CourseExceptions.class)
    public ResponseEntity<CourseErrorResponse> handleException(CourseExceptions courseExceptions){
        log.error("CourseException occured! Exception details: " + courseExceptions.getMessage());
        CourseErrorResponse courseErrorResponse = new CourseErrorResponse(courseExceptions.getStatus().value(),
                courseExceptions.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(courseErrorResponse,courseExceptions.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CourseErrorResponse> handleAllException(Exception exception){
        CourseErrorResponse courseErrorResponse =
                new CourseErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        exception.getMessage(),
                        LocalDateTime.now());
        return new ResponseEntity<>(courseErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
