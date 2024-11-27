package com.shivamingale.backend.exception;

import com.shivamingale.backend.dto.SystemResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<SystemResponse> handleAppException(AppException ex) {
        SystemResponse apiResponse = new SystemResponse<>(false, ex.getMessage());
        HttpStatus status = determineHttpStatus(ex.getErrorCode());
        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SystemResponse> handleGenericException(Exception ex) {
        SystemResponse apiResponse = new SystemResponse<>(false, "An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private HttpStatus determineHttpStatus(String errorCode) {
        switch (errorCode) {
            case "NOT_FOUND":
                return HttpStatus.NOT_FOUND;
            case "CREATION_FAILED":
            case "UPDATE_FAILED":
            case "DELETION_FAILED":
                return HttpStatus.BAD_REQUEST;
            case "AUTH_FAILED":
                return HttpStatus.UNAUTHORIZED;
            case "ALREADY_EXISTS":
                return HttpStatus.CONFLICT;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}