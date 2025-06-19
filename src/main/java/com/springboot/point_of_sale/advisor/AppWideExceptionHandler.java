package com.springboot.point_of_sale.advisor;

import com.springboot.point_of_sale.exceptions.NotFoundException;
import com.springboot.point_of_sale.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Error", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
