package com.hypermarket.springbootproject.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ManagerNotFoundException.class,
            ProductNotFoundException.class,
            ProductOutOfStock.class,
            EmployeeNotFoundException.class,
            UserDoesNotExistException.class,
            UserWithGivenEmailAlreadyExistsException.class,
            RuntimeException.class,
            ManufacturerNotFoundException.class,
            SaleNoteInfoException.class
    })
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
