package com.example.nvcreviewassignment.common.handler;

import com.example.nvcreviewassignment.common.exception.FailLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResponse> handleException(IllegalArgumentException e) {

        ApiResponse apiResponse = new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                apiResponse,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({FailLoginException.class})
    public ResponseEntity<ApiResponse> handleException(FailLoginException e) {

        ApiResponse apiResponse = new ApiResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(
                apiResponse,
                HttpStatus.BAD_REQUEST
        );
    }
}
